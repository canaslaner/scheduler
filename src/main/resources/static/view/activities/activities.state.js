app.config(function ($stateProvider) {
    $stateProvider
        .state('activities', {
            url: '/activities',
            templateUrl: "/view/activities/activities.html",
            controller: "activitiesController"
        })
        .state('activities.create', {
            url: '/create',
            params: {event: null, onSuccess: null},
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: '/view/activities/activities.create.html',
                    controller: 'activitiesCreateUpdateController',
                    size: 'md',
                    resolve: {
                        item: {id: null, name: null, duration: null, summary: null,
                            isNetworking: null, eventId: $stateParams.event.id}
                    }
                }).result.then(function () {
                    if (typeof $stateParams.onSuccess === 'function') {
                        $stateParams.onSuccess();
                    }

                    $state.go('^');
                }, function () {
                    $state.go('^');
                });
            }]
        })
        .state('activities.update', {
            url: '/update/:id',
            params: {activity: null, onSuccess: null},
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: '/view/activities/activities.create.html',
                    controller: 'activitiesCreateUpdateController',
                    size: 'md',
                    resolve: {
                        item: $stateParams.activity
                    }
                }).result.then(function () {
                    if (typeof $stateParams.onSuccess === 'function') {
                        $stateParams.onSuccess();
                    }
                    $state.go('^');
                }, function () {
                    $state.go('^');
                });
            }]
        })
        .state('activities.delete', {
            url: '/delete/:id',
            params: {activity: null, onSuccess: null},
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: '/view/activities/activities.delete.html',
                    controller: 'activitiesDeleteController',
                    size: 'md',
                    resolve: {
                        item: $stateParams.activity
                    }
                }).result.then(function () {
                    if (typeof $stateParams.onSuccess === 'function') {
                        $stateParams.onSuccess();
                    }
                    $state.go('^');
                }, function () {
                    $state.go('^');
                });
            }]
        });
});