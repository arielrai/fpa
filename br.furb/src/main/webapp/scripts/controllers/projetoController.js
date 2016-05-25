var projeto = angular.module('fpa', [ 'ngWizard', 'ngAnimate' ]);

projeto.controller('ProjetoController', function ($scope){
		  $scope.submit = function (){
		    alert("Submitted Wizard!");
		  }
		  $scope.addNewStep = function (newStep){
		    $scope.dynamicSteps.push(newStep);
		  }
		  
		  $scope.dynamicSteps = ["step 3"];
		  $scope.dynamicRequiredText = {};
	
});