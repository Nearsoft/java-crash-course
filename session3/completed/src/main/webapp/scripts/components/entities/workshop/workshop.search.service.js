'use strict';

angular.module('academyApp')
    .factory('WorkshopSearch', function ($resource) {
        return $resource('api/workshops/search/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
