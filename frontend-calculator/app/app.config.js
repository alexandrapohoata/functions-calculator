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

    chartColors.$inject = ['ChartJsProvider'];
    function chartColors(ChartJsProvider) {
        ChartJsProvider.setOptions({ colors : [ '#4D5360', '#00ADF9', '#DCDCDC', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'] });
    }

    angular
        .module('func')
        .config(routeConfiguration)
        .config(chartColors)
        .run(function($rootScope) {
            $rootScope.$watch(function() {
                MathJax.Hub.Queue(["Typeset", MathJax.Hub]);
                return true;
              });
        });
}());
