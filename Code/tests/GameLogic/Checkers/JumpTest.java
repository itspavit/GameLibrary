package GameLogic.Checkers;

import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

import static org.junit.Assert.*;

public class JumpTest {
    InputManager playerInput = new InputManager();
    NetInputManager netInput = new NetInputManager();
    CheckersGUI checkersGUI = new CheckersGUI();

    @Test
    public void basicJump() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        pieces.add(new PlayerPiece(4,4));
        pieces.add(new OpponentPiece(3,3));
        pieces.add(new OpponentPiece(0,0));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        checkersGUI.drawBoard();

        assertTrue(playerInput.simulateInput("44 22"));
        checkersGUI.drawBoard();
        assertFalse(netInput.simulateInput("33 24"));
        assertTrue(netInput.simulateInput("00 11"));
        checkersGUI.drawBoard();
    }
    @Test
    public void forceJump() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(1,3));
        pieces.add(new PlayerPiece(6,4));
        pieces.add(new OpponentPiece(2,4));
        pieces.add(new OpponentPiece(5,3));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        checkersGUI.drawBoard();

        assertFalse(playerInput.simulateInput("13 22"));
        checkersGUI.drawBoard();
        assertTrue(playerInput.simulateInput("64 42"));
        checkersGUI.drawBoard();
        assertTrue(netInput.simulateInput("24 35"));
        checkersGUI.drawBoard();
    }

    @Test
    public void doubleJump() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(4,4));
        pieces.add(new OpponentPiece(3,3));
        pieces.add(new OpponentPiece(3,1));
        pieces.add(new OpponentPiece(0,0));
        pieces.add(new PlayerPiece(3,7));
        pieces.add(new OpponentPiece(2,6));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        checkersGUI.drawBoard();

        assertTrue(playerInput.simulateInput("44 22"));
        assertTrue(playerInput.simulateInput("22 40"));
        assertFalse(netInput.simulateInput("33 44"));
        assertTrue(netInput.simulateInput("00 11"));
        checkersGUI.drawBoard();
    }
}
