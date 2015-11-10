'use strict';

angular.module('academyApp').controller('WorkshopDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Workshop', 'Student',
        function($scope, $stateParams, $modalInstance, entity, Workshop, Student) {

        $scope.workshop = entity;
        $scope.students = Student.query();
        $scope.load = function(id) {
            Workshop.get({id : id}, function(result) {
                $scope.workshop = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('academyApp:workshopUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.workshop.id != null) {
                Workshop.update($scope.workshop, onSaveFinished);
            } else {
                Workshop.save($scope.workshop, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
