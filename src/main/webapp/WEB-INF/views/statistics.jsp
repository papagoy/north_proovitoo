<%@ include file="init.jsp"%>
<nrtl:pageTemplate>

  <nrtl:header>
    <h1>Statistics <i class="icon-refresh" onclick="location.reload()"></i></h1>
  </nrtl:header>

  <div class="row" ng-controller="StatisticsController">

    <div class="col-sm-12">
      <div class="row">
        <div class="col-sm-4">
          <h4>Overview</h4>
          <ul>
            <li><c:out value="The project with most records is: ${projectWithMostRecords.projectName}" /></li>
            <li><c:out value="The worst executed project is: Washing the car" /></li>
            <li><c:out value="The best executed project is: Washing the car" /></li>
          </ul>
        </div>
        <div class="col-sm-8">
          <h4>Employees involvement</h4>
          <canvas id="employeesInvolvementChart" class="chart chart-employee-involvement"></canvas>
        </div>
      </div>
    </div>
    <div class="col-sm-12">

      <div class="panel-group" bs-collapse data-start-collapsed="true">
        <div class="panel panel-default" ng-repeat="project in projects">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a bs-collapse-toggle ng-click="load(project)">
                {{ project.projectName }}
              </a>
            </h4>
          </div>
          <div class="panel-collapse" bs-collapse-target>
            <div class="panel-body">

              <div class="row text-center">
                <div class="col-sm-6">
                  <h3>Satisfaction Records</h3>
                  <canvas id="satisfactionRecordsChart" class="char chart-satisfaction-records"></canvas>
                  <div id="satisfactionRecordsChartLegend" class="chart-legend"></div>
                </div>
                <div class="col-sm-6">
                  <h3>Employees Records</h3>
                  <canvas id="employeesRecordsChart" class="char chart-satisfaction-employees"></canvas>
                  <div id="employeesRecordsChartLegend" class="chart-legend"></div>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>

    </div>

  </div>

</nrtl:pageTemplate>


