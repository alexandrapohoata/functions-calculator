(function() {
    'use strict';

    angular
        .module('func')
        .factory('createChart', createChartFactory)
        
    createChartFactory.$inject = ['serverApi'];

    /** @ngInject */
    function createChartFactory(serverApi) {

        return {
            chart: chartDataFunc,
            steps: funcSteps,
            applyFormater: applyFormater,
            methods: methods
        }

        // function 

        function methods() {
            return [
                {
                    id: 1,
                    name: 'Euler'
                },
                {
                    id: 2,
                    name: 'Heun'
                },
                {
                    id: 3,
                    name: 'Midpoint'
                },
                {
                    id: 4,
                    name: 'Runge-Kutta'
                }
            ];
        }

        function applyFormater() {
            const formatter = new Intl.NumberFormat('en-US', {
                minimumFractionDigits: 3,      
                maximumFractionDigits: 3,
            });
             
            return formatter;
        }

        function chartDataFunc(func, methodid, xi, xf, h, computeByIterations, iterations) {
            self.points = [];
            var success;
            var chartData = {
                labels: [],
                data: [[]],
                series: ['solution differential equation'],
                colors: ['rgb(255, 99, 132)', 'rgb(255, 99, 132)'],
                datasetOverride: [
                    {
                        // borderColor: 'rgb(255, 99, 132)',
                        // backgroundColor: 'rgb(255, 99, 132)',
                        yAxisID: 'y-axis-1'
                    }
                ],
                options: {
                    legend: {
                        display: true,
                        position: 'top'
                    },
                    responsive: true,
                    // stacked: false,
                    interaction: {
                        mode: 'index',
                        intersect: false,
                    },
                    scales: {
                        yAxes: [
                            {
                                id: 'y-axis-1',
                                type: 'linear',
                                display: true,
                                position: 'left'
                            }
                        ],
                        xAxes: [
                            {
                                display: true,
                                position: 'bottom'
                            }
                        ]
                    },
                }
            };
            serverApi.getPoints(func, methodid, xi, xf, h, computeByIterations, iterations).then(
                function(data) {
                    self.points = data;
                    success = true;
                    angular.forEach(self.points, function(value, key) {
                        chartData.labels.push(value.x);
                        chartData.data[0].push(value.y);
                    });

                    // merge
                    // console.log('get = ' + JSON.stringify(chartData, null, 4));
                },
                function(reason) {
                    success = false;
                    console.log('Failed: `' + reason + '`');
                    alert('Failed: ' + JSON.stringify(reason, null, 4) + '');
                }
            );

            return {
                "chart": chartData,
                "success": success
            }
        }

        function funcSteps(methodid) {
            var steps = [];
            serverApi.getSteps(methodid).then(
                function(data) {
                    steps = data;
                    console.log('steps0 = ' + JSON.stringify(steps, null, 3));
                },
                function(reason) {
                    success = false;
                    console.log('Failed: `' + reason + '`');
                    alert('Failed: ' + JSON.stringify(reason, null, 4) + '');
                }
            );
            return steps;
        }
    }
}());
