'use strict';

angular.module('academyApp')
    .controller('StudentDetailController', function ($scope, $rootScope, $stateParams, entity, Student, Workshop) {
        $scope.student = entity;
        $scope.load = function (id) {
            Student.get({id: id}, function(result) {
                $scope.student = result;
            });
        };
        var unsubscribe = $rootScope.$on('academyApp:studentUpdate', function(event, result) {
            $scope.student = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
