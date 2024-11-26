import java.util.Scanner;

public class Game {

    private Board board;
    private char currentPlayerSymbol;
    private Scanner scanner;

    public Game() {
        board = new Board();
        currentPlayerSymbol = 'X'; //X always goes first
        scanner = new Scanner(System.in);
    }

    private int getValidMove(String prompt) throws InvalidMoveException {
        System.out.println(prompt);
        int input = scanner.nextInt();

        if (input < 0 || input > 2) {
            throw new InvalidMoveException("Invalid move, must choose a number from 0-2");
        }
        return input;
    }


    public void play() {
        boolean gameOver = false;

        //Main game loop
        while (!gameOver) {
            //display board
            System.out.println("\nCurrent board: ");
            System.out.println(board);

            try {
                //Get Player move
                System.out.println("Player " + currentPlayerSymbol + "'s turn");
                int row = getValidMove("Enter row (0-2): ");
                int col = getValidMove("Enter col (0-2): ");


                //Make the move
                if (board.makeMove(row, col, currentPlayerSymbol)) {
                    //check win
                    if (board.checkWin(currentPlayerSymbol)) {
                        System.out.println(board);
                        System.out.println("Player " + currentPlayerSymbol + " wins!");
                        gameOver = true;
                    }

                    //check draw
                    else if (board.isDraw()) {
                        System.out.println(board);
                        System.out.println("It's a draw!");
                        gameOver = true;
                    }

                    //continue game
                    else {
                        currentPlayerSymbol = (currentPlayerSymbol == 'X' ? 'O' : 'X');
                    }


                } else {
                    System.out.println("Invalid move, position already taken");
                }
            } catch (InvalidMoveException e) {
                System.out.println("Error: " + e.getMessage() + "Please try again.");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error please try again.");
                scanner.next();
            }
        }
        scanner.close();

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

}
