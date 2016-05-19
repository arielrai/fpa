
angular.module('brfurb').controller('NewProjetoController', function ($scope, $location, locationParser, flash, ProjetoResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.projeto = $scope.projeto || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The projeto was created successfully.'});
            $location.path('/Projetos');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        ProjetoResource.save($scope.projeto, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Projetos");
    };
});