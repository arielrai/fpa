'use strict';

angular.module('fpa').run(function(){
	
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
