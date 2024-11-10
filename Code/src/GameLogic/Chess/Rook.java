package GameLogic.Chess;

/**
 * the Rook chess piece can move any number of spaces in the x or y direction
 */
public class Rook extends ChessPiece
{
    Rook(Coordinate start_pos, boolean isWhite, ChessBoard _board)
    {
        super(start_pos, isWhite, _board);
        type = EPieceType.ROOK;
    }
}
