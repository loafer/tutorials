/**
 * Created by zjh on 14-5-16.
 */
angular.module('docsIsoFnBindExample', [])
.controller('Controller', ['$scope', '$timeout', function($scope, $timeout){
    $scope.name = 'Tobias';
        $scope.hideDialog = function(){
            $scope.dialogIsHidden = true;
            $timeout(function(){
                $scope.dialogIsHidden = false;
            }, 2000);
        }
}])
.directive('myDialogClose', function(){
    return {
        restrict: 'E',
        transclude: true,
        scope: {
            close: '&onClose'
        },
        templateUrl: 'my-dialog-close.html'
    }
});