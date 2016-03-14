var app = angular.module('grading-app', [ 'ngSanitize', 'ngGrid', 'mgcrea.ngStrap' ,'ui.select']);

/* =========== EmployeeController =========== */
app
    .controller(
        'EmployeeController',
        function($scope, $http) {

          $scope.loadData = function() {
            $http.get('/employee/data.json').then(function(res) {
              console.log(res.data);
              $scope.employees = res.data;
            });
          }

          $scope.loadData();

          $scope.makeColDefs = function(row) {
            var colDefs = [ {
              field : 'id',
              displayName : 'Id',
              resizable : false
            }, {
              field : 'employeeName',
              displayName : 'Employee Name'
            }, {
              field : 'kind',
              displayName : 'Kind'
            }, {
              field : 'salary',
              displayName : 'Salary'
            } ];

            var buttonTemplate = '<div><button id="delete_{{row.rowIndex}}" class="btn btn-sm btn-transparent" ng-click="deleteRow(row)"><li class="icon-trash"></li></button></div>';
            var saveColumn = {};
            saveColumn.field = '';
            saveColumn.width = 'auto';
            saveColumn.cellTemplate = buttonTemplate;
            colDefs.push(saveColumn);

            return colDefs;
          };

          $scope.gridOptions = {
            data : 'employees',
            columnDefs : $scope.makeColDefs(),
            multiSelect : false,
            afterSelectionChange : function(rowItem, event) {
              if (rowItem.selected) {
                console.log(rowItem.entity);
                // reset form
                $scope.employee = {};
                $scope.form.$setPristine();
                // set new value
                $scope.employee = angular.copy(rowItem.entity);
              }
            }
          };
          
          $scope.clear = function() {
            $scope.employee = {};
          };

          $scope.update = function(employee) {
            $http.post('/employee/save', JSON.stringify(employee), {
              contentType : 'application/json',
              dataType : 'json'
            }).then(function(res) {
              $scope.loadData();
              // reset form
              $scope.employee = {};
              $scope.form.$setPristine();
            });
          };

          $scope.deleteRow = function(row) {
            $http.post('/employee/delete', JSON.stringify(row.entity), {
              async : true,
              contentType : 'application/json',
              dataType : 'json'
            }).then(function(res) {
              $scope.loadData();
              // reset form
              $scope.employee = {};
              $scope.form.$setPristine();
            });
          };

        });

/* =========== ProjectController =========== */

app
    .controller(
        'ProjectController',
        function($scope, $http) {

          $scope.loadData = function() {
            $http.get('/project/data.json').then(function(res) {
              $scope.projects = res.data;
            });
          }

          $scope.loadData();

          $scope.makeColDefs = function(row) {
            var colDefs = [ {
              field : 'id',
              displayName : 'Id',
              resizable : false,
              width : '30px'
            }, {
              field : 'projectName',
              displayName : 'Project Name'
            } ];

            var buttonTemplate = '<div><button id="delete_{{row.rowIndex}}" class="btn btn-sm btn-transparent" ng-click="deleteRow(row)"><li class="icon-trash"></li></button></div>';
            var saveColumn = {};
            saveColumn.field = '';
            saveColumn.width = '60px';
            saveColumn.cellTemplate = buttonTemplate;
            colDefs.push(saveColumn);

            return colDefs;
          };

          $scope.gridOptions = {
            data : 'projects',
            columnDefs : $scope.makeColDefs(),
            multiSelect : false,
            afterSelectionChange : function(rowItem, event) {
              if (rowItem.selected) {
                console.log(rowItem.entity);
                // reset form
                $scope.project = {};
                $scope.form.$setPristine();
                // set new value
                $scope.project = angular.copy(rowItem.entity);
              }
            }
          };
          
          $scope.clear = function() {
            $scope.project = {};
          };

          $scope.update = function(project) {
            $http.post('/project/save', JSON.stringify(project), {
              contentType : 'application/json',
              dataType : 'json'
            }).then(function(res) {
              $scope.loadData();
              // reset form
              $scope.project = {
                projectName : project.projectName
              };
              $scope.form.$setPristine();
            });
          };

          $scope.deleteRow = function(row) {
            $http.post('/project/delete', JSON.stringify(row.entity), {
              async : true,
              contentType : 'application/json',
              dataType : 'json'
            }).then(function(res) {
              $scope.loadData();
              // reset form
              $scope.project = {};
              $scope.form.$setPristine();
            });
          };

        });

/* =========== RecordController =========== */

