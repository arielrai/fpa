'use strict';

angular.module('brfurb', [ 'ngRoute', 'ngResource' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$stateProvider.state('contacts', {
				abstract : true,
				url : '/contacts',
				templateUrl : 'contacts.html',
				controller : function($scope) {
					$scope.contacts = [ {
						id : 0,
						name : "Alice"
					}, {
						id : 1,
						name : "Bob"
					} ];
				}
			}).state('contacts.list', {
				url : '/list',
				templateUrl : 'contacts.list.html'
			}).state('contacts.detail', {
				url : '/:id',
				templateUrl : 'contacts.detail.html',
				controller : function($scope, $stateParams) {
					$scope.person = $scope.contacts[$stateParams.id];
				}
			})

			$routeProvider.when('/', {
				templateUrl : 'views/landing.html',
				controller : 'LandingPageController'
			}).when('/Projetos', {
				templateUrl : 'views/Projeto/search.html',
				controller : 'SearchProjetoController'
			}).when('/Projetos/new', {
				templateUrl : 'views/Projeto/detail.html',
				controller : 'NewProjetoController'
			}).when('/Projetos/edit/:ProjetoId', {
				templateUrl : 'views/Projeto/detail.html',
				controller : 'EditProjetoController'
			}).otherwise({
				redirectTo : '/'
			});
		} ]).controller('LandingPageController',
		function LandingPageController() {
		}).controller(
		'NavController',
		function NavController($scope, $location) {
			$scope.matchesRoute = function(route) {
				var path = $location.path();
				return (path === ("/" + route) || path.indexOf("/" + route
						+ "/") == 0);
			};
		});
