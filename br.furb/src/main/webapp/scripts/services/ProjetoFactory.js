angular.module('fpa').factory('ProjetoResource', function($resource) {
	var resource = $resource('rest/projetos/:ProjetoId', {
		ProjetoId : '@id'
	}, {
		'queryAll' : {
			method : 'GET',
			isArray : true
		},
		'query' : {
			method : 'GET',
			isArray : false
		},
		'update' : {
			method : 'PUT'
		}
	});
	return resource;
});
