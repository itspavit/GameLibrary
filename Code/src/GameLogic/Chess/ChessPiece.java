package GameLogic.Chess;

/**
 * the chess piece abstract class outlines the functions that every chess piece type must have
 */
public abstract class ChessPiece
{
    // the coordinate of the position of the piece on the board
    Coordinate position;
    // the piece type
    EPieceType type;
    // color of the piece
    boolean is_white;
    // the board this piece is on (needed to check if a piece is in its intended move path)
    ChessBoard board;

    /**
     * initialize a chess piece
     * @param start_pos: the starting position of this piece
     * @param _is_white: the color of this piece
     */
    ChessPiece(Coordinate start_pos, boolean _is_white)
    {
        position = start_pos;
        type = EPieceType.PAWN;
        is_white = _is_white;
    }

    /**
     * checks if this position is a valid position to move to
     * @param target: the targeted position to move to
     * @return : true if this piece can move to this position, false if not
     */
    boolean CanMoveToPosition(Coordinate target)
    {
        return false;
    }


}
