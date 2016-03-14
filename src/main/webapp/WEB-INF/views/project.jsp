<%@ include file="init.jsp"%>

<nrtl:pageTemplate>

  <nrtl:header>
    <h1>Projects</h1>
  </nrtl:header>

  <div class="row" ng-controller="ProjectController">

    <div class="col-sm-7">
      <!-- Grid is placed here by angular.js -->
      <div class="gridStyle" ng-grid="gridOptions"></div>
    </div>

    <div class="col-sm-5">
      <form name="form" class="css-form" novalidate>
        <div class="form-group">
          <label>Project Name:</label>
          <input type="text" class="form-control" ng-model="project.projectName" required />
        </div>
        <button ng-click="update(project)" class="btn btn-primary">SAVE</button>
        <button ng-click="clear()" class="btn btn-danger">CLEAR</button>
      </form>
    </div>


  </div>


</nrtl:pageTemplate>


