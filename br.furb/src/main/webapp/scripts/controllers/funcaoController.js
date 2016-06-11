angular.module('fpa').controller('FuncaoController',

function($scope, $location, $state, $stateParams, $requestService) {

	$scope.page = 1;
	$scope.params = angular.fromJson($stateParams.params);
	
	if (!!$stateParams.id) {
		$requestService.get("funcoes/form/" + $scope.params.id + "/" + $scope.params.projeto, function(response){
			$scope.form = response.data;
			$scope.$watch('form.pojo', function (newValue, oldValue, scope) {
				$scope.formValid = $scope.isAllFieldValid();
			}, true);
		});
	}else{
		$requestService.get("funcoes/form/"+ $scope.params.projeto, function(response){
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
			$requestService.post("funcoes", function(data) {
				$scope.form.pojo = data.data;
				$scope.success = data.data.message;
			}, function(error) {
				$scope.error = error.data;
			}, pojo);
		}else{
			//Update
			$requestService.put("funcoes/"+pojo.id, function(data) {
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
			return "Cadastro de Função";
		}
	}
	
	$scope.addAtribute = function(tabela){
		tabela.campos.push({nome: ""});
	}
	
	$scope.removeTabela = function(index){
		$scope.form.params.tabelas.splice(index, 1);
	}
	
	$scope.addTabela = function(){
		$scope.form.params.tabelas.push({nome:"", campos:[]});
	}
	
	$scope.removeAtribute = function(tabela, index){
		tabela.campos.splice(index, 1);
	}
});