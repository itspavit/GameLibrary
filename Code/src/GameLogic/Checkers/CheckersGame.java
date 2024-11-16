package GameLogic.Checkers;

public class CheckersGame {
    private CheckersLogic logic;
    private CheckersGUI checkersGUI;
    private InputManager playerInput;
    private NetInputManager opponentInput;

    public CheckersGame(InputManager playerInput, NetInputManager opponentInput, CheckersGUI checkersGUI) {
        logic = new CheckersLogic();
        this.playerInput = playerInput;
        this.opponentInput = opponentInput;
        this.checkersGUI = checkersGUI;
        
        playerInput.addLogic(logic);

        playerInput.connectPlayer();

    }

    public void reset() {
    }

    public void quit() {
    }

    public void sendMessage(String message) {
    }

    public void recieveMessage(String message) {
    }


}