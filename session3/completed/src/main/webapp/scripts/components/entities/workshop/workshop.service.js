'use strict';

angular.module('academyApp')
    .factory('Workshop', function ($resource, DateUtils) {
        return $resource('api/workshops/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.startDate = DateUtils.convertLocaleDateFromServer(data.startDate);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.startDate = DateUtils.convertLocaleDateToServer(data.startDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.startDate = DateUtils.convertLocaleDateToServer(data.startDate);
                    return angular.toJson(data);
                }
            }
        });
    });


angular.module('academyApp')
    .factory('WorkshopSearch', function ($resource) {
        return $resource('api/workshops/search/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });