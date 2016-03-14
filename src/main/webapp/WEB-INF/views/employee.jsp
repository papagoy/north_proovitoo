<%@ include file="init.jsp"%>
<nrtl:pageTemplate>

  <nrtl:header>
    <h1>Employees</h1>
  </nrtl:header>

  <div class="row" ng-controller="EmployeeController">
    <div class="col-sm-9">
      <div class="gridStyle" ng-grid="gridOptions"></div>
    </div>

    <div class="col-sm-3">
      <form name="form" class="css-form" novalidate>
        <div class="form-group">
          <label for="employeeName">Employee Name:</label>
          <input type="text" class="form-control" id="employeeName" ng-model="employee.employeeName" required />
        </div>

        <div class="form-group">
          <label for="employeeKind">Employee Kind:</label>
          <input type="text" class="form-control" id="employeeKind" ng-model="employee.kind" required />
        </div>
        <!-- TODO: Uncomment this once back-end supports this field
        <div class="form-group">
          <label for="inputDatepicker">Start Date:</label>
          <div class="input-group">
            <input type="text" class="form-control" id="inputDatepicker" ng-model="employee.startDate" data-date-format="dd.MM.yyyy" data-date-type="string" bs-datepicker>
            <div class="input-group-addon"><i class="icon-calendar"></i></div>
          </div>
        </div>
        -->
        <div class="form-group">
          <label>Salary:</label>
          <input type="number" class="form-control" ng-model="employee.salary" required />
        </div>

        <button ng-click="update(employee)" class="btn btn-primary">SAVE</button>
        <button ng-click="clear()" class="btn btn-danger">CLEAR</button>
        
      </form>
    </div>

  </div>

</nrtl:pageTemplate>


