(function() {
    'use strict';

    /** @ngInject */
    function routeConfiguration($routeProvider) {
        $routeProvider.
            when('/calculator', {
                template: '<display-chart></display-chart>'
            }).
            otherwise('/calculator');
    }

    angular
        .module('func')
        .config(routeConfiguration)
}());
