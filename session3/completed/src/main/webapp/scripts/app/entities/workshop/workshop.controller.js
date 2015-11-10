'use strict';

angular.module('academyApp')
    .controller('WorkshopController', function ($scope, Workshop, $resource) {
        $scope.workshops = [];
        $scope.loadAll = function() {
            Workshop.query(function(result) {
               $scope.workshops = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Workshop.get({id: id}, function(result) {
                $scope.workshop = result;
                $('#deleteWorkshopConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Workshop.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteWorkshopConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.workshop = {
                title: null,
                groupMin: null,
                groupMax: null,
                startDate: null,
                id: null
            }
        };
    });
