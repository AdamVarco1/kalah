'use strict';


var kalah = angular.module('kalah');

//This controller is responsible for sending to the back end the player's input 
//and receiving back the updated board of the game
kalah.controller('HomeCtrl', function ($scope, $http, $window) {
    //When the partial is loaded ask the game to be initiated
    $http.get('/kalah/resources/service/kalah')
            .success(function (data) {
                $scope.board = data;
                $scope.msg = "It's your turn South Player!!";
                $scope.pointsNorth = $scope.board[0];
                $scope.pointsSouth = $scope.board[7];
            });
    //Function that is called when a pit is clicked
    $scope.move = function (pit) {
        if ($scope.board[14] === 3)
        {
            return;
        }
        else
        {
            $http.post('/kalah/resources/service/kalahPlay', pit)
                    .success(function (data) {
                        $scope.board = data;
                        if ($scope.board[14] === 1) {
                            $scope.msg = "It's your turn South Player!!";
                        }
                        else if ($scope.board[14] === 2) {
                            $scope.msg = "It's your turn North Player!!";
                        }
                        else
                        {
                            $scope.msg = "GAME OVER!!";
                            $scope.board[0] = $scope.board[0] + $scope.board[8] + $scope.board[9] + $scope.board[10] + $scope.board[11] + $scope.board[12] + $scope.board[13];
                            $scope.board[7] = $scope.board[7] + $scope.board[1] + $scope.board[2] + $scope.board[3] + $scope.board[4] + $scope.board[5] + $scope.board[6];
                            $scope.board[8] = $scope.board[9] = $scope.board[10] = $scope.board[11] = $scope.board[12] = $scope.board[13] = 0;
                            $scope.board[1] = $scope.board[2] = $scope.board[3] = $scope.board[4] = $scope.board[5] = $scope.board[6] = 0;
                            $scope.pointsNorth = $scope.board[0];
                            $scope.pointsSouth = $scope.board[7];
                        }

                        $scope.pointsNorth = $scope.board[0];
                        $scope.pointsSouth = $scope.board[7];
                    });
        }
    };
});
