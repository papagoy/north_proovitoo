package com.nortal.assignment.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * @author Priit Liivak
 *
 */
@Service
public class ProjectServiceJdbcImpl implements ProjectService {

  private final class projectMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
      Project project = new Project();
      project.setId(rs.getLong("id"));
      project.setProjectName(rs.getString("project_name"));

      return project;
    }
  }

  @Resource
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Project> getListData() {
    String sql = "select id, project_name from project";

    return jdbcTemplate.query(sql, new projectMapper());
  }

  @Override
  public void save(Project project) {
    String sql;
    List<Object> args = new ArrayList<>();
    args.add(project.getProjectName());

    if (project.getId() == null) {
      sql = "insert into project (project_name) values (?)";
    } else {
      sql = "update project set project_name=? where id= ?";
      args.add(project.getId());
    }

    jdbcTemplate.update(sql, args.toArray());
  }

  @Override
  public void delete(Long projectId) {
    String sql = "delete from projects where id=?";
    jdbcTemplate.update(sql, projectId);
  }

  @Override
  public Project getProjectWithMostRecords() {
    //@formatter:off
    String sql = "SELECT p.* FROM project p where p.id in "
                     + "(SELECT project_id FROM "
                         + "(SELECT rp.project_id, COUNT(rp.record_id) record_count FROM record_project rp GROUP BY rp.project_id) proj_rec_count "
                     + "ORDER BY record_count DESC  LIMIT 1) ";
    //@formatter:on
    return jdbcTemplate.queryForObject(sql, new projectMapper());
  }

  @Override
  public Project getBestExecutedProject() {
    return getExecutedProjectGrading(false);
  }

  @Override
  public Project getWorstExecutedProject() {
    return getExecutedProjectGrading(true);
  }

  private Project getExecutedProjectGrading(boolean positiveMinusNegative) {
    //@formatter:off
    String sql = "select * from project p where p.id in (select project_id from( "
                   + "select project_id, sum(record_count) total_count from ( "
                       + "select negative_records.project_id, "+(positiveMinusNegative?"":"-")+"count(negative_records.record_id) record_count from( "
                          + "select rp.* from record_project rp join record r on r.id=rp.record_id where r.type_code in ('NEGATIVE', 'VERY_NEGATIVE') "
                       + ") as negative_records group by negative_records.project_id "
                       + "union "
                       + "select positive_records.project_id, "+(positiveMinusNegative?"-":"")+"count(positive_records.record_id) record_count from( "
                          + "select rp.* from record_project rp join record r on r.id=rp.record_id where r.type_code in ('POSITIVE', 'VERY_POSITIVE') "
                       + ") as positive_records group by positive_records.project_id "
                   + ") group by project_id order by total_count LIMIT 1 "
               + ")) ";
  //@formatter:on
    return jdbcTemplate.queryForObject(sql, new projectMapper());
  }

}
