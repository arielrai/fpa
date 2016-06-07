var fpa = angular.module('fpa', [ 'ui.router', 'ngTasty', 'ngAnimate', 'ui.bootstrap','dialogs.main', 'formly', 'ngMask', 'formlyBootstrap']);

fpa.config(function myAppConfig($stateProvider, $locationProvider, $urlRouterProvider) {

	$locationProvider.hashPrefix('fpa');
//	$urlRouterProvider.otherwise('projeto');

	$stateProvider.state('login', {
		url : '/login',
		templateUrl : 'pages/login.html',
		controller : 'LoginController'
	}).state('projetosCadastro', {
		url : '/projetoCadastro/:id?',
		templateUrl : 'pages/projetoCadastro.html',
		controller : 'ProjetoController'
	}).state('projetos', {
		url : '/projeto',
		templateUrl : 'pages/table.html',
		controller : 'TableCtrl'
    });

	$stateProviderRef = $stateProvider;
});
