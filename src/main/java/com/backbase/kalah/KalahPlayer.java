/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backbase.kalah;

import java.io.IOException;

/**
 *
 * @author Stavroula
 *
 */
//The class for describing a player
public class KalahPlayer {

    //Player's id
    public int id;

    //Player's points
    public int playerPoints;

    //Allowed pits to choose for the south and the north player respectively
    int AllowedPits[];

    //Costructor of the player
    KalahPlayer(int playerId) {
        this.id = playerId;
        this.AllowedPits = new int[6];
        if (this.id == 1) {

            AllowedPits[0] = 1;
            AllowedPits[1] = 2;
            AllowedPits[2] = 3;
            AllowedPits[3] = 4;
            AllowedPits[4] = 5;
            AllowedPits[5] = 6;

        } else {
            AllowedPits[0] = 8;
            AllowedPits[1] = 9;
            AllowedPits[2] = 10;
            AllowedPits[3] = 11;
            AllowedPits[4] = 12;
            AllowedPits[5] = 13;
        }

    }

    public void points() {

        if (id == 1) {
            this.playerPoints = Kalah.gameBoard[7];
            System.out.println("this.playerPoints");
            System.out.println(this.playerPoints);
        }
        if (id == 2) {
            this.playerPoints = Kalah.gameBoard[0];
            System.out.println("this.playerPoints");
            System.out.println(this.playerPoints);
        }
    }

}
