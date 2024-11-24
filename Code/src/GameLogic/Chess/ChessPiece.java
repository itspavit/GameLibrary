package GameLogic.Chess;

/**
 * the chess piece abstract class outlines the functions that every chess piece type must have
 */
public abstract class ChessPiece
{
    // the coordinate of the position of the piece on the board
    protected Coordinate position;
    // the piece type
    protected EPieceType type = EPieceType.NOTHING;
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
     * attempts to move the piece to the given location
     * @param target: the targeted position to move to
     * @return : the result of the move attempt
     */
    public EMoveResult AttemptMove(Coordinate target)
    {
        return EMoveResult.OUT_OF_PIECE_RANGE;
    }

    /**
     * places the piece at a specified position
     * @param target: the target position to move the piece to
     */
    public void MovePieceToPosition(Coordinate target)
    {
        // clear old position
        board.ClearSquare(position);
        // update position
        position = target;
        // place the piece in this position on the board
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

    /**
     * is this piece a white piece
     * @return : true if this piece is white
     */
    public boolean IsWhite()
    {
        return is_white;
    }

    /**
     * get the position of this piece
     * @return : the position of this piece
     */
    public Coordinate GetPosition()
    {
        return position;
    }

    public EPieceType GetPieceType()
    {
        return type;
    }


}
