<%@ include file="init.jsp"%>

<nrtl:pageTemplate>

  <nrtl:header>
    <h1>Records</h1>
  </nrtl:header>

  <div ng-controller="RecordController">
    <div class="row">
      <div class="col-sm-12">
        <!-- Grid is placed here by angular.js -->
        <div class="gridStyle" ng-grid="gridOptions"></div>
      </div>
    </div>

    <form name="form" class="css-form" novalidate>
      <h2 id="addRecord">Add/edit record</h2>
      <div class="row">
        <div class="col-sm-4">
          <div class="form-group">
            <label>Employees involved:</label>
            <ui-select multiple="true" sortable="true" ng-model="record.employees" theme="bootstrap" class="form-control" title="Choose employees">
              <ui-select-match placeholder="Select or search a employee in the list...">{{$item.employeeName}}</ui-select-match>
              <ui-select-choices repeat="item in employeeList | filter: $select.search track by item.id">
                <div ng-bind-html="item.employeeName | highlight: $select.search"></div>
                <small ng-bind-html="item.kind | highlight: $select.search"></small>
              </ui-select-choices>
            </ui-select>
          </div>
          <div class="form-group">
            <label>Projects involved:</label>
            <ui-select multiple="true" sortable="true" ng-model="record.projects" theme="bootstrap" class="form-control" title="Choose projects">
              <ui-select-match placeholder="Select or search a projects in the list...">{{$item.projectName}}</ui-select-match>
              <ui-select-choices repeat="item in projectList | filter: $select.search track by item.id">
                <div ng-bind-html="item.projectName | highlight: $select.search"></div>
              </ui-select-choices>
            </ui-select>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="form-group">
            <label for="inputDatepicker">Date:</label>
            <div class="input-group">
              <input id="inputDatepicker" class="form-control" type="text" ng-model="record.date" data-date-format="dd.MM.yyyy" data-date-type="string" bs-datepicker>
              <div class="input-group-addon"><i class="icon-calendar"></i></div>
            </div>
          </div>
          <div class="form-group">
            <label>Type:</label>
            <select ng-model="record.typeString" class="form-control" required ng-options="type as type for type in typeOptions">
            </select>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="form-group">
            <label>Content:</label>
            <textarea class="form-control" ng-model="record.content" required ng-maxlength="2000" style="height: 108px;"></textarea>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 text-center">
          <button ng-click="update(record)" class="btn btn-primary">SAVE</button>
          <button ng-click="clear()" class="btn btn-danger">CLEAR</button>
          <a href="/record/export" class="btn btn-link">Export records</a>
        </div>
      </div>
    </form>

  </div>


</nrtl:pageTemplate>


