package GameLogic.Checkers;

import java.util.Arrays;
import java.util.Scanner;

public class CheckersGUI {
    private CheckersLogic logic;

    public void drawBoard() {
        //initializing an empty board
        char[][] board = new char[8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {

                if((i+j) % 2 ==0){
                    board[i][j] = ' ';
                }else{
                    board[i][j] = '█';
                }
            }
        }
        // filling in all the pieces on the board
        for (CheckersPiece p : logic.getPieces()) {
            int x = p.getPosition().x;
            int y = p.getPosition().y;

            switch (p.getType()) {
                case Player:
                    board[y][x] = 'x';
                    break;
                case PlayerKing:
                    board[y][x] = 'X';
                    break;
                case Opponent:
                    board[y][x] = 'o';
                    break;
                case OpponentKing:
                    board[y][x] = 'O';
                    break;
            }
        }

        String output = "  0 1 2 3 4 5 6 7 \n";
        for (int x = 0; x < 8; x++) {
            String line = x + "|";
            for (int y = 0; y < 8; y++) {
                line += board[x][y]+" ";
            }
            line+="\n";
            output+=line;
        }
        System.out.println(output);
    }

    public void addLogic(CheckersLogic logic) {
        this.logic =logic;
    }
}
