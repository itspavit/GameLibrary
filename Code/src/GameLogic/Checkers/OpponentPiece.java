package GameLogic.Checkers;

public class OpponentPiece extends  CheckersPiece{
    public OpponentPiece(Coord position) {
        super(position);
    }

    public OpponentPiece(int x, int y) {
        super(x, y);
    }

    @Override
    public Boolean canMove(Coord destination) {
        return null;
    }

    @Override
    public EPieceTypes getType() {
        return EPieceTypes.Opponent;
    }
}
