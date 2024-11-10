package GameLogic.Chess;

public class King extends ChessPiece
{
    King(Coordinate start_pos, boolean isWhite, ChessBoard _board, int id)
    {
        super(start_pos, isWhite, _board, id);
        type = EPieceType.ROOK;
    }
}
