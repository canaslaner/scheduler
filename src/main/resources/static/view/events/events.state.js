app.config(function ($stateProvider) {
    $stateProvider
        .state('events', {
            url: '/events',
            templateUrl: "/view/events/events.html",
            controller: "eventsController"
        })
        .state('events.timeline', {
            url: '/timeline/:id',
            params: {event: null},
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: '/view/events/events.timeline.html',
                    controller: 'eventsTimelineController',
                    size: 'md',
                    resolve: {
                        item: $stateParams.event
                    }
                }).result.then(function () {
                    $state.go('events', null, {reload: true});
                }, function () {
                    $state.go('^');
                });
            }]
        })
        .state('events.create', {
            url: '/create',
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: '/view/events/events.create.html',
                    controller: 'eventsCreateUpdateController',
                    size: 'md',
                    resolve: {
                        item: {id: null, name: null, date: null, summary: null}
                    }
                }).result.then(function () {
                    $state.go('events', null, {reload: true});
                }, function () {
                    $state.go('^');
                });
            }]
        })
        .state('events.update', {
            url: '/update/:id',
            params: {event: null},
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: '/view/events/events.create.html',
                    controller: 'eventsCreateUpdateController',
                    size: 'md',
                    resolve: {
                        item: $stateParams.event
                    }
                }).result.then(function () {
                    $state.go('events', null, {reload: true});
                }, function () {
                    $state.go('^');
                });
            }]
        })
        .state('events.delete', {
            url: '/delete/:id',
            params: {event: null},
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: '/view/events/events.delete.html',
                    controller: 'eventsDeleteController',
                    size: 'md',
                    resolve: {
                        item: $stateParams.event
                    }
                }).result.then(function () {
                    $state.go('events', null, {reload: true});
                }, function () {
                    $state.go('^');
                });
            }]
        });
});