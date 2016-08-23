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
		
		.when('/test', {
            templateUrl: 'test1.html',
            controller: 'team'
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
	 $scope.edituserdetail = function(){
    var myEl = angular.element( document.querySelector( '#editprofile' ) );
myEl.toggleClass('edit'); 
  }
});
betr.controller('contestsController', function ($scope, contestService) {
    $scope.pageClass = 'page-contests';

    $scope.lastThreeBets = [{
        gameName: '$250k Sat NFL Dive',
        amountEntered: 20,
        endingd: 5,
        endingh: 6
    }, {
        gameName: '$1 Million SAT NFL Splash',
        amountEntered: 20,
         endingd: 3,
        endingh: 2
    }, {
        gameName: '$60k Sat NFL Squib',
        amountEntered: 10,
         endingd: 5,
        endingh: 6
    },]

    $scope.contests = [
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5,
			tooltip1: true,
			tooltip2: true
        },
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5,
			tooltip1: true,
			tooltip2: true
        },
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5,
			tooltip1: true,
			tooltip2: false
        },
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5,
			tooltip1: true,
			tooltip2: false
        },
        {
            gameName: 'NFL 100K Drive',
            numEntries: 100,
            numMatches: 200,
            buyIn: 10,
            prizePool: 100000,
            deadLine: 5,
			tooltip1: true,
			tooltip2: true
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
    $scope.Class = 'active';
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

betr.controller('team', function($scope) {
    $scope.pageClass = 'page-team';

    $scope.availableBalance = 20000;
 $scope.currentSalary = 50000;
   /*  $scope.games = [
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
        }]; */
	/*	$scope.contestName = game.gameName;
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

    initialize(); */

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
	$scope.Plays = [
        {
            headline:'Adding Fund',
            description:'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.'
        },
        {
            headline:'Building Team',
            description:'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'
        },
        {
            headline:'Placing a Bet',
            description:'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
        },
        {
            headline:'Withdrawal',
            description:'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
        }
        ];
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

    $scope.currentSalary = 2500000;
    $scope.contestName = '$250k Sat NFL Dive';
    $scope.defense = '3/6';
    $scope.Offense = '5/8';
   // $scope.contestName = game.gameName;
    $scope.selectedPlayers = [];
    $scope.buttons = [];
$scope.players = [{
            player:'Elias Mekuria',
            position:'QB',
            FPPG: '13',
            played: '12',
            OPRK: '13th',
            game: 'CIN@NO',
            salary: '7400',
			status:true
        },
       {
            player:'Arjun BAbu',
            position:'QB',
            FPPG: '13',
            played: '12',
            OPRK: '13th',
            game: 'CIN@NO',
            salary: '7400',
			status:true
        },{
            player:'David Rosential',
            position:'QB',
            FPPG: '13',
            played: '12',
            OPRK: '13th',
            game: 'CIN@NO',
            salary: '7400',
			status:false
        },{
            player:'Charles Gooding',
            position:'QB',
            FPPG: '13',
            played: '12',
            OPRK: '13th',
            game: 'CIN@NO',
            salary: '7400',
			status:true
        },{
            player:'Ned Jamieson',
             position:'QB',
            FPPG: '13',
            played: '12',
            OPRK: '13th',
            game: 'CIN@NO',
            salary: '7400',
			status:false
        }];


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

