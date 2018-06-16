app.controller("activitiesController", function ($state, $scope, $http, ErrorMessageHandler) {
    $scope.events = [];
    $scope.selectedEvent = null;
    $scope.errorMessage = null;


    $http.get("/api/events")
        .then(function (response) {
                $scope.events = response.data;
            },
            function (error) {
                $scope.errorMessage = ErrorMessageHandler.handle(error);
            });

    $scope.onEventChanged = function () {
        $scope.errorMessage = null;

        if ($scope.selectedEvent) {
            $http.get("/api/activities?event=" + $scope.selectedEvent.id)
                .then(function (response) {
                        $scope.activities = response.data;
                    },
                    function (error) {
                        $scope.errorMessage = ErrorMessageHandler.handle(error);
                    });
        }
    };
});

app.controller("activitiesDeleteController", ['$scope', 'item', '$http', 'ErrorMessageHandler', function ($scope, item, $http, ErrorMessageHandler) {
    $scope.activity = item;
    $scope.errorMessage = null;

    $scope.dismiss = function () {
        $scope.$dismiss();
    };

    $scope.confirm = function () {
        $scope.errorMessage = null;
        $http.delete("/api/activities/" + $scope.activity.id)
            .then(function (response) {
                    $scope.$close(true);
                },
                function (error) {
                    $scope.errorMessage = ErrorMessageHandler.handle(error);
                });
    };
}]);

app.controller("activitiesCreateUpdateController", ['$scope', 'item', '$http', 'ErrorMessageHandler', function ($scope, item, $http, ErrorMessageHandler) {
    $scope.activity = item;
    $scope.isSaving = false;
    $scope.errorMessage = null;

    $scope.durations = [5, 15, 30, 45, 60];

    $scope.onNetworkingFlagChanged = function () {
        if ($scope.activity.isNetworking) {
            $scope.activity.duration = 60;
        }
    };

    $scope.dismiss = function () {
        $scope.$dismiss();
    };

    $scope.confirm = function () {
        $scope.isSaving = true;
        $scope.errorMessage = null;

        if ($scope.activity.id) {
            $http.put("/api/activities", $scope.activity)
                .then(function (response) {
                        $scope.isSaving = false;
                        $scope.$close(true);
                    },
                    function (error) {
                        $scope.isSaving = false;
                        $scope.errorMessage = ErrorMessageHandler.handle(error);
                    });
        }
        else {
            $http.post("/api/activities", $scope.activity)
                .then(function (response) {
                        $scope.isSaving = false;
                        $scope.$close(true);
                    },
                    function (error) {
                        $scope.isSaving = false;
                        $scope.errorMessage = ErrorMessageHandler.handle(error);
                    });
        }
    };
}]);