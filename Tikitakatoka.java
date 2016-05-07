/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Administrator
 */
public class Tikitakatoka {

    char[][] board = new char[3][3];
    int currentPlayer;
    int plays;
    String player1;
    String player2;
    char marker1;
    char marker2;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    protected void init() {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //gonna fill with digit from 0 to 10 in board 3x3
                board[i][j] = Character.forDigit(++counter, 10);
            }
        }
        //set player 1 first move
        currentPlayer = 1;
        plays = 0;
    }

    protected String getPrompt() {
        String prompt = "";
        try {
            prompt = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prompt;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public char getMarker1() {
        return marker1;
    }

    public void setMarker1(char marker1) {
        this.marker1 = marker1;
    }

    public char getMarker2() {
        return marker2;
    }

    public void setMarker2(char marker2) {
        this.marker2 = marker2;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    protected int getCurrentPlayer() {
        return currentPlayer;
    }

    protected void setCurrentPLayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    protected String getRules() {
        StringBuilder builder = new StringBuilder();
        builder.append("Players take turns marking a square. Only squares \n");
        builder.append("not already marked can be picked. Once a player has \n");
        builder.append("marked three squares in a row, the player wins! If all squares \n");
        builder.append("are marked and no three squares are the same, a tie game is declared.\n");
        builder.append("Have Fun! \n\n");

        return builder.toString();
    }

    //this method gonna draw board using StringBuilder
    protected String drawBoard() {
        StringBuilder builder = new StringBuilder("Game Board: \n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                builder.append("[" + board[i][j] + "]");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    //this method gonna check the game was ended or not as goal
    /*
     [][x][]  [x][][]  [][][x]
     [][x][]  [][x][]  [][x][]
     [][x][]  [][][x]  [x][][] etc...
     */
    protected boolean winner() {
        //gonna checking rows
        /*
         GOAL check:
         [x][x][x]  [][][]     [][][]
         [][][]     [x][x][x]  [][][]         
         [][][]     [][][]     [x][x][x]
         */
        char current = ' ';
        for (int i = 0; i < 3; i++) {
            int j = 0;
            for (j = 0; j < 3; j++) {
                if (!Character.isLetter(board[i][j])) {
                    break;
                }
                if (j == 0) {
                    current = board[i][j];
                } else if (current != board[i][j]) {
                    break;
                }
                if (j == 2) {
                    //winner found
                    return true;
                }
            }
        }

        //gonna checking columns
        /*
         GOAL check:
         [x][][]  [][x][]  [][][x]
         [x][][]  [][x][]  [][][x]
         [x][][]  [][x][]  [][][x]
         */
        for (int i = 0; i < 3; i++) {
            current = ' ';
            int j = 0;
            for (j = 0; j < 3; j++) {
                if (!Character.isLetter(board[j][i])) {
                    break;
                }
                if (j == 0) {
                    current = board[i][j];
                } else if (current != board[j][i]) {
                    break;
                }
                if (j == 2) {
                    //winner found
                    return true;
                }
            }
        }

        //gonna checking diagonal
        /*
         GOAL check:
         [x][][]
         [][x][]
         [][][x]
         */
        current = board[0][0];
        if (Character.isLetter(current)
                && board[1][1] == current
                && board[2][2] == current) {
            return true;
        }

        /*
         GOAL check:
         [][][x]
         [][x][]
         [x][][]
         */
        current = board[2][0];
        if (Character.isLetter(current)
                && board[1][1] == current
                && board[0][2] == current) {
            return true;
        }
        return false;
    }

    boolean placeMarker(int pick) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Character.forDigit(pick, 10)) {
                    board[i][j] = (this.getCurrentPlayer() == 1) ? this.getMarker1() : this.getMarker2();
                    return true;
                }
            }
        }
        return false;
    }

    void switchPlayer() {
        if (this.getCurrentPlayer() == 1) {
            this.setCurrentPLayer(2);
        } else {
            this.setCurrentPLayer(1);
        }
        this.setPlays(this.getPlays() + 1);
    }
}
