'use strict';

describe('Workshop Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockWorkshop, MockStudent;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockWorkshop = jasmine.createSpy('MockWorkshop');
        MockStudent = jasmine.createSpy('MockStudent');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Workshop': MockWorkshop,
            'Student': MockStudent
        };
        createController = function() {
            $injector.get('$controller')("WorkshopDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'academyApp:workshopUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
