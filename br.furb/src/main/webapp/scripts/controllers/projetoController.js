angular.module('fpa').controller('ProjetoController',

function($scope, $location, $state, $stateParams, $requestService) {

	$scope.formData = {};
	$scope.currentPage = 1;

	// function to process the form
	$scope.processForm = function() {
		$scope.currentPage = $scope.currentPage + 1;
		$state.go("cadastroProjeto." + $scope.currentPage);
		// fazer uma requisição para o servidor para solicitar a próxima página
	};

	$scope.page = 1;
	
	$scope.fatorLabel = function(selected){
		for (fator in $scope.form.params.fatores) {
			if ($scope.form.params.fatores[fator].value == selected) {
				return $scope.form.params.fatores[fator].desc;
			}
		}
		return 'def';
	}
	
	if (!!$stateParams.id) {
		$requestService.post("projetos/form/" + $stateParams.id, function(response){
			$scope.form = response.data;
			$scope.$watch('form.pojo', function (newValue, oldValue, scope) {
				if (angular.isDefined($scope.formValid)) {
					$scope.formValid = $scope.isAllFieldValid();
				}else{
					$scope.formValid = false;
				}
			}, true);
		});
	}else{
		$requestService.get("projetos/form", function(response){
			$scope.form = response.data;
			$scope.$watch('form.pojo', function (newValue, oldValue, scope) {
				if (angular.isDefined($scope.formValid)) {
					$scope.formValid = $scope.isAllFieldValid();
				}else{
					$scope.formValid = false;
				}
			}, true);
		});
	}
	
	$scope.isAllFieldValid = function(){
		if (angular.isDefined($scope.form.fields)) {
			for (field in $scope.form.fields) {
				if (!!$scope.form.fields[field].formControl && $scope.form.fields[field].formControl.$invalid) {
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	} 
	
	$scope.formataValor = function(value){
		value = value + "";
		var afterComma = value.substring(value.length-2, value.length);
		var beforeComma = value.substr(0, value.length-2).replace(/^0+/, '');
		return "R$ " + beforeComma +","+ afterComma;
	}
	
	
});