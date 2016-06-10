angular.module('fpa').controller('ProjetoController',

function($scope, $location, $state, $stateParams, $requestService) {

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
		$requestService.get("projetos/form/" + $stateParams.id, function(response){
			$scope.form = response.data;
			$scope.form.pojo.dataInicial = new Date($scope.form.pojo.dataInicial);
			$scope.form.pojo.dataFinal = new Date($scope.form.pojo.dataFinal);
			$scope.form.pojo.valorFormatado = $scope.form.pojo.valorFormatado.replace("R$", "").replace(",", "");
			var length = $scope.form.pojo.valorFormatado.length;
			for (var int = 0; int < 7-length; int++) {
				$scope.form.pojo.valorFormatado = "0"+ $scope.form.pojo.valorFormatado;
				
			}
			$scope.$watch('form.pojo', function (newValue, oldValue, scope) {
				$scope.formValid = $scope.isAllFieldValid();
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
	
	$scope.save = function(pojo){
		if (pojo.id == null) {
			//Create
			$requestService.post("projetos", function(data) {
				$scope.form.pojo = data.data;
				$scope.success = data.data.message;
			}, function(error) {
				$scope.error = error.data;
			}, pojo);
		}else{
			//Update
			$requestService.put("projetos/"+pojo.id, function(data) {
				$scope.success = data.data.message;
			}, function(error, status) {
				$scope.error = error.data;
			}, pojo);
		}
	
	}
	
	$scope.getTitle = function(){
		if (angular.isDefined($scope.form) && angular.isDefined($scope.form.pojo) && $scope.form.pojo.nome != null) {
			return $scope.form.pojo.nome + " - Código: " + ($scope.form.pojo.id == null ? "" : $scope.form.pojo.id);
		}else{
			return "Cadastro de Projeto";
		}
	}
	

	$scope.funcao = function(id){
		$state.go("funcoes", {id: id});
	}
});