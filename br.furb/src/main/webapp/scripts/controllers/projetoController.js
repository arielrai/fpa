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
			$scope.formDesc = response.data;
		});
	}else{
		$requestService.get("projetos/form", function(response){
			console.log(response);
			$scope.form = response.data;
		});
	}
	
});