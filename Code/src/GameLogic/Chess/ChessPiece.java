package GameLogic.Chess;

/**
 * the chess piece abstract class outlines the functions that every chess piece type must have
 */
public abstract class ChessPiece
{
    // the coordinate of the position of the piece on the board
    protected Coordinate position;
    // the piece type
    protected EPieceType type;
    // color of the piece
    protected boolean is_white;
    // the board this piece is on (needed to check if a piece is in its intended move path)
    protected ChessBoard board;

    /**
     * initialize a chess piece
     * @param start_pos: the starting position of this piece
     * @param _is_white: the color of this piece
     */
    public ChessPiece(Coordinate start_pos, boolean _is_white, ChessBoard _board)
    {
        position = start_pos;
        is_white = _is_white;
        board = _board;
        board.PlacePieceAtPos(position, this);
    }

    /**
     * checks if this position is a valid position to move to
     * @param target: the targeted position to move to
     * @return : true if this piece can move to this position, false if not
     */
    public boolean CanMoveToPosition(Coordinate target)
    {
        return false;
    }


}
