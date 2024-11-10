package GameLogic.Chess;

public class Queen extends ChessPiece
{
    Queen(Coordinate start_pos, boolean isWhite, ChessBoard _board, int id)
    {
        super(start_pos, isWhite, _board, id);
        type = EPieceType.ROOK;
    }
}
