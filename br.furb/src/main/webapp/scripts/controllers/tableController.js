angular.module('fpa').controller('TableCtrl', function($scope, $requestService, $state, $requestService) {

	$scope.resource = function(params, paramsObj) {
		return $requestService.get($state.$current.name + "/table", function(response){
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