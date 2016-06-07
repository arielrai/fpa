angular.module('fpa').controller('ProjetoController',
		function($scope, $location, $state, $requestService) {

	

			$scope.formData = {};
			$scope.currentPage = 1;

			$scope.$on('$viewContentLoaded', function() {
				$state.go("cadastroProjeto." + $scope.currentPage);
			});

			// function to change the currentPage
			$scope.onClick = function(pageNumber) {
				$scope.currentPage = pageNumber;
				$state.go("cadastroProjeto." + $scope.currentPage);
			}

			// function to process the form
			$scope.processForm = function() {
				$scope.currentPage = $scope.currentPage + 1;
				$state.go("cadastroProjeto." + $scope.currentPage);
				//fazer uma requisição para o servidor para solicitar a próxima página
			};
			
			$requestService.get($state.$current.name + "/wizard", response => {
				$scope.dados = response.data.dados;

				//receber uma lista de perguntas ou apenas 
				//1 pergunta por vez e fazer uma nova requisição a cada nextStep
				
				//receber os metadados (diretiva) da página a ser renderizada
				
			}, () => {
				console.log('Internal Error')
			});

		});