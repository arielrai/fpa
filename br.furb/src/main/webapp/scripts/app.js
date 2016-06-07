'use strict';

angular.module('fpa').run(
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
			alert("Se quiser ver meu form vai pelo novo do projeto, kisses!");
		}).controller('LandingPageController',
		function LandingPageController() {
	
		});

angular
		.module('fpa')
		.factory(
				'$requestService',
				function($rootScope, $http, $state) {
					var baseUrl = "rest/";
					var methods = {
						get : function(url, doneFunction, errorFunction,
								paramsObj) {
							return $http.get(
									baseUrl.concat(url),
									angular.isDefined(paramsObj) ? angular
											.toJson(paramsObj) : paramsObj)
									.then(function(d) {
										if (doneFunction != null) {
											return doneFunction(d);
										}
									}, function(data, status) {
										if (angular.equals(data.status, 401)) {
											$state.go("login");
										} else {
											if (!!errorFunction) {
												errorFunction(data, data.status);
											}
										}
									}, function(value) {
										// notify?
									});
						},
						post : function(url, doneFunction, errorFunction,
								paramsObj) {
							return $http.post(
									baseUrl.concat(url),
									angular.isDefined(paramsObj) ? angular
											.toJson(paramsObj) : paramsObj)
									.then(function(d) {
										if (!!doneFunction) {
											return doneFunction(d);
										}
									}, function(data) {
										if (angular.equals(data.status, 401)) {
											$state.go("login");
										} else {
											if (!!errorFunction) {
												errorFunction(data, data.status);
											}
										}
									}, function(value) {
										// notify?
									});
						},
						delete : function(url, doneFunction, errorFunction,
								paramsObj) {
							return $http.delete(
									baseUrl.concat(url),
									angular.isDefined(paramsObj) ? angular
											.toJson(paramsObj) : paramsObj)
									.then(function(d) {
										if (!!doneFunction) {
											return doneFunction(d);
										}
									}, function(data) {
										if (angular.equals(data.status, 401)) {
											$state.go("login");
										} else {
											if (!!errorFunction) {
												errorFunction(data, data.status);
											}
										}
									}, function(value) {
										// notify?
									});
						}
					};
					return methods;
				});
