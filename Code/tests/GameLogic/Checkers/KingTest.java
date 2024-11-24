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
    public void promotion() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        // Todo: promotion tests
    }

}
