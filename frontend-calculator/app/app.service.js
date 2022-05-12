(function() {
    'use strict';

    doRequestFactory.$inject = ['$http', 'SERVER_DOMAIN', 'FUNCTION_CODOMAIN'];

    /** @ngInject */
    function doRequestFactory($http, SERVER_DOMAIN, FUNCTION_CODOMAIN) {
        return {
            getPoints: getPoints
        }

        function getPoints() {
            var url = SERVER_DOMAIN + FUNCTION_CODOMAIN;
            console.log("req: " + url);
            return $http.get(url);
        }
    }

    angular
        .module('func')
        .factory('serverApi', doRequestFactory);
}());
