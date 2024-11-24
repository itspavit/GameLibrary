package GameLogic.Checkers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BasicRules {
    InputManager playerInput = new InputManager();
    NetInputManager netInput = new NetInputManager();
    CheckersGUI checkersGUI = new CheckersGUI();

    @Test
    public void outOfBounds() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        pieces.add(new PlayerPiece(6,6));
        pieces.add(new OpponentPiece(7,5));
        pieces.add(new PlayerKing(7,1));
        pieces.add(new OpponentPiece(7,3));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);

        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        Scanner scanner = new Scanner(System.in);
        String inputText = "";
        String prefix = "p2 ";

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        assertFalse(playerInput.simulateInput("77 86"));
        checkersGUI.drawBoard();
        assertFalse(playerInput.simulateInput("66 84"));
        checkersGUI.drawBoard();
        assertTrue(playerInput.simulateInput("66 55"));
        checkersGUI.drawBoard();
        assertFalse(netInput.simulateInput("73 84"));
        assertTrue(netInput.simulateInput("73 64"));
    }

}
