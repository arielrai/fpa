var fpa = angular.module('fpa', [ 'ui.router', 'ngTasty' ]);

fpa.config(function myAppConfig($stateProvider, $locationProvider, $urlRouterProvider) {

	$locationProvider.hashPrefix('fpa');
	$urlRouterProvider.otherwise('projeto');

	$stateProvider.state('login', {
		url : '/login',
		templateUrl : 'pages/login.html',
		controller : 'LoginController'
	}).state('projetos', {
		url : '/projeto',
		templateUrl : 'pages/table.html',
		controller : 'TableCtrl'
	});

	$stateProviderRef = $stateProvider;
});
