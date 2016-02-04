package com.backbase.kalah;

import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author Stavroula
 */
public class KalahGame {

    //An integer which indicates from which pit the player wants to move his stones 
    //int selectedPit;

    //Number of stones into the selected pit
    int stones;

    

    //Total number of pits
    final int TOTAL_NUMBER_OF_PITS = 14;

    public int move(int selectedPit, KalahPlayer player) throws IOException {

   
        stones = Kalah.gameBoard[selectedPit];

        //If one of the players chooses an opponent's pit, has to choose again
        if (player.id == 1) {
            if (!ArrayUtils.contains(player.AllowedPits, selectedPit)) {
                System.out.println("wrong!!!");
                //Kalah.play(player);
                Kalah.flag = 1;
            } else {
                moveAround(0, 7, player.AllowedPits, player,selectedPit);
            }
        }

        if (player.id == 2) {
            if (!ArrayUtils.contains(player.AllowedPits, selectedPit)) {
                System.out.println("wrong!!!");
                //this.move(player.id);
                //Kalah.play(player);
                Kalah.flag = 2;
            } else {
                moveAround(7, 0, player.AllowedPits, player,selectedPit);
            }

        }
        return Kalah.flag;
    }

    public void moveAround(int opponentsHouse, int playersHouse, int[] allowedPits, KalahPlayer player, int selectedPit) throws IOException {
        int skipMode = 0;
        boolean captureMode;
        captureMode = captureOpponentsStonesCheck(allowedPits,selectedPit);
        //Sow all the stones from the selected pit moving to the right
        for (int i = 1; i < stones + 1; i++) {

            //Keep sowing until you reach the end of the board 
            if (selectedPit + i < TOTAL_NUMBER_OF_PITS) {

                //If a stone meets the opponent's house, skips it and so do the rest of the stones
                if (selectedPit + i == opponentsHouse) {
                    skipMode = 1;
                }

                if (skipMode == 1) {
                    Kalah.gameBoard[selectedPit + i + 1]++;
                } else {
                    Kalah.gameBoard[selectedPit + i]++;
                }

            } //When you reach the end of the board start from the beginning
            else {

                //If a stone meets the opponent's house, skips it and so do the rest of the stones
                if (selectedPit + i - TOTAL_NUMBER_OF_PITS == opponentsHouse) {
                    skipMode = 1;
                }

                if (skipMode == 1) {
                    Kalah.gameBoard[selectedPit + i + 1 - TOTAL_NUMBER_OF_PITS]++;
                } else {
                    Kalah.gameBoard[selectedPit + i - TOTAL_NUMBER_OF_PITS]++;
                }
            }
        }
        Kalah.gameBoard[selectedPit] = 0;
        if (captureMode) {
            Kalah.gameBoard[playersHouse] = Kalah.gameBoard[playersHouse] + Kalah.gameBoard[selectedPit + stones] + Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones];
            Kalah.gameBoard[selectedPit + stones] = 0;
            Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones] = 0;
        }
        System.out.println("The board now is:");
        System.out.println(Arrays.toString(Kalah.gameBoard));
        playAgainCheck(playersHouse, player,selectedPit);
        gameOverCheck(player);
    }

    private void playAgainCheck(int house, KalahPlayer player, int selectedPit) throws IOException {

        if (selectedPit + stones < TOTAL_NUMBER_OF_PITS) {
            if (selectedPit + stones == house) {
                //move(player.id);
                //Kalah.play(player);
                Kalah.flag = player.id;
            }
            else { Kalah.flag = 3 - player.id;}
        } else if (selectedPit + stones - TOTAL_NUMBER_OF_PITS == house) {
            //move(player.id);
            //Kalah.play(player);
            Kalah.flag = player.id;
        }
        else{ Kalah.flag = 3 - player.id; }
    }

    private boolean captureOpponentsStonesCheck(int[] allowedPits, int selectedPit) {
        if (selectedPit + stones < TOTAL_NUMBER_OF_PITS) {
            if (ArrayUtils.contains(allowedPits, selectedPit + stones) && Kalah.gameBoard[selectedPit + stones] == 0 && Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones] > 0) {
                return true;
            } else {
                return false;
            }
        } else if (ArrayUtils.contains(allowedPits, selectedPit + stones - TOTAL_NUMBER_OF_PITS) && Kalah.gameBoard[selectedPit + stones - TOTAL_NUMBER_OF_PITS] == 0 && Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones + TOTAL_NUMBER_OF_PITS] > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void gameOverCheck(KalahPlayer player) {
        
        if (Kalah.gameBoard[player.AllowedPits[0]] == 0 && Kalah.gameBoard[player.AllowedPits[1]] == 0 && Kalah.gameBoard[player.AllowedPits[2]] == 0 && Kalah.gameBoard[player.AllowedPits[3]] == 0 && Kalah.gameBoard[player.AllowedPits[4]] == 0 && Kalah.gameBoard[player.AllowedPits[5]] == 0)
        {
        Kalah.flag = 3;
        }
        
    }

}
