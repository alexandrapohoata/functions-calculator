(function() {
    'use strict';

    angular
        .module('func')
        .factory('createChart', createChartFactory)
        
    createChartFactory.$inject = ['serverApi']

    /** @ngInject */
    function createChartFactory(serverApi) {

        return {
            chart: chartDataFunc
        }

        function chartDataFunc() {
            self.points = [];

            var chartData = {
                labels: [],
                data: [[]],
                datasetOverride: [
                    {
                        yAxisID: 'y-axis-1'
                    }
                ],
                options: {
                    scales: {
                        yAxes: [
                            {
                                id: 'y-axis-1',
                                type: 'linear',
                                display: true,
                                position: 'left'
                            }
                        ]
                    }
                }
            };

            serverApi.getPoints().then(
                (response) => {
                    self.points = response.data;
                    angular.forEach(self.points, function(value, key) {
                        chartData.labels.push(value.x);
                        chartData.data[0].push(value.y);
                    });
                    console.log(chartData);
                },
                (error) => {
                    self.points = error.statusText;
                }
            );

            return chartData;
        }
    }
}());
