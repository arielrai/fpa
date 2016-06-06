angular.module('fpa').controller('ProjetoController',
		function($scope, $location, $state, $stateParams) {

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
			};
			
			

		});