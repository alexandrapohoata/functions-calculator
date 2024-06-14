(function() {
    'use strict';

    angular
        .module('func')
        .component('eulerMethod', component());

    function component() {
        function eulerMethodController() {
            var self = this;
            
            self.$onInit = () => {
            }
        }

        return {
            bindings: {
                steps: '<',
                showSteps: '<'
            },
            controller: eulerMethodController,
            controllerAs: 'eulerMethodCtrl',
            templateUrl: 'eulerMethod/eulerMethod.html'
        }
    }
}());
