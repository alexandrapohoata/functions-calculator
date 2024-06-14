(function() {
    'use strict';

    angular
        .module('func')
        .component('midpointMethod', component());

    function component() {
        function midpointMethodController() {
            var self = this;
            
            self.$onInit = () => {
            }
        }

        return {
            bindings: {
                steps: '<',
                showSteps: '<'
            },
            controller: midpointMethodController,
            controllerAs: 'midpointMethodCtrl',
            templateUrl: 'midpointMethod/midpointMethod.html'
        }
    }
}());
