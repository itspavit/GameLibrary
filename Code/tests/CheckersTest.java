import GameLogic.Checkers.*;

import java.util.Scanner;

public class CheckersTest {
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
        while (!inputText.equals("exit")) {
            //getting player input from the console for testing
            inputText = scanner.nextLine();
            if (inputText.equals("draw")) {
                checkersGUI.drawBoard();
            }
            if (inputText.startsWith("p1 ")) {
                playerInput.simulateInput(inputText.substring(3));
                System.out.println("player input:"+inputText);
            }
            if (inputText.startsWith("p2 ")) {
                netInput.simulateInput(inputText.substring(3));
                System.out.println("oppoennt input:"+inputText);
            }
        }

    }
}
