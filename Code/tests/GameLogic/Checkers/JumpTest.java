package GameLogic.Checkers;

import java.util.Scanner;

public class JumpTest {
    public static void main(String[] args) {
        InputManager playerInput = new InputManager();
        NetInputManager netInput = new NetInputManager();
        CheckersGUI checkersGUI = new CheckersGUI();

        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI);

        Scanner scanner = new Scanner(System.in);
        String inputText = "";
        String prefix = "p2 ";

        System.out.println("game initialized");
        checkersGUI.drawBoard();
        System.out.println("use <p1/p2> <x1y1> <x2y2> to move pieces around the board!");
        System.out.println("ex. 'p1 11 02' tries to move the piece at (1,1) to (0,2) as player 1");

        playerInput.simulateInput("");

        while (!inputText.equals("exit")) {
            //getting player input from the console for testing
            inputText = scanner.nextLine();
            if (inputText.startsWith("p1 ")) {
                playerInput.simulateInput(inputText.substring(3));
            }
            if (inputText.startsWith("p2 ")) {
                netInput.simulateInput(inputText.substring(3));
            }
            checkersGUI.drawBoard();
        }

    }
}
