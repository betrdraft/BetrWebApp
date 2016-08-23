/**
 * Created by James on 2/19/2016.
 */
var betr = angular.module('betr', ['ngScrollbars', 'anguFixedHeaderTable', 'ngRoute', 'ngAnimate', 'ngSanitize']);


betr.config(function($routeProvider) {

    $routeProvider

        .when('/', {
            templateUrl: 'contestHome.html',
            controller: 'contestsController'
        })

        .when('/wallet', {
            templateUrl: 'wallet.html',
            controller: 'walletController'
        })

        .when('/teamManager', {
            templateUrl: 'teamManager.html',
            controller: 'teamManagerController'
        })

        .when('/news', {
            templateUrl: 'news.html',
            controller: 'newsController'
        })

        .when('/howToPlay', {
            templateUrl: 'howToPlay.html',
            controller: 'htpController'
        })

        .when('/fundsManager', {
            templateUrl: 'fundsManager.html',
            controller: 'fundsManagerController'
        })

        .when('/userSettings', {
            templateUrl: 'userSettings.html',
            controller: 'userSettingsController'
        })
    ;



});


betr.controller('fundsManagerController', function ($scope) {
    $scope.pageClass = 'page-fund-manager';

    });

betr.controller('userSettingsController', function ($scope) {
    $scope.pageClass = 'page-user-settings';
});

betr.controller('contestsController', function ($scope, contestService) {
    $scope.pageClass = 'page-contests';

    $scope.lastThreeBets = [{
        gameName: 'NFL 100K Drive',
        amountEntered: 20,
        ending: 5
    }, {
        gameName: 'NFL 100K Drive',
        amountEntered: 20,
        ending: 5
    }, {
        gameName: 'NFL 100K Drive',
        amountEntered: 20,
        ending: 5
    },]

    $scope.contests = [
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5
        },
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5
        },
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5
        },
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5
        },
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5
        }
    ]


    $scope.selectGame = function(index) {
        contestService.setGame($scope.contests[index]);
    };


  /*  var contests;

    var retrieveContests = function() {
        $http.get('/api/retrieve/contests/all').success(function(result, status) {
            $scope.contests = result;
            contests = result;
        });
    };

    retrieveContests();
    */
});


betr.controller('signUpController', function ($scope, $http, $window) {
    $scope.signUp = function () {
        var user = {name: $scope.name, email: $scope.email, password: $scope.password, phoneNumber: $scope.phoneNumber};
        $http.post('/api/create/user', user).success(function (result, status, headers) {
            $window.location.href = 'login.html';
        }).error(function (result, status, headers) {

            }
        );
    }
});

betr.controller('loginController', function ($scope, $http, $window) {
    $scope.login = function () {
        var credentials = { email: $scope.username, password: $scope.password };
        $http.post('/api/login', credentials).success(function (result, status, headers) {
            $scope.authenticated = true;
            $window.sessionStorage.setItem('X-AUTH-TOKEN', headers('X-AUTH-TOKEN'));
            $window.sessionStorage.setItem('username', $scope.username);
            $window.location.href = 'index.html';
        });
    }
});

betr.controller('walletController', function($scope) {
    $scope.pageClass = 'page-wallet';

    $scope.availableBalance = 20000;

    $scope.walletItems = [
            {
            date:'2015/01/01',
            type:'Withdrawal',
            description: 'Description goes here',
            amount: '100000',
            balance: '2000000'
        },
        {
            date:'2015/01/01',
            type:'Withdrawal',
            description: 'Description goes here',
            amount: '100000',
            balance: '2000000'
        },
        {
            date:'2015/01/01',
            type:'Withdrawal',
            description: 'Description goes here',
            amount: '100000',
            balance: '2000000'
        },
        {
            date:'2015/01/01',
            type:'Withdrawal',
            description: 'Description goes here',
            amount: '100000',
            balance: '2000000'
        }]
});

betr.controller('newsController', function($scope) {
    $scope.pageClass = 'page-news';

    $scope.newsItems = [
        {
            headline:'Broncos win the superbowl',
            description:'This is description. This is description. This is description. This is description. This is description. This is description. This is description. This is description. This is description. This is description. '
        },
        {
            headline:'Sherman tears ACL',
            description:'Richard Sherman tears ACL. More text here. More text here. More text here. More text here. More text here. More text here. More text here. More text here. More text here. '
        }
        ];
});

betr.controller('htpController', function($scope) {
    $scope.pageClass = 'page-howToPlay';
});


betr.factory('TokenAuthInterceptor', ['$q','$window', function($q, $window) {
    return {
        request: function(config) {
            var authToken = $window.sessionStorage.getItem('X-AUTH-TOKEN');
            if (authToken) {
                config.headers['X-AUTH-TOKEN'] = authToken;
            }
            return config;
        },
        responseError: function(error) {
            if (error.status === 401 || error.status === 403) {
                $window.sessionStorage.removeItem('X-AUTH-TOKEN');
            }
            return $q.reject(error);
        }
    };
}]).config(function($httpProvider) {
    $httpProvider.interceptors.push('TokenAuthInterceptor');
});


betr.controller('mainController', ['$scope', '$http', '$window','$location', function($scope, $http, $window, $location){
    $scope.username = $window.sessionStorage.getItem('username');

    $scope.redirect = function(url, refresh) {
        if(refresh || $scope.$$phase) {
            $window.location.href = url;
        } else {
            $location.path(url);
            $scope.$apply();
        }
    }


    $scope.logout = function () {
        // Just clear the local storage
        $window.sessionStorage.removeItem('X-AUTH-TOKEN');
        $window.sessionStorage.removeItem('username');
        $scope.authenticated = false;
        $window.location.href = 'login.html';
    }
}]);

betr.controller('viewController', ['$scope', function($scope) {
    $scope.view = "contestHome.html";

    $scope.setView = function(view) {
        $scope.view = view;
    };

    $scope.getView = function() {
        return $scope.view;
    };
}]);

betr.controller('teamManagerController', ['$scope', '$http', 'contestService', function($scope, $http, contestService) {
    $scope.pageClass = 'page-team-manager';
    var game = contestService.getGame();

    $scope.currentSalary = 50000;
    $scope.contestName = game.gameName;
    $scope.selectedPlayers = [];
    $scope.buttons = [];



    $scope.selectPlayer = function(id, salary) {
        var index;
        if((index = $scope.selectedPlayers.indexOf(id)) > -1) {
            $scope.selectedPlayers.splice(index, 1);
            $scope.currentSalary = $scope.currentSalary + salary;
            angular.forEach(players, function (player) {
                if (player.id === id) {
                    player.button = '+';
                }
            })
        } else {
            if ($scope.currentSalary - salary >= 0) {
                $scope.selectedPlayers.push(id);
                $scope.currentSalary = $scope.currentSalary - salary;
                angular.forEach(players, function (player) {
                    if (player.id === id) {
                        player.button = '-';
                    }
                })
            }
        }

    };

    var initialize = function() {
        $http.get('/api/retrieve/players/values/all').success(function(result, status) {
            $scope.players = result;
            //players = result;
        });
    };

    initialize();

}]);

betr.factory('contestService', function() {
    var selectedGame;

    function setGame(game) {
        selectedGame = game;
    }

    function getGame() {
        return selectedGame;
    }

    return {
        getGame: getGame,
        setGame: setGame
    };
});

