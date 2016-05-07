/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tictactoe;

/**
 *
 * @author Administrator
 */
public class GameMain {

    public static void main(String args[]) {
        GameMain gameMain = new GameMain();
        gameMain.play();
    }

    private void play() {
        Tikitakatoka game = new Tikitakatoka();

        System.out.println("Welcome, this game is based 2 Player!");

        System.out.print("Enter Player one's name: ");
        game.setPlayer1(game.getPrompt());

        System.out.print("Enter Player two's name: ");
        game.setPlayer2(game.getPrompt());

        boolean markerOK = false;

        while (!markerOK) {
            System.out.print("Select any letter as " + game.getPlayer1() + "'s marker: ");
            String marker = game.getPrompt();

            if (marker.length() == 1
                    && Character.isLetter(marker.toCharArray()[0])) {
                markerOK = true;
                game.setMarker1(marker.toCharArray()[0]);
            } else {
                System.out.println("Invalid Marker try again!");
            }
        }
        markerOK = false;
        while (!markerOK) {
            System.out.print("Selec any letter as " + game.getPlayer2() + "'s marker: ");
            String marker = game.getPrompt();

            if (marker.length() == 1
                    && Character.isLetter(marker.toCharArray()[0])) {
                markerOK = true;
                game.setMarker2(marker.toCharArray()[0]);
            } else {
                System.out.println("Invalid Marker try again!");
            }
        }

        boolean continuePlaying = true;

        while (continuePlaying) {
            game.init();

            System.out.println();
            System.out.println(game.getRules());
            System.out.println();
            System.out.println(game.drawBoard());
            System.out.println();

            String player = null;

            while (!game.winner() && game.getPlays() < 9) {
                /*
                 player = game.getCurrentPlayer();
                 if(game.getCurrentPlayer == 1){                
                 player = game.getPlayer1();
                 }else{
                 player = game.getPlayer2();
                 }
                 */
                player = (game.getCurrentPlayer() == 1) ? game.getPlayer1() : game.getPlayer2();
                boolean validPick = false;
                while (!validPick) {
                    System.out.println("It is " + player + "'s turn. Pick a move! ");
                    String square = game.getPrompt();
                    if (square.length() == 1 && 
                            Character.isDigit(square.toCharArray()[0])) {
                        int pick = 0;
                        try {
                            pick = Integer.parseInt(square);
                        } catch (NumberFormatException e) {
                            //nothing
                        }
                        validPick = game.placeMarker(pick);
                    }
                    if (!validPick) {
                        System.out.println("Square cannot to pick, select another");
                    }
                }
                game.switchPlayer();
                System.out.println();
                System.out.println(game.drawBoard());
                System.out.println();
            }
            if (game.winner()) {
                System.out.println("Game Over -- " + player + " WINS");
            } else {
                System.out.println("Game Over -- DRAW");
            }
            System.out.println();
            System.out.println("Play Again? (Y/N): ");
            String choice = game.getPrompt();

            if (!choice.equalsIgnoreCase("Y")) {
                continuePlaying = false;
            }
        }
    }
}
