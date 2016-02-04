package com.backbase.kalah;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Stavroula
 */
public class Kalah {

    public static int[] gameBoard;
    public static int flag;
    public static KalahPlayer playerSouth;
    public static KalahPlayer playerNorth;
    

    /**
     * @return 
     * @throws java.io.IOException
     */
    //public static void main(String[] args) throws IOException {
public static int[] init() throws IOException{
        /**
         *
         */
        playerSouth = new KalahPlayer(1);

        playerNorth = new KalahPlayer(2);
        gameBoard = KalahMap.init();
        Kalah.flag = gameBoard[14];
       
       return gameBoard;
    }

    public static int play(KalahPlayer player, int pit) throws IOException {
        
        System.out.println("your id!!!!!");
        System.out.println(player.id);
        System.out.println("Choose from which selectedPit you want to move your stones");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
       // pit = Integer.parseInt(input);
        KalahGame game = new KalahGame();
        
        Kalah.flag = game.move(pit, player);
        
        return Kalah.flag;
        
    }
    

}