app
    .controller(
        'RecordController',
        function($scope, $http) {

          $scope.loadData = function() {
            $http.get('/record/data.json').then(function(res) {
              $scope.records = res.data;
            });
          }

          $scope.record = {};
          $scope.employeeList = [];
          $scope.projectList = [];
          $scope.typeOptions = [ 'VERY_NEGATIVE', 'NEGATIVE', 'NEUTRAL', 'POSITIVE', 'VERY_POSITIVE' ];

          $http.get("/employee/data.json").then(
            function (response) {
              console.log(response.data);
              $scope.employeeList = response.data;
            },
            function (response) {
              console.log('Error loading employee list');
            }
          );

          $http.get("/project/data.json").then(
            function (response) {
              console.log(response.data);
              $scope.projectList = response.data;
            },
            function (response) {
              console.log('Error loading project list');
            }
          );

          $scope.loadData();

          $scope.makeColDefs = function(row) {
            var colDefs = [ {
              field : 'id',
              displayName : 'Id',
              resizable : false,
              width : '30px'
            }, 
            {
              field : 'date',
              cellFilter: 'date:\'dd.MM.yyyy\'',
              displayName : 'Date'
            }, 
            {
              field : 'typeString',
              displayName : 'Type'
            },
            {
              field : 'employeeNames',
              displayName : 'Employees'
            }, 
            {
              field : 'projectNames',
              displayName : 'Projects'
            },
            {
              field : 'content',
              displayName : 'Record content'
            } ];

            var buttonTemplate = '<div><button id="delete_{{row.rowIndex}}" class="btn btn-sm btn-transparent" ng-click="deleteRow(row)"><li class="icon-trash"></li></button></div>';
            var saveColumn = {};
            saveColumn.field = '';
            saveColumn.width = '60px';
            saveColumn.cellTemplate = buttonTemplate;
            colDefs.push(saveColumn);

            return colDefs;
          };

          $scope.gridOptions = {
            data : 'records',
            columnDefs : $scope.makeColDefs(),
            multiSelect : false,
            afterSelectionChange : function(rowItem, event) {
              if (rowItem.selected) {
                console.log(rowItem.entity);
                // reset form
                $scope.record = {};
                $scope.form.$setPristine();
                // set new value
                $scope.record = angular.copy(rowItem.entity);
              }
            }
          };

          $scope.clear = function() {
            $scope.record = {};
          };
          
          $scope.update = function(record) {
            console.log(JSON.stringify(record));
            $http.post('/record/save', JSON.stringify(record), {
              contentType : 'application/json',
              dataType : 'json'
            }).then(function(res) {
              $scope.loadData();
              // reset form
              $scope.record = {};
              $scope.form.$setPristine();
            });
          };

          $scope.deleteRow = function(row) {
            console.log( JSON.stringify(row.entity.id));
            $http.post('/record/delete', JSON.stringify(row.entity), {
              async : true,
              contentType : 'application/json',
              dataType : 'json'
            }).then(function(res) {
              $scope.loadData();
              // reset form
              $scope.record = {};
              $scope.form.$setPristine();
            });
          };

        });

/* =========== StatisticsController =========== */

app
    .controller(
        'StatisticsController',
        function($scope, $http) {
          $scope.projects = [];
          $scope.employeeInvolvement = [];

          $http.get('/project/data.json').then(function(res) {
            $scope.projects = res.data;
          });

          // Draw Employee Involvement Chart
          $http.get('/statistics/employeeInvolvement.json').then(function(res) {
            ChartDrawer.drawBarChart("employeesInvolvementChart", res.data.labels, [{
              label: "Employee Involvement by Project",
              fillColor: "rgba(220,220,220,0.5)",
              strokeColor: "rgba(220,220,220,0.8)",
              highlightFill: "rgba(220,220,220,0.75)",
              highlightStroke: "rgba(220,220,220,1)",
              data: res.data.data
            }]);
          });

          $scope.load = function(project) {
            console.log(project);
            $http.get('/statistics/satisfactionRecords/' + project.id + '.json').then(function(res) {
              ChartDrawer.drawPieChart("satisfactionRecordsChart", res.data.labels, res.data.data, {
                'VERY_NEGATIVE': 'darkRed',
                'NEGATIVE': 'red',
                'NEUTRAL': 'yellow',
                'POSITIVE': 'green',
                'VERY_POSITIVE': 'lime'
              });
            });
            $http.get('/statistics/employeeRecords/' + project.id + '.json').then(function(res) {
              var labels = ['VERY_NEGATIVE', 'NEGATIVE', 'NEUTRAL', 'POSITIVE', 'VERY_POSITIVE'];
              var datasets = [];
              for (var i in res.data) {
                // TODO: Create a data array based on json data
                var data = [1, 2, 1, 0, 1];
                var color = ChartDrawer.randomColor();
                datasets.push({
                   label: res.data[i].title,
                   fillColor: color,
                   strokeColor: color,
                   highlightFill: color,
                   highlightStroke: color,
                   data: data
                });
              }
              ChartDrawer.drawBarChart("employeesRecordsChart", labels, datasets);
            });
          };

        });

