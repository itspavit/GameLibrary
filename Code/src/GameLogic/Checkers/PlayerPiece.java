package GameLogic.Checkers;

public class PlayerPiece extends  CheckersPiece{
    public PlayerPiece(Coord position) {
        super(position);
    }

    public PlayerPiece(int x, int y) {
        super(x, y);
    }

    @Override
    public Boolean canMove(Coord destination) {
        return null;
    }

    @Override
    public EPieceTypes getType() {
        return EPieceTypes.Player;
    }
}
