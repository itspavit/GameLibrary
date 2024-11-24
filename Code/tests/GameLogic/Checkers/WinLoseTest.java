package GameLogic.Checkers;

import org.junit.Test;

import java.util.ArrayList;

public class WinLoseTest {
    InputManager playerInput = new InputManager();
    NetInputManager netInput = new NetInputManager();
    CheckersGUI checkersGUI = new CheckersGUI();

    @Test
    public void win() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        // Todo: win test
    }
    @Test
    public void lose() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        // Todo: lose test
    }
    @Test
    public void rematch() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        // Todo: rematch test
    }
    @Test
    public void forefeit() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        // Todo: forefeit test
    }
    @Test
    public void opponentForefeit() {
        ArrayList<CheckersPiece> pieces = new ArrayList<>();
        pieces.add(new PlayerPiece(7,7));
        CheckersLogic logic = new CheckersLogic(ETurn.PLAYER, pieces);
        CheckersGame game = new CheckersGame(playerInput, netInput, checkersGUI, logic);

        System.out.println("game initialized");
        checkersGUI.drawBoard();

        // Todo: opponent forefeit tests
    }

}
