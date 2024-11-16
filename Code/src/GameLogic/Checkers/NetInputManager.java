package GameLogic.Checkers;

import java.util.Scanner;

public class NetInputManager {

    private CheckersLogic logic;
    Scanner scanner = new Scanner(System.in);

    public void addLogic(CheckersLogic logic) {
        this.logic = logic;
    }

    public void connectPlayer() {
        System.out.println("oppoenent input connected to console");
        String inputText = "";
        String prefix = "p2 ";

        while (!inputText.equals("exit")) {
            //getting player input from the console fore testing
            inputText = scanner.nextLine();
            if (inputText.startsWith(prefix)) {
                inputText = inputText.substring(prefix.length());
                System.out.println("Opponent input:"+inputText);

            }
        }

    }

}
