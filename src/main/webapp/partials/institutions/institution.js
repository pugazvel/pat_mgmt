'use strict';

angular.module('institutionModule', ['ngTable','timestamp','angularMoment']);

//Routers
myApp.config(function($stateProvider) {
  
  //Search institutions
  $stateProvider.state('institutions', {
	url: '/institutionView',
    templateUrl: 'partials/institutions/institutions.html',
	data:{
		auth:true
	}
  });
    
});

//Factories
myApp.factory('institutionService', ['$http','$rootScope', function($http,$rootScope) {

    var factoryDefinitions = {
      getInstitutionList: function() {
		  $rootScope.promise = $http.post('institution/fetch').then( function success(data) { return data; }, function error(data) {return data; });
		  return $rootScope.promise;
      },
      getInstList: function() {
		  $rootScope.promise = $http.post('institution/fetch').then( function success(data) { return data; }, function error(data) {return data; });
		  return $rootScope.promise;
      },
	  addInstitution: function(institution) {
		  $rootScope.promise = $http.post('institution/create', institution).then( function success(data) { return data; }, function error(data) {return data; });
		  return $rootScope.promise;
      },
      updateInstitution: function(institution) {
    	  $rootScope.promise = $http.post('institution/update', institution).then( function success(data) { return data; }, function error(data) {return data; });
    	  return $rootScope.promise;
      }
	}
	
    return factoryDefinitions;
  }
]);

//Controllers
myApp.controller('institutionController', ['$scope', '$window', 'institutionService', 'dataTable', '$fancyModal',  function($scope, $window, institutionService, dataTable, $fancyModal) {
	
	$scope.getInstitutionList = function() {
		$scope.errorMsg = null;
		institutionService.getInstitutionList().then(function(result){
			$scope.data = result.data;	
			if (result.data.status === 200) {	
				dataTable.render($scope, '', "institutionList", result.data.response);
			} 
		});
	}
	$scope.getInstList = function() {
		$scope.errorMsg = null;
		institutionService.getInstList().then(function(result){
			$scope.data = result.data;	
			if (result.data.status === 200) {			
				$scope.instList = result.data.response;
			} 
		});
	}
	
	$scope.getInstitutionList();
	$scope.getInstList();
	
	$scope.newInstitutionDialog = function() {
		$scope.errorMsg = null;
		$scope.institution = {};
		$scope.title = 'Add Institution';
		$scope.dialogObj = $fancyModal.open({ templateUrl: 'partials/institutions/addInstitution.html', scope: $scope});
	};
	$scope.editInstitutionDialog = function(institution) {
		$scope.errorMsg = null;
		$scope.institution = institution;
		$scope.title = 'Edit Institution';
		$scope.dialogObj = $fancyModal.open({ templateUrl: 'partials/institutions/addInstitution.html', scope: $scope});
	};
	$scope.addOrUpdateInstitution = function() {
		if($scope.institution.id)
			$scope.updateInstitution();
		else
			$scope.addInstitution();
	}
	
	$scope.addInstitution = function() {
		$scope.errorMsg = null;
		institutionService.addInstitution($scope.institution).then(function(result){
			$scope.data = result.data;
			if (result.data.status === 200) {	
				$scope.dialogObj.close();
				$window.location.reload();
			} else {
				$scope.errorMsg = result.data.message;
			}	
		});	
	}
	
	$scope.updateInstitution = function() {
		$scope.errorMsg = null;
		institutionService.updateInstitution($scope.institution).then(function(result){
			$scope.data = result.data;
			if (result.data.status === 200) {
				$scope.dialogObj.close();
				$window.location.reload();
			} else {
				$scope.errorMsg = result.data.message;
			}	
		});	
	}
	
}]);