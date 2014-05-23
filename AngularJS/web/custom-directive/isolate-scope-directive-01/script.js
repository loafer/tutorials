/**
 * Created by zjh on 14-5-15.
 */
angular.module('docsIslateScopeDirective', [])
.controller('Controller', ['$scope', function($scope){
        $scope.naomi = {name: 'Naomi',address: '1600 Amphitheatre'};
        $scope.igor = { name: 'Igor', address: '123 Somewhere' };
    }])
.directive('myCustomer', function(){
        return {
            restrict: 'E',
            scope: {
                customerInfo: '=info'
            },
            templateUrl: 'template.html'
        }
    });