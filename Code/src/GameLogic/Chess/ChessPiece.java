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
    // the unique id of this chess piece
    private int piece_id;

    /**
     * initialize a chess piece
     * @param start_pos: the starting position of this piece
     * @param _is_white: the color of this piece
     */
    public ChessPiece(Coordinate start_pos, boolean _is_white, ChessBoard _board, int id)
    {
        position = start_pos;
        is_white = _is_white;
        board = _board;
        board.PlacePieceAtPos(position, this);
        piece_id = id;
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

    /**
     * places the piece at a specified position
     * @param target: the target position to move the piece to
     */
    public void MovePieceToPosition(Coordinate target)
    {
        position = target;
        board.PlacePieceAtPos(target, this);
    }

    /**
     * tests to see if the current piece could check the king
     * @return : true if this piece could check the king
     */
    public boolean TestForInCheck(){return false;};

    /**
     * gets the id of this piece
     * @return: the id of this piece
     */
    public int GetID()
    {
        return piece_id;
    }


}
