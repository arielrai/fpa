var fpa = angular.module('fpa', [ 'ui.router', 'ngTasty', 'ngAnimate' ]);

fpa.config(function myAppConfig($stateProvider, $locationProvider, $urlRouterProvider) {

	$locationProvider.hashPrefix('fpa');
//	$urlRouterProvider.otherwise('projeto');

	$stateProvider.state('login', {
		url : '/login',
		templateUrl : 'pages/login.html',
		controller : 'LoginController'
	}).state('cadastroProjeto', {
		url : '/cadastroProjeto',
		templateUrl : 'pages/projetoCadastro.html',
		controller : 'ProjetoController'
	}).state('projetos', {
		url : '/projeto',
		templateUrl : 'pages/table.html',
		controller : 'TableCtrl'
	}).state('cadastroProjeto.one', {
        url: '',
        templateUrl: 'pages/wizard/wizard-1.html'
    }).state('cadastroProjeto.two', {
        url: '',
        templateUrl: 'pages/wizard/wizard-2.html'
    }).state('cadastroProjeto.three', {
        url: '',
        templateUrl: 'pages/wizard/wizard-3.html'
    });

	$stateProviderRef = $stateProvider;
});
