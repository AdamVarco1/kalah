package com.backbase.kalah;

import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author Stavroula
 */
//This class is responsible for implementing all the game rules
public class KalahGame {

    //Number of stones into the selected pit
    int stones;

    //Total number of pits
    final int TOTAL_NUMBER_OF_PITS = 14;

    //This method is responsile for implementing a player's move
    //It takes for parameters the index of the pit from which the player has chosen to move his stones
    //and the player
    //It returns the game's flag which indicates who is next to play
    public int move(int selectedPit, KalahPlayer player) throws IOException {

        stones = Kalah.gameBoard[selectedPit];

        //If one of the players chooses an opponent's pit, has to choose again
        if (player.id == 1) {
            if (!ArrayUtils.contains(player.AllowedPits, selectedPit) || Kalah.gameBoard[selectedPit] == 0 ) {
                System.out.println("wrong!!!");
                Kalah.flag = 1;
            } //Else make the move
            else {
                moveAround(0, 7, player.AllowedPits, player, selectedPit);
            }
        }

        //If one of the players chooses an opponent's pit, has to choose again
        if (player.id == 2) {
            if (!ArrayUtils.contains(player.AllowedPits, selectedPit) || Kalah.gameBoard[selectedPit] == 0) {
                System.out.println("wrong!!!");

                Kalah.flag = 2;

            } //Else make the move
            else {
                moveAround(7, 0, player.AllowedPits, player, selectedPit);
            }

        }
        return Kalah.flag;
    }

    //This method is responsible for sowing the stones in the appropriate pits
    public void moveAround(int opponentsHouse, int playersHouse, int[] allowedPits, KalahPlayer player, int selectedPit) throws IOException {

        //A value which when is set indicates that the stones will meet the opponent's house in their way 
        //and should skip it
        int skipMode = 0;

        //A value indicating wether the player's last stones arrivs in an empty pit 
        //and should capture opponent's stones
        boolean captureMode;

        captureMode = captureOpponentsStonesCheck(allowedPits, selectedPit);

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

        //Empty player's selected pit
        Kalah.gameBoard[selectedPit] = 0;

        //Capture opponent's stones if needed
        if (captureMode) {

            if (selectedPit + stones < TOTAL_NUMBER_OF_PITS) {
                Kalah.gameBoard[playersHouse] = Kalah.gameBoard[playersHouse] + Kalah.gameBoard[selectedPit + stones] + Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones];
                Kalah.gameBoard[selectedPit + stones] = 0;
                Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones] = 0;
            } else {
                Kalah.gameBoard[playersHouse] = Kalah.gameBoard[playersHouse] + Kalah.gameBoard[selectedPit + stones + 1 - TOTAL_NUMBER_OF_PITS] + Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones - 1 + TOTAL_NUMBER_OF_PITS];
                Kalah.gameBoard[selectedPit + stones + 1 - TOTAL_NUMBER_OF_PITS] = 0;
                Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones - 1 + TOTAL_NUMBER_OF_PITS] = 0;
            }
        }

        System.out.println("The board now is:");
        System.out.println(Arrays.toString(Kalah.gameBoard));

        //Check if the player wins an extra round
        playAgainCheck(playersHouse, player, selectedPit);

        //Check if the game is over
        gameOverCheck(player);
    }

    private void playAgainCheck(int house, KalahPlayer player, int selectedPit) throws IOException {

        if (selectedPit + stones < TOTAL_NUMBER_OF_PITS) {
            if (selectedPit + stones == house) {

                Kalah.flag = player.id;
            } else {
                Kalah.flag = 3 - player.id;
            }
        } else if (selectedPit + stones - TOTAL_NUMBER_OF_PITS == house) {

            Kalah.flag = player.id;
        } else {
            Kalah.flag = 3 - player.id;
        }
    }

    private boolean captureOpponentsStonesCheck(int[] allowedPits, int selectedPit) {

        if (selectedPit + stones < TOTAL_NUMBER_OF_PITS) {
            if (ArrayUtils.contains(allowedPits, selectedPit + stones) && Kalah.gameBoard[selectedPit + stones] == 0 && Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones] > 0) {
                return true;
            } else {
                return false;
            }
        } else if (ArrayUtils.contains(allowedPits, selectedPit + stones + 1 - TOTAL_NUMBER_OF_PITS) && Kalah.gameBoard[selectedPit + stones + 1 - TOTAL_NUMBER_OF_PITS] == 0 && Kalah.gameBoard[TOTAL_NUMBER_OF_PITS - selectedPit - stones - 1 + TOTAL_NUMBER_OF_PITS] > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void gameOverCheck(KalahPlayer player) {

        if (Kalah.gameBoard[player.AllowedPits[0]] == 0 && Kalah.gameBoard[player.AllowedPits[1]] == 0 && Kalah.gameBoard[player.AllowedPits[2]] == 0 && Kalah.gameBoard[player.AllowedPits[3]] == 0 && Kalah.gameBoard[player.AllowedPits[4]] == 0 && Kalah.gameBoard[player.AllowedPits[5]] == 0) {
            Kalah.flag = 3;
        }

    }

}
