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

        assertFalse(playerInput.simulateInput("77 66"));
        checkersGUI.drawBoard();
        assertTrue(playerInput.simulateInput("44 22"));
        checkersGUI.drawBoard();
    }

    @Test
    public void kingJump() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        pieces.add(new PlayerPiece(4,4));
        pieces.add(new OpponentPiece(3,3));
        pieces.add(new OpponentPiece(0,0));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        checkersGUI.drawBoard();

        //TODO: king jump test

    }
    @Test
    public void doubleJump() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        pieces.add(new PlayerPiece(4,4));
        pieces.add(new OpponentPiece(3,3));
        pieces.add(new OpponentPiece(0,0));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        checkersGUI.drawBoard();

        //TODO: multijumps test
    }
}
