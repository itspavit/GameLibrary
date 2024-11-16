import GameLogic.Checkers.CheckersGUI;
import GameLogic.Checkers.CheckersGame;
import GameLogic.Checkers.InputManager;
import GameLogic.Checkers.NetInputManager;

public class CheckersTest {
    public static void main(String[] args) {
        CheckersGame game = new CheckersGame(new InputManager(),new NetInputManager(), new CheckersGUI());

    }
}
