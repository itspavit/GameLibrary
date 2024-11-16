package GameLogic.Checkers;

public class PlayerKing extends  CheckersPiece{
    public PlayerKing(Coord position) {
        super(position);
    }

    public PlayerKing(int x, int y) {
        super(x, y);
    }

    @Override
    public Boolean canMove(Coord destination) {
        return null;
    }

    @Override
    public EPieceTypes getType() {
        return EPieceTypes.PlayerKing;
    }
}
