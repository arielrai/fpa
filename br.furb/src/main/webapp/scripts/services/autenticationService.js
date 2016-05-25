angular.module('fpa').factory('AutenticacaoService', ['$http', '$rootScope', function ($http, $rootScope) {
        var usuario = null;

        return {
            login: function (credencial) {
                $http.post('./api/login', JSON.stringify(credencial)).then(function (response) {
                    usuario = response.data;
                    $rootScope.$broadcast('loginComSucesso', usuario);
                }, function (error) {
                    $rootScope.$broadcast('loginComErro');
                });
            },
            logout: function () {
                usuario = null;
                $rootScope.$broadcast('logoutComSucesso');

            },
            getUsuario: function () {
                return usuario;
            }
        }
    }]);
