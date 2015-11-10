'use strict';

angular.module('academyApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