/* =========== TimetableController =========== */

app
    .controller(
        'TimetableController',
        function($scope, $http) {
          $scope.entry = {};

          $scope.gridData = {};
          $scope.employeeList = [];
          $scope.projectList = [];
          // TODO Load employee and project list

          $scope.loadData = function() {
            $http.get('/timetable/data.json').then(function(res) {
              $scope.gridData = res.data;
            });
          }

          $scope.loadData();

          $scope.makeColDefs = function(row) {
            // TODO Add fields
            var colDefs = [];

            var buttonTemplate = '<div><button id="delete_{{row.rowIndex}}" class="btn btn-sm btn-transparent" ng-click="deleteRow(row)"><li class="icon-trash"></li></button></div>';
            var saveColumn = {};
            saveColumn.field = '';
            saveColumn.width = '60px';
            saveColumn.cellTemplate = buttonTemplate;
            colDefs.push(saveColumn);

            return colDefs;
          };

          $scope.gridOptions = {
            data : 'gridData',
            columnDefs : $scope.makeColDefs(),
            multiSelect : false,
            afterSelectionChange : function(rowItem, event) {
              if (rowItem.selected) {
                console.log(rowItem.entity);
                // reset form
                $scope.entry = {};
                $scope.form.$setPristine();
                // set new value
                $scope.entry = angular.copy(rowItem.entity);
              }
            }
          };

          $scope.update = function(timetable) {
            console.log(JSON.stringify(timetable));
            $http.post('/timetable/save', JSON.stringify(timetable), {
              contentType : 'application/json',
              dataType : 'json'
            }).then(function(res) {
              $scope.loadData();
              // reset form
              $scope.entry = {};
              $scope.form.$setPristine();
            });
          };

          $scope.clear = function() {
            $scope.entry = {};
          };

          $scope.deleteRow = function(row) {
            $http.post('/timetable/delete', JSON.stringify(row.entity), {
              async : true,
              contentType : 'application/json',
              dataType : 'json'
            }).then(function(res) {
              $scope.loadData();
              // reset form
              $scope.entry = {};
              $scope.form.$setPristine();
            });
          };


        });

ChartDrawer = {
  randomColor: function() {
    return '#' + ('000000' + Math.floor(Math.random()*16777215).toString(16)).slice(-6);
  },
  drawBarChart: function(id, labels, datasets) {
    // Get the context of the canvas element we want to select
    var ctx = document.getElementById(id).getContext("2d");
    var chart = new Chart(ctx).Bar({
      labels: labels,
      datasets: datasets
    });
    var legend = document.getElementById(id + "Legend");
    if (legend != null) {
      legend.innerHTML = chart.generateLegend();
    }
  },
  drawPieChart: function(id, labels, data, colors) {
    var combinedData = [];
    for(var i in labels) {
      combinedData.push({
        value: data[i],
        color: colors[labels[i]] ? colors[labels[i]] : ChartDrawer.randomColor(), // or random color
        label: labels[i]
      });
    }
    var ctx = document.getElementById(id).getContext("2d");
    var chart = new Chart(ctx).Pie(combinedData);
    var legend = document.getElementById(id + "Legend");
    if (legend != null) {
      legend.innerHTML = chart.generateLegend();
    }
  }
};

Submenu = {

  populateSubmenu : function(menuId, templateItemId, sectionClassName) {
    var template = $('#' + templateItemId);
    $("section." + sectionClassName).each(function() {
      var addItem = template.clone();
      var sectionId = $(this).attr('id');
      addItem.attr('id', 'menu' + sectionId);
      addItem.find('a').attr('href', '#' + sectionId);

      var biggestHeadingInSection;
      for (var i = 1; i < 6; i++) {
        biggestHeadingInSection = $(this).find('h' + i);
        if (biggestHeadingInSection.length > 0) {
          break;
        }
      }

      addItem.find('a').text(biggestHeadingInSection.text());
      $('#' + menuId).append(addItem);
      addItem.show();
    });
  }

}