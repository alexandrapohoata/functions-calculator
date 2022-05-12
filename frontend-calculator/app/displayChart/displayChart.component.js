(function() {
    'use strict';

    angular
        .module('func')
        .component('displayChart', component());

    function component() {
        function displayChartController(createChart) {
            var self = this;
            
            self.$onInit = () => {
                self.chartData = createChart.chart();
                console.log('Chart data used for creating chart: ' + JSON.stringify(self.chartData, null, 2));
            }
        }

        return {
            bindings: {},
            controller: ['createChart', displayChartController],
            controllerAs: 'displayChartCtrl',
            templateUrl: 'displayChart/displayChart.html'
        }
    }
}());
