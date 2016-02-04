package com.backbase.kalah;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Stavroula
 */
//This class is responsible for starting the game, by initiating the game board, 
//creating the two players and starting a new game
public class Kalah {

    //An array of integers representing the game's board and storing in the last position the game's flag
    public static int[] gameBoard;

    //A flag indicating whose player turn is to play
    //If flag = South Player's Id then it is South player's turn
    //If flag = North Player's Id then it is North player's turn
    public static int flag;

    //Player objects
    public static KalahPlayer playerSouth;
    public static KalahPlayer playerNorth;

    public static int[] init() throws IOException {

        //Initiate the players
        playerSouth = new KalahPlayer(1);

        playerNorth = new KalahPlayer(2);

        //Initiate the game's board
        gameBoard = KalahMap.init();

        //Set game's flag to South player's turn
        Kalah.flag = gameBoard[14];

        return gameBoard;
    }

    public static int play(KalahPlayer player, int pit) throws IOException {

        System.out.println("player's id");
        System.out.println(player.id);

        //Start a new round of the game
        KalahGame game = new KalahGame();

        //Call this method for making a move for the player whose turn is and starting from the pit he chose
        //Return the flag indicating who is playing next
        Kalah.flag = game.move(pit, player);

        return Kalah.flag;

    }

}
