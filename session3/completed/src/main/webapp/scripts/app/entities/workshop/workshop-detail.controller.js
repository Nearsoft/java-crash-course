'use strict';

angular.module('academyApp')
    .controller('WorkshopDetailController', function ($scope, $rootScope, $stateParams, entity, Workshop, Student) {
        $scope.workshop = entity;
        $scope.load = function (id) {
            Workshop.get({id: id}, function(result) {
                $scope.workshop = result;
            });
        };
        var unsubscribe = $rootScope.$on('academyApp:workshopUpdate', function(event, result) {
            $scope.workshop = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
