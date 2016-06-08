angular.module('fpa')
.run(
		function(formlyConfig) {
			formlyConfig.setType({
				name: 'maskedInput',
				extends: 'input',
				template: '<input class="form-control" ng-model="model[options.key]"/>',
				defaultOptions: {
					ngModelAttrs: {
						mask: {
							attribute: 'ui-mask'
						},
						maskPlaceholder: {
							attribute: 'ui-mask-placeholder'
						}
					},
					templateOptions: {
						maskPlaceholder: ''
					}
				}
			});
		})
		.controller(function() {
	}).controller('FormCtrl', function($scope, $requestService, $state, $requestService, dialogs, $window, $stateParams) {
		if (!!$stateParams.id) {
			$requestService.post($state.$current.name.substr(0, $state.$current.name.indexOf("Cadastro")) + "/form/" + $stateParams.id, function(response){
				$scope.form = response.data;
			});
		}else{
			$requestService.get($state.$current.name.substr(0, $state.$current.name.indexOf("Cadastro")) + "/form", function(response){
				$scope.form = response.data;
			});
		}
	});