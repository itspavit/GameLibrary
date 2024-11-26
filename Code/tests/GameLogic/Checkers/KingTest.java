package GameLogic.Checkers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KingTest {
    InputManager playerInput = new InputManager();
    NetInputManager netInput = new NetInputManager();
    CheckersGUI checkersGUI = new CheckersGUI();

    @Test
    public void kingMovement() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerKing(3,3));
        pieces.add(new OpponentKing(5,5));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("game initialized");
        checkersGUI.drawBoard();
        assertTrue(playerInput.simulateInput("33 22"));
        assertTrue(netInput.simulateInput("55 66"));
        assertTrue(playerInput.simulateInput("22 33"));
        assertTrue(netInput.simulateInput("66 55"));
        assertFalse(playerInput.simulateInput("33 11"));
        assertTrue(playerInput.simulateInput("33 24"));
        assertFalse(netInput.simulateInput("55 33"));
        assertTrue(netInput.simulateInput("55 64"));
        checkersGUI.drawBoard();
    }
    @Test
    public void kingJump() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerKing(3,3));
        pieces.add(new OpponentPiece(4,4));
        pieces.add(new OpponentKing(3,5));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        assertTrue(playerInput.simulateInput("33 55"));
        assertFalse(netInput.simulateInput("44 35"));
        assertTrue(netInput.simulateInput("35 44"));
        assertTrue(playerInput.simulateInput("55 33"));
        checkersGUI.drawBoard();
    }
    @Test
    public void promotion() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(1,1));
        pieces.add(new OpponentPiece(6,6));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        assertTrue(playerInput.simulateInput("11 00"));
        assertTrue(netInput.simulateInput("66 77"));
        assertTrue(playerInput.simulateInput("00 11"));
        assertTrue(netInput.simulateInput("77 66"));

    }

}
