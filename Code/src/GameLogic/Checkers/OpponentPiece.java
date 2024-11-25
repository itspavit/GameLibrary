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
    protected boolean canJump() {
        // check all diagonals by default (king jump logic)
        int x = position.x;
        int y = position.y;
        return (canJump( new Coord(x - 2, y + 2)) ||
                canJump( new Coord(x + 2, y + 2))) ;
    }

    @Override
    public EPieceTypes getType() {
        return EPieceTypes.Opponent;
    }
}
