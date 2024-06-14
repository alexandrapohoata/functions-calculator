(function() {
    'use strict';

    angular
        .module('func')
        .component('displayChart', component());

    function component() {
        function displayChartController(createChart, serverApi, $timeout) {
            var self = this;
            
            self.$onInit = () => {
                // self.chartData = createChart.chart();
                self.stringFunction = '';
                self.showLabels = true;
                self.lowPrecision = false;
                self.formatter = createChart.applyFormater();
                self.methods = createChart.methods();
                self.currentMethod = self.methods[0].id;
                self.lastMethodId = self.currentMethod;
                self.x_initial = undefined;
                self.y_initial = undefined;
                self.h = undefined;
                self.iterations = undefined;
                self.computeByIterations = false;
                self.showSteps = false;
                self.steps = [];
                self.success = false;
                self.loadStepsOneTime = -1;
            }

            self.funcShowLabels = () => {
                self.showLabels = !self.showLabels;
                self.chartData.options['scales']['xAxes'][0]['display'] = self.showLabels;
                console.log('displayChartCtrl.showLabels = ' + self.showLabels);
            }
            
            self.funcComputeFunction = () => {
                if (!self.stringFunction)
                    alert('f(x) is empty');
                else if (!self.stringFunction.includes('x'))
                    alert('x argument is missing from equation');
                else if (!self.x_initial)
                    alert('x is empty');
                else if (!self.y_initial)
                    alert('y is empty');
                else if (!self.h)
                    alert('h is empty');
                else if (self.computeByIterations == true && !self.iterations)
                    alert('iterations is empty');
                else {
                    var result = createChart.chart(self.stringFunction, self.currentMethod, self.x_initial, self.y_initial, self.h, self.computeByIterations, self.iterations);
                    
                    self.chartData = result["chart"];
                    self.success = true;

                    self.actualLabels = self.chartData.labels;
                    self.lastMethodId = self.currentMethod;

                    self.showSteps = false;
                    self.loadStepsOneTime = 1;
                }
            }

            self.funcSetPrecision = () => {
                self.lowPrecision = !self.lowPrecision;

                if (self.lowPrecision) {
                    self.chartData.labels = [];
                    angular.forEach(self.actualLabels, function(value, _key) {
                        self.chartData.labels.push(self.formatter.format(value));
                    });
                } else
                    self.chartData.labels = self.actualLabels;
            }

            self.funcshowSteps = () => {
                self.showSteps = !self.showSteps;

                if (self.loadStepsOneTime == 1) {
                    self.loadStepsOneTime = 2;
                    // get steps from backend
                    serverApi.getSteps(self.currentMethod).then(
                        function(data) {
                            self.steps = data;
                        },
                        function(reason) {
                            console.error('Failed: `' + reason + '`');
                            alert('Failed: ' + JSON.stringify(reason, null, 4) + '');
                        }
                    );
                }
            }
        }

        return {
            bindings: {},
            controller: ['createChart', 'serverApi', '$timeout', displayChartController],
            controllerAs: 'displayChartCtrl',
            templateUrl: 'displayChart/displayChart.html'
        }
    }
}());
