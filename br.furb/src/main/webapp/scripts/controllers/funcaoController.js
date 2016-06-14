angular.module('fpa').controller('FuncaoController',

function($scope, $rootScope, $location, $state, $stateParams, $requestService) {

	$scope.page = 1;
	$scope.params = angular.fromJson($stateParams.params);
	
	if (!!$scope.params.id) {
		$requestService.get("funcoes/form/" + $scope.params.id + "/" + $scope.params.projeto, function(response){
			$scope.form = response.data;
			angular.forEach($scope.form.params.tipo, function(value, key) {
				if (angular.equals($scope.form.pojo.complexidade.nome, value.value.nome)) {
					$scope.form.pojo.complexidade = value.value;
				}
			})
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
	
	$scope.save = function(form){
//		if (pojo.id == null) {
//			//Create
//			$requestService.post("funcoes", function(data) {
//				$scope.form.pojo = data.data;
//				$scope.success = data.data.message;
//			}, function(error) {
//				$scope.error = error.data;
//			}, pojo);
//		}else{
			//salva
		var id = form.pojo.complexidade.id;
		var copy = angular.copy(form.pojo);
		copy.complexidade = null;
		copy.projeto = {id:form.pojo.projeto.id}
			$requestService.post("funcoes", function(data) {
				$rootScope.error = "";
				$rootScope.success = data.message;
				$scope.form.pojo.valor = data.entity;
			}, function(error, status) {
				$rootScope.error = error.data;
				$rootScope.success = "";
			}, {funcao: copy, tabelas: $scope.form.params.tabelas, complexidadeId: id});
//		}
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