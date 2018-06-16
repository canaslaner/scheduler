var app = angular.module("scheduler", ["ui.router", 'ui.bootstrap']);

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: '/view/home/home.html',
            controller: 'homeController'
        });
});

app.service('ErrorMessageHandler', function () {
    this.handle = function (error) {
        if (error && error.data) {
            return error.data.status + ' - ' + error.data.error + ' - ' + error.data.message;
        }
        else {
            return 'An error occured! >>> ' + JSON.stringify(error);
        }
    }
});