/**
 * Created by zjh on 14-5-15.
 */
angular.module('docsSimpleDirective', [])
.controller('Controller', ['$scope', function($scope){
        $scope.customer = {
            name: 'Naomi',
            address: '1600 Amphitheatre'
        };
    }])
.directive('myCustomer', function(){
        return {
            templateUrl: 'template.html'
        }
    });