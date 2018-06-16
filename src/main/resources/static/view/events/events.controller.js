app.controller("eventsController", function ($scope, $http, ErrorMessageHandler) {
    $scope.errorMessage = null;

    $http.get("/api/events")
        .then(function (response) {
                $scope.events = response.data;
            },
            function (error) {
                $scope.errorMessage = ErrorMessageHandler.handle(error);
            });
});

app.controller("eventsTimelineController", ['$scope', 'item', '$http', 'ErrorMessageHandler', function ($scope, item, $http, ErrorMessageHandler) {
    $scope.event = item;
    $scope.scheduleDtoList = [];
    $scope.errorMessage = null;

    $http.get("/api/schedules/" + $scope.event.id)
        .then(function (response) {
                if (response.data && response.data.length > 0) {
                    $scope.scheduleDtoList = response.data;
                }
                else {
                    $scope.errorMessage = "No data found!";
                }
            },
            function (error) {
                $scope.errorMessage = ErrorMessageHandler.handle(error);
            });

    $scope.dismiss = function () {
        $scope.$close(true);
    };
}]);

app.controller("eventsDeleteController", ['$scope', 'item', '$http', 'ErrorMessageHandler', function ($scope, item, $http, ErrorMessageHandler) {
    $scope.event = item;
    $scope.errorMessage = null;

    $scope.dismiss = function () {
        $scope.$dismiss();
    };

    $scope.confirm = function () {
        $scope.errorMessage = null;
        $http.delete("/api/events/" + $scope.event.id)
            .then(function (response) {
                    $scope.$close(true);
                },
                function (error) {
                    $scope.errorMessage = ErrorMessageHandler.handle(error);
                });
    };
}]);

app.controller("eventsCreateUpdateController", ['$scope', 'item', '$http', 'ErrorMessageHandler', function ($scope, item, $http, ErrorMessageHandler) {
    $scope.event = item;
    $scope.isSaving = false;
    $scope.errorMessage = null;

    if ($scope.event && $scope.event.date) {
        $scope.event.date = new Date($scope.event.date);
    }

    $scope.dismiss = function () {
        $scope.$dismiss();
    };

    $scope.confirm = function () {
        $scope.isSaving = true;
        $scope.errorMessage = null;

        if ($scope.event.id) {
            $http.put("/api/events", $scope.event)
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
            $http.post("/api/events", $scope.event)
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