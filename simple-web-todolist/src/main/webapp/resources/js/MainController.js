(function () {
    var app = angular.module("SimpleTodoList");
    
    var MainController = function ($scope, simpleTodoListService) {

        $scope.isAccomplished='false';
        $scope.due_date=$.datepicker.formatDate('dd-mm-yy', new Date());
        $scope.priority='MEDIUM';

        var allTasks=[];

        $scope.init = function () {
            simpleTodoListService.findTasks().then(function (data) {
                allTasks = data;
                populateTasksToDisplay($scope.isAccomplished);
            }, onError);
        };

        var populateTasksToDisplay = function (accomplished) {
            $scope.tasks = [];
            $scope.tasks = allTasks.filter(elem => elem.completed == JSON.parse(accomplished));
        };

        var onError = function (data) {
            $scope.userError = data;
        };

        $scope.delete = function (obj) {
            var id = obj.target.parentNode.parentNode.id;
            simpleTodoListService.deleteTask(id).then(function (data) {
                for(var i = 0; i < allTasks.length; i++){
                    if(allTasks[i].id == id){
                        allTasks.splice(i, 1);
                        break;
                    }
                }
                populateTasksToDisplay($scope.isAccomplished);

            }, onError);
        };

        $scope.markDone = function (obj) {
            var id = obj.target.parentNode.parentNode.id;
            simpleTodoListService.markTaskDone(id).then(function (data) {
            	for(var i = 0; i < allTasks.length; i++){
                    if(allTasks[i].id == id){
                        allTasks[i].completed=true;
                        break;
                    }
                }
                populateTasksToDisplay($scope.isAccomplished);
            }, onError);
        };

        $scope.$watch(function ($scope) {
            return $scope.isAccomplished;
        }, function (newValue, oldValue) {
            populateTasksToDisplay(newValue);
        });

        $scope.add = function (task, date, priority) {
            simpleTodoListService.saveTask(
                'name='+ task +
                '&dueDate='+date+
                '&priority='+priority
            ).then(function (data) {
                $scope.due_date=$.datepicker.formatDate('dd-mm-yy', new Date());
                $scope.priority='MEDIUM';
                $scope.what="";
                allTasks.push(data);
                if(!JSON.parse($scope.isAccomplished))
                    $scope.tasks.push(data);
            }, onError);
        };

        $scope.init();
    };
    
    app.controller("MainController", ["$scope", "simpleTodoListService", MainController]);
}());