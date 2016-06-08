angular.module('fpa').controller('WizardController',
		function($scope, $location, $state, $requestService, $stateParams) {
			if (!!$stateParams.id) {
				$requestService.post($state.$current.name.substr(0, $state.$current.name.indexOf("Cadastro")) + "/wizard/" + $stateParams.id, function(response){
					$scope.wizard = response.data;
					$state.go($state.$current.name+ "." + $scope.wizard.parts[0]);
				});
			}else{
				$requestService.get($state.$current.name.substr(0, $state.$current.name.indexOf("Cadastro")) + "/wizard", function(response){
					$scope.wizard = response.data;
					alert($scope.wizard.parts[0].state);
					$scope.register($scope.wizard);
					$state.go($state.$current.name+ "." + $scope.wizard.parts[0].state);
				});
			}
	
			$scope.register = function(wizard){
				angular.forEach(wizard.parts, function(value, key) {
					$stateProviderRef.state($state.$current.name+ "." +value.state, {
				        url: $state.$current.name+ "." +value.state,
				        templateUrl: 'pages/form.html'
				    })
				});
			}

//			$scope.$on('$viewContentLoaded', function() {
//				$state.go($state.$current.name+ "." + state);
//			});

			// function to change the currentPage
			$scope.onClick = function(state) {
				alert($state.$current.name+ "." + state);
				$state.go($state.$current.name+ "." + state);
			}

			// function to process the form
//			$scope.processForm = function() {
//				$scope.currentPage = $scope.currentPage + 1;
//				$state.go("cadastroProjeto." + $scope.currentPage);
//			};

		});