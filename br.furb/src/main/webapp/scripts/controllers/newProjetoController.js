angular.module('fpa').controller('ProjetoController',
		function($scope, $location, $state) {

			$scope.formData = {};
			$scope.currentPage = 'one';

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
				alert('awesome!');
				$scope.formData = event;
			};

		});