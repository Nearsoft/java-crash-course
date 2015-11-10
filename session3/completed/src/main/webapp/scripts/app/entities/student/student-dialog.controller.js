'use strict';

angular.module('academyApp').controller('StudentDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Student', 'Workshop',
        function($scope, $stateParams, $modalInstance, entity, Student, Workshop) {

        $scope.student = entity;
        $scope.workshops = Workshop.query();
        $scope.load = function(id) {
            Student.get({id : id}, function(result) {
                $scope.student = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('academyApp:studentUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.student.id != null) {
                Student.update($scope.student, onSaveFinished);
            } else {
                Student.save($scope.student, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
