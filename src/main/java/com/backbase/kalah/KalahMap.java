package com.backbase.kalah;

/**
 *
 * @author Stavroula
 */
public class KalahMap {

    public static int[] init() {

        //Initialize the game's board with sowing 6 stones in each pit 
        int[] board = {0, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, Kalah.playerSouth.id};

        return board;
    }
}
