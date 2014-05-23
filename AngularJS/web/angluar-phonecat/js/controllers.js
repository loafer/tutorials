/**
 * Created by zjh on 14-5-12.
 */
//'use strict';
var phonecatControllers = angular.module('phonecatControllers', []);

phonecatControllers.controller('PhoneListCtrl', ['$scope', '$http', function($scope, $http){
    $http.get('phones/phones.json').success(function(data){
        $scope.phones = data.splice(0, 5);
        $scope.orderPro = 'age';
    });
}]);

phonecatControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http){
//    $scope.phoneId = $routeParams.phoneId;
    $http.get('phones/' + $routeParams.phoneId + '.json').success(function(data){
        $scope.phone = data;
        $scope.mainImageUrl = data.images[0]
    });

    $scope.setImage = function(imgUrl){
        $scope.mainImageUrl = imgUrl;
    }
}]);

