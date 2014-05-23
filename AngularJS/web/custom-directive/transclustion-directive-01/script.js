/**
 * Created by zjh on 14-5-16.
 */
angular.module('docsTransclustionDirective', [])
.controller('Controller', ['$scope', function($scope){
        $scope.name = 'Tobias';
}])
.directive('myDialog', function(){
    return {
        restrict: 'E',
        transclude: true,
        scope: {},
        link: function(scope, element){
            scope.name = 'Jeff';
        },
        templateUrl: 'my-dialog.html'
    }
});