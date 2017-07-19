'use strict';

// Declare app level module which depends on views, and components
var myApp = angular.module('myApp', [
  'ui.router',
  'ui.bootstrap',
  'validation', 
  'validation.rule', 
  'institutionModule',
  'patientModule',
  'vesparny.fancyModal',
  'ngAnimate',
  'cgBusy'
]);

myApp.controller('MainCtrl', ['$rootScope', function($rootScope,$http){
	// Loading
	$rootScope.delay = 0;
	$rootScope.minDuration = 0;
	$rootScope.message = 'Please Wait...';
	$rootScope.backdrop = true;
	$rootScope.promise = null;
}]);

//Config phase
myApp.config(function($urlRouterProvider, $httpProvider) {
  //session check and redirect to specific state
	$urlRouterProvider.otherwise("/patientView");  
  
  $httpProvider.interceptors.push(function($q) {

      return {

          'responseError': function(rejection){

              var defer = $q.defer();

              if(rejection.status == 403){
                  window.location.href = "#login";
              } else {
            	  defer.reject(rejection);

                  return defer.promise;
              }
          }
      };
  });
    
});

//Run phase
myApp.run(function($rootScope, $state) {
	$rootScope.$state = $state; //Get state info in view
	$rootScope.userInfo = {'firstname':'Velraja', 'lastname':'Nagarajan'};
});

//Datatable
myApp.factory('dataTable', ['$filter', 'ngTableParams', function($filter, ngTableParams) {

    var factoryDefinition = {
      render: function($scope, config, componentId, data) {
        
		if(!config) config ={};
		var config = angular.extend({}, {page:1, count:10}, config)
		
		$scope[componentId] = new ngTableParams(config, {
			total: data.length, // length of data
			getData: function($defer, params) {
				// use build-in angular filter
				var filteredData = params.filter() ?
						$filter('filter')(data, params.filter()) :
						data;
				var orderedData = params.sorting() ?
						$filter('orderBy')(filteredData, params.orderBy()) :
						data;
				params.total(orderedData.length); // set total for recalc pagination
				$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
			}
		}); 
		
		
      }
    }
	
    return factoryDefinition;
  }
]);

//For top sub menu (look others menu)
$(function () {
	$('.subnavbar').find ('li').each (function (i) {
		var mod = i % 3;
		if (mod === 2) {
			$(this).addClass ('subnavbar-open-right');
		}
	});
});

