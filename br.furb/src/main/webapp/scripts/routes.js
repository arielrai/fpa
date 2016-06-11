var fpa = angular.module('fpa', [ 'ui.router', 'ngTasty', 'ngAnimate', 'ui.bootstrap','dialogs.main', 'formly', 'ui.mask', 'formlyBootstrap']);

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
	}).state('funcoesCadastro', {
		url : '/funcaoCadastro/:id?',
		templateUrl : 'pages/funcaoCadastro.html',
		controller : 'FuncaoController'
	}).state('funcoes', {
		url : '/funcao/:id?',
		templateUrl : 'pages/table.html',
		controller : 'FuncaoTableCtrl'
    });

	$stateProviderRef = $stateProvider;
});
