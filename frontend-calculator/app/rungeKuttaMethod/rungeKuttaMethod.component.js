(function() {
    'use strict';

    angular
        .module('func')
        .component('rungeKuttaMethod', component());

    function component() {
        function rungeKuttaMethodController() {
            var self = this;
            
            self.$onInit = () => {
            }
        }

        return {
            bindings: {
                steps: '<',
                showSteps: '<'
            },
            controller: rungeKuttaMethodController,
            controllerAs: 'rungeKuttaMethodCtrl',
            templateUrl: 'rungeKuttaMethod/rungeKuttaMethod.html'
        }
    }
}());
