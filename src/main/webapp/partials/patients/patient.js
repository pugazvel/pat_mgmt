'use strict';

angular.module('patientModule', ['ngTable','timestamp','angularMoment']);

//Routers
myApp.config(function($stateProvider) {
  
  //Search patients
  $stateProvider.state('patients', {
	url: '/patientView',
    templateUrl: 'partials/patients/patients.html',
	data:{
		auth:true
	}
  });
    
});

//Factories
myApp.factory('patientService', ['$http','$rootScope', function($http,$rootScope) {

    var factoryDefinitions = {
      getPatientList: function() {
		  $rootScope.promise = $http.post('patient/fetch').then( function success(data) { return data; }, function error(data) {return data; });
		  return $rootScope.promise;
      },
	  addPatient: function(patient) {
		  $rootScope.promise = $http.post('patient/create', patient).then( function success(data) { return data; }, function error(data) {return data; });
		  return $rootScope.promise;
      },
      updatePatient: function(patient) {
    	  $rootScope.promise = $http.post('patient/update', patient).then( function success(data) { return data; }, function error(data) {return data; });
    	  return $rootScope.promise;
      }, 
      getExamList: function(patientId) {
		  $rootScope.promise = $http.post('exam/fetch?patientId='+patientId).then( function success(data) { return data; }, function error(data) {return data; });
		  return $rootScope.promise;
      },
	  addExam: function(exam) {
		  $rootScope.promise = $http.post('exam/create', exam).then( function success(data) { return data; }, function error(data) {return data; });
		  return $rootScope.promise;
      },
      updateExam: function(exam) {
    	  $rootScope.promise = $http.post('exam/update', exam).then( function success(data) { return data; }, function error(data) {return data; });
    	  return $rootScope.promise;
      }, 
      getInstList: function() {
		  $rootScope.promise = $http.post('institution/fetch').then( function success(data) { return data; }, function error(data) {return data; });
		  return $rootScope.promise;
      }
	}
	
    return factoryDefinitions;
  }
]);

//Controllers
myApp.controller('patientController', ['$scope', '$window', 'patientService', 'dataTable', '$fancyModal',  function($scope, $window, patientService, dataTable, $fancyModal) {
	
	$scope.getPatientList = function() {
		$scope.errorMsg = null;
		patientService.getPatientList().then(function(result){
			$scope.data = result.data;	
			if (result.data.status === 200) {	
				dataTable.render($scope, '', "patientList", result.data.response);
			}
		});
	}
	$scope.getInstList = function() {
		$scope.errorMsg = null;
		patientService.getInstList().then(function(result){
			$scope.data = result.data;	
			if (result.data.status === 200) {			
				$scope.instList = result.data.response;
			} 
		});
	}
	
	$scope.getPatientList();
	$scope.getInstList();
	
	$scope.newPatientDialog = function() {
		$scope.errorMsg = null;
		$scope.patient = {};
		$scope.title = 'Add Patient';
		$scope.dialogObj = $fancyModal.open({ templateUrl: 'partials/patients/addPatient.html', scope: $scope});
	};
	$scope.editPatientDialog = function(patient) {
		$scope.errorMsg = null;
		$scope.patient = patient;
		$scope.title = 'Edit Patient';
		$scope.dialogObj = $fancyModal.open({ templateUrl: 'partials/patients/addPatient.html', scope: $scope});
	};
	$scope.addOrUpdatePatient = function() {
		if($scope.patient.id)
			$scope.updatePatient();
		else
			$scope.addPatient();
	}
	
	$scope.addPatient = function() {
		$scope.errorMsg = null;
		patientService.addPatient($scope.patient).then(function(result){
			$scope.data = result.data;
			if (result.data.status === 200) {	
				$scope.dialogObj.close();
				$window.location.reload();
			} else {
				$scope.errorMsg = result.data.message;
			}	
		});	
	}
	
	$scope.updatePatient = function() {
		$scope.errorMsg = null;
		patientService.updatePatient($scope.patient).then(function(result){
			$scope.data = result.data;
			if (result.data.status === 200) {
				$scope.dialogObj.close();
				$window.location.reload();
			} else {
				$scope.errorMsg = result.data.message;
			}	
		});	
	}
	
	
	$scope.openExamListView = function(patient) {
		$scope.patient = patient;
		$scope.title =  'Exams('+patient.patientId+')';
		$scope.dialogObj = $fancyModal.open({ templateUrl: 'partials/patients/viewExamList.html',
			scope : $scope
		});
	};
	
}]);

//Controllers
myApp.controller('examController', ['$scope', '$window', 'patientService', 'dataTable', '$fancyModal', 'moment',   function($scope, $window, patientService, dataTable, $fancyModal, moment) {
	
	$scope.getExamList = function() {
		$scope.errorMsg = null;
		patientService.getExamList($scope.patient.id).then(function(result){
			$scope.data = result.data;	
			if (result.data.status === 200) {			
				dataTable.render($scope, '', "examList", result.data.response);
			} 
		});
	}
	$scope.newExamDialog = function() {
		$scope.errorMsg = null;
		$scope.exam = {'patientId':$scope.patient.id};
		$scope.title = 'Add Exam';
		$scope.dialogAddExamObj = $fancyModal.open({ templateUrl: 'partials/patients/addExam.html', scope: $scope});
	};
	$scope.editExamDialog = function(exam) {
		$scope.errorMsg = null;
		$scope.exam = {};
		angular.copy(exam, $scope.exam);
		if($scope.exam.examDate != null)
			$scope.exam.examDate = moment($scope.exam.examDate).format("DD/MM/YYYY");
		$scope.title = 'Edit Exam';
		$scope.dialogAddExamObj = $fancyModal.open({ templateUrl: 'partials/patients/addExam.html', scope: $scope});
	};
	$scope.addOrUpdateExam = function() {
		if($scope.exam.examDate != null)
			$scope.exam.examDate = moment($scope.exam.examDate, 'DD/MM/YYYY');
		if($scope.exam.id)
			$scope.updateExam();
		else
			$scope.addExam();
	}
	$scope.addExam = function() {
		$scope.errorMsg = null;
		patientService.addExam($scope.exam).then(function(result){
			$scope.data = result.data;
			if (result.data.status === 200) {	
				$scope.dialogAddExamObj.close();
				$scope.dialogObj.close();
			} else {
				$scope.errorMsg = result.data.message;
			}	
		});	
	}
	
	$scope.updateExam = function() {
		$scope.errorMsg = null;
		patientService.updateExam($scope.exam).then(function(result){
			$scope.data = result.data;
			if (result.data.status === 200) {
				$scope.dialogAddExamObj.close();
				$scope.getExamList();
			} else {
				$scope.errorMsg = result.data.message;
			}	
		});	
	}
	
	$scope.getExamList();
}]);