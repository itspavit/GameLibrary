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
    public void testJump() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        pieces.add(new PlayerPiece(4,4));
        pieces.add(new OpponentPiece(3,3));
        pieces.add(new OpponentPiece(0,0));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);

        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        Scanner scanner = new Scanner(System.in);
        String inputText = "";
        String prefix = "p2 ";

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        assertFalse(playerInput.simulateInput("77 66"));
        checkersGUI.drawBoard();
        assertTrue(playerInput.simulateInput("44 22"));
        checkersGUI.drawBoard();
    }

}
