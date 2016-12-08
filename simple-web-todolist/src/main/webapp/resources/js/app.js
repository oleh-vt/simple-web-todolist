(function () {
    var app = angular.module("SimpleTodoList", []);

    app.directive('jqdatepicker', function () {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function (scope, element, attrs, ngModelCtrl) {
                element.datepicker({
                    dateFormat: 'dd-mm-yy',
                    minDate: new Date(),
                    onSelect: function (date) {
                        scope.due_date = date;
                        scope.$apply();
                    }
                });
            }
        };
    });
}());