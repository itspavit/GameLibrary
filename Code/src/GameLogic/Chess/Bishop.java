package GameLogic.Chess;

public class Bishop extends ChessPiece
{
    Bishop(Coordinate start_pos, boolean isWhite, ChessBoard _board, int id)
    {
        super(start_pos, isWhite, _board, id);
        type = EPieceType.BISHOP;
    }

    @Override
    public EMoveResult AttemptMove(Coordinate target)
    {

    }

    @Override
    public boolean TestForInCheck()
    {
        return false;
    };
}
