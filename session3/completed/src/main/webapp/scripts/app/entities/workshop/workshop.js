'use strict';

angular.module('academyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workshop', {
                parent: 'entity',
                url: '/workshops',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'academyApp.workshop.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workshop/workshops.html',
                        controller: 'WorkshopController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('workshop');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('workshop.detail', {
                parent: 'entity',
                url: '/workshop/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'academyApp.workshop.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workshop/workshop-detail.html',
                        controller: 'WorkshopDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('workshop');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Workshop', function($stateParams, Workshop) {
                        return Workshop.get({id : $stateParams.id});
                    }]
                }
            })
            .state('workshop.new', {
                parent: 'workshop',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/workshop/workshop-dialog.html',
                        controller: 'WorkshopDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    title: null,
                                    min: null,
                                    max: null,
                                    startDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('workshop', null, { reload: true });
                    }, function() {
                        $state.go('workshop');
                    })
                }]
            })
            .state('workshop.edit', {
                parent: 'workshop',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/workshop/workshop-dialog.html',
                        controller: 'WorkshopDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Workshop', function(Workshop) {
                                return Workshop.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workshop', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
    });
