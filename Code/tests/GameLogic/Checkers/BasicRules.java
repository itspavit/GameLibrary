package GameLogic.Checkers;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BasicRules {
    InputManager playerInput = new InputManager();
    NetInputManager netInput = new NetInputManager();
    CheckersGUI checkersGUI = new CheckersGUI();

    @Test
    public void outOfBounds() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7, 7));
        pieces.add(new PlayerPiece(6, 6));
        pieces.add(new OpponentPiece(7, 5));
        pieces.add(new PlayerKing(7, 1));
        pieces.add(new OpponentPiece(7, 3));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);

        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("out of bounds test");
        assertFalse(playerInput.simulateInput("77 86"));
        checkersGUI.drawBoard();
        assertFalse(playerInput.simulateInput("66 84"));
        checkersGUI.drawBoard();
        assertTrue(playerInput.simulateInput("66 55"));
        checkersGUI.drawBoard();
        assertFalse(netInput.simulateInput("73 84"));
        assertTrue(netInput.simulateInput("73 64"));
    }

    @Test
    public void basicMovement() {

        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7, 7));
        pieces.add(new PlayerKing(4, 6));
        pieces.add(new OpponentPiece(0, 0));
        pieces.add(new OpponentKing(7, 1));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);

        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);
        System.out.println("diag movement test");
        checkersGUI.drawBoard();
        // player checks
        assertFalse(playerInput.simulateInput("77 35"));
        assertFalse(playerInput.simulateInput("77 55"));
        assertFalse(playerInput.simulateInput("46 44"));
        assertFalse(playerInput.simulateInput("46 17"));
        assertTrue(playerInput.simulateInput("77 66"));

        // opponent checks
        assertFalse(netInput.simulateInput("00 35"));
        assertFalse(netInput.simulateInput("71 35"));
        assertTrue(netInput.simulateInput("00 11"));
    }
    @Test
    public void turnOrder() {

        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(4, 6));
        pieces.add(new OpponentPiece(3, 1));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);

        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);
        checkersGUI.drawBoard();
        System.out.println("turn order test");

        assertFalse(netInput.simulateInput("31 42"));
        assertTrue(playerInput.simulateInput("46 35"));
        assertFalse(playerInput.simulateInput("35 24"));
        assertTrue(netInput.simulateInput("31 42"));
        checkersGUI.drawBoard();

    }
    @Test
    public void occupiedSpace() {

        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(6, 6));
        pieces.add(new PlayerPiece(5, 5));
        pieces.add(new OpponentPiece(4, 4));
        pieces.add(new OpponentPiece(3, 3));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);

        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);
        checkersGUI.drawBoard();
        System.out.println("occupied space test");
        assertFalse(playerInput.simulateInput("66 55"));
        assertFalse(playerInput.simulateInput("55 44"));
        assertFalse(playerInput.simulateInput("55 33"));
        assertTrue(playerInput.simulateInput("66 75"));
        assertFalse(netInput.simulateInput("33 44"));
        assertFalse(netInput.simulateInput("44 55"));
        checkersGUI.drawBoard();
        assertTrue(netInput.simulateInput("33 24"));
        checkersGUI.drawBoard();

    }
    @Test
    public void ownPieces() {

        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7, 7));
        pieces.add(new OpponentPiece(0, 0));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);

        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);
        checkersGUI.drawBoard();
        System.out.println("own pieces test");
        assertFalse(playerInput.simulateInput("00 11"));
        assertFalse(netInput.simulateInput("77 66"));
        assertTrue(playerInput.simulateInput("77 66"));
        assertFalse(playerInput.simulateInput("00 11"));
        assertFalse(netInput.simulateInput("66 55"));
        checkersGUI.drawBoard();
    }
}
