(function() {
    'use strict';

    angular
        .module('func')
        .component('heunMethod', component());

    function component() {
        function heunMethodController() {
            var self = this;
            
            self.$onInit = () => {
            }
        }

        return {
            bindings: {
                steps: '<',
                showSteps: '<'
            },
            controller: heunMethodController,
            controllerAs: 'heunMethodCtrl',
            templateUrl: 'heunMethod/heunMethod.html'
        }
    }
}());
