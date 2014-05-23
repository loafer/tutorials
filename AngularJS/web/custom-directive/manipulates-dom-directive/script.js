/**
 * Created by zjh on 14-5-15.
 */
angular.module('docsTimeDirective', [])
.controller('Controller', ['$scope', function($scope){
        $scope.format = 'yyyy-MM-dd h:mm:ss a'
    }])
.directive('myCurrentTime', ['$interval', 'dateFilter', function($interval, dateFilter){
        function link(scope, element, attrs){
            var format, timerId;

            function updateTime(){
                element.text(dateFilter(new Date(), format));
            }

            scope.$watch(attrs.myCurrentTime, function(value){
                format = value;
                updateTime();
            });

            element.on('$destroy', function(){
                $interval.cancel(timerId);
            });

            timerId = $interval(function(){
                updateTime();
            }, 1000);
        }

        return {link: link}
    }]);