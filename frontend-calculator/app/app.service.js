(function() {
    'use strict';

    doRequestFactory.$inject = ['$http', '$q', 'SERVER_DOMAIN', 'FUNCTION_CODOMAIN', 'FUNCTION_STEPS'];

    /** @ngInject */
    function doRequestFactory($http, $q, SERVER_DOMAIN, FUNCTION_CODOMAIN, FUNCTION_STEPS) {
        return {
            getPoints: getPoints,
            getSteps: getSteps
        }

        function getPoints(func, methodid, xi, xf, h, computeByIterations, iterations) {
            func = encodeURI(func);
            var url = SERVER_DOMAIN + FUNCTION_CODOMAIN + "?func=" + func + "&methodid=" + methodid + "&xi=" + xi + "&xf=" + xf + "&h=" + h;
            if (computeByIterations)
                url += "&iterations=" + iterations;
                
            console.log("req points: " + url);
            
            var deferred = $q.defer();
            $http.get(url).then(
                (response) => {
                    deferred.resolve(response.data);
                },
                (error) => {
                    console.error(JSON.stringify(error));
                    deferred.reject('Can\'t validate/parse the equation. Rewrite it.');
                }
            );
            return deferred.promise;
        }

        function getSteps(methodid) {
            var url = SERVER_DOMAIN + FUNCTION_STEPS + "?methodid=" + methodid;

            console.log("req steps: " + url);
            
            var deferred1 = $q.defer();
            $http.get(url).then(
                (response) => {
                    deferred1.resolve(response.data);
                },
                (error) => {
                    console.error(JSON.stringify(error));
                    deferred1.reject('Can\'t get the steps. Enter an equation, then press Compute button.');
                }
            );
            return deferred1.promise;
        }
    }

    angular
        .module('func')
        .factory('serverApi', doRequestFactory);
}());
