package GameLogic.Checkers;

public class OpponentPiece extends  CheckersPiece{
    public OpponentPiece(Coord position) {
        super(position);
    }

    public OpponentPiece(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canMove(Coord dest) {

        if ( position.y >= dest.y) {
            System.out.println("normal piece can't move backwards");
            return false;
        }
        return super.canMove(dest);
    }

    @Override
    public EPieceTypes getType() {
        return EPieceTypes.Opponent;
    }
}
