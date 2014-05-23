/**
 * Created by zjh on 14-5-12.
 */
//'use strict';
angular.module('phonecatApp', ['ngRoute', 'phonecatControllers', 'phonecatFilters'])
   .config(['$routeProvider',
    function($routeProvider){
        $routeProvider
            .when('/phones', {
                templateUrl: 'partials/phone-list.html',
                controller: 'PhoneListCtrl'
            })
            .when('/phones/:phoneId', {
                templateUrl: 'partials/phone-detail.html',
                controller: 'PhoneDetailCtrl'
            })
            .otherwise({redirectTo: '/phones'})
    }]);

