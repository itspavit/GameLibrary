package GameLogic.Checkers;

public class OpponentKing extends  CheckersPiece{


    public OpponentKing(int x, int y) {
        super(x, y);
    }

    public OpponentKing(Coord position) {
        super(position);
    }

    @Override
    public EPieceTypes getType() {
        return EPieceTypes.OpponentKing;
    }
}
