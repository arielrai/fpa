angular.module('fpa').controller('FuncaoTableCtrl', function($scope, $requestService, $state,
		$requestService, dialogs, $window, $state, $stateParams) {
	$scope.editar = function(id){
		$state.go($state.$current.name+"Cadastro", { "id": id});
	}
	
	$scope.novo = function(){
		$state.go($state.$current.name+"Cadastro");
	}
	
	$scope.excluir = function(id){
		var dlg = dialogs.confirm("Confirme a operação", "Você tem certeza que deseja excluir o registro", {size:"sm"});
		dlg.result.then(function(btn){
			$requestService.delete($state.$current.name + "/" + id, function(response) {
				$window.location.reload(); //TODO apenas recarregar os dados
			}, function(data, status) {
				//Fazer algo se gerar erro
			});
		
		},function(btn){
			$scope.confirmed = 'You confirmed "No."';
		});
	}
	
	$scope.resource = function(params, paramsObj) {
		return $requestService.get($state.$current.name + "/table/" +$stateParams.id, function(response){
			$scope.tableBean = response.data;
			$scope.title = response.data.title;
			$scope.icon = response.data.icon;
			$scope.header = response.data.header;
			return {
				'rows' : response.data.rows,
				'header' : response.data.header,
				'pagination': response.data.pagination
			}
		});
	};
});