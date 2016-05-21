var fpa = angular.module('fpa', [ 'ui.router' ]);
fpa.config(

function myAppConfig($stateProvider, $locationProvider, $urlRouterProvider) {

	$locationProvider.hashPrefix('fpa');

	$urlRouterProvider.otherwise('login');

	$stateProvider.state('login', {
		url : '/login',
		templateUrl : 'pages/login.html',
		controller : 'LoginController'
	}).state('home', {
		url : '/home',
		templateUrl : 'pages/home.html',
		controller : 'HomeController'
	});

	$stateProviderRef = $stateProvider;
});
