package GameLogic.Checkers;

public class PlayerPiece extends  CheckersPiece{
    public PlayerPiece(Coord position) {
        super(position);
    }

    public PlayerPiece(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canMove(Coord dest) {
        if ( position.y < dest.y) {
            System.out.println("normal piece can't move backwards");
            return false;
        }
        return super.canMove(dest);
    }

    @Override
    public EPieceTypes getType() {
        return EPieceTypes.Player;
    }
}
