package GameLogic.Checkers;

import java.util.Scanner;

public class InputManager {
    private CheckersLogic logic;

    public void addLogic(CheckersLogic logic) {
        this.logic = logic;

    }

    public void simulateInput(String substring) {
        System.out.println("doing stuff "+ substring);
    }
}