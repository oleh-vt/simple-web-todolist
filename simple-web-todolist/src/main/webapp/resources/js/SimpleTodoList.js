(function () {
   
    var simpleTodoListService = function ($http) {
    	//var serverUrl = '/simple-web-todolist/tasks';
        var serverUrl = '/tasks'; 
        
        var findTasks = function () {
            return $http.get(serverUrl).then(function (response) {
                        return response.data;
                    });
        };


        var saveTask = function (user) {
            return  $http({
                        method: 'POST',
                        url: serverUrl,
                        data: user,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    })
                        .then(function (response) {
                            return response.data;
                        });
        };
        
        var markDone = function (id) {
            return $http.put(serverUrl + '?id='+id).then(function (response) {
                        return response.data;
                    });
        };

        var deleteTask = function (id) {
             return $http.delete(serverUrl + '?id='+id).then(function (response) {
                        return response.data;
                    });
        };
        
        return {
            findTasks: findTasks,
            saveTask: saveTask,
            markTaskDone: markDone,
            deleteTask: deleteTask
        };
    };

    angular.module("SimpleTodoList").factory("simpleTodoListService", simpleTodoListService);
}());