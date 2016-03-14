<%@ include file="init.jsp"%>

<nrtl:pageTemplate>

  <nrtl:header>
    <h1>Timetable</h1>
  </nrtl:header>

  <div ng-controller="TimetableController">
    <div class="row">
      <div class="col-sm-8">
        <!-- Grid is placed here by angular.js -->
        <div class="gridStyle" ng-grid="gridOptions"></div>
      </div>
      <div class="col-sm-4">
        <form name="form" class="css-form form-vertical" novalidate>
          <h2 id="addToTimetable">Add/edit Timetable Record</h2>
          <div class="form-group">
            <label>Employee involved:</label>
            <ui-select sortable="true" ng-model="entry.employee" theme="bootstrap" title="Choose employee">
              <ui-select-match placeholder="Select or search a employee in the list...">{{$select.selected.employeeName}}</ui-select-match>
              <ui-select-choices repeat="item in employeeList | filter: $select.search">
                <div ng-bind-html="item.employeeName | highlight: $select.search"></div>
                <small ng-bind-html="item.kind | highlight: $select.search"></small>
              </ui-select-choices>
            </ui-select>
          </div>
          <div class="form-group">
            <label>Project involved:</label>
            <ui-select sortable="true" ng-model="entry.project" theme="bootstrap" title="Choose project">
              <ui-select-match placeholder="Select or search a project in the list...">{{$select.selected.projectName}}</ui-select-match>
              <ui-select-choices repeat="item in projectList | filter: $select.search">
                <div ng-bind-html="item.projectName | highlight: $select.search"></div>
              </ui-select-choices>
            </ui-select>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <label for="inputDatepickerStart">Start date:</label>
            </div>
            <div class="col-sm-12">
              <div class="form-group">
                <div class="input-group">
                  <input id="inputDatepickerStart" class="form-control" type="text" ng-model="entry.startDate">
                  <div class="input-group-addon"><i class="icon-calendar"></i></div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <label for="inputDatepickerEnd">End date:</label>
            </div>
            <div class="col-sm-12">
              <div class="form-group">
                <div class="input-group">
                  <input id="inputDatepickerEnd" class="form-control" type="text" ng-model="entry.endDate">
                  <div class="input-group-addon"><i class="icon-calendar"></i></div>
                </div>
              </div>
            </div>
          </div>
          <div class="text-center">
            <button ng-click="update(entry)" class="btn btn-primary">SAVE</button>
            <button ng-click="clear()" class="btn btn-danger">CLEAR</button>
          </div>
        </form>
      </div>
    </div>
  </div>


</nrtl:pageTemplate>
