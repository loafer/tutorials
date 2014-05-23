/**
 * Created by zjh on 14-5-13.
 */
angular.module('phonecatFilters', []).filter('checkmark', function(){
    return function(input){
        return input ? '\u2713' : '\u2718';
    }
});