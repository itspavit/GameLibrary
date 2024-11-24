package GameLogic.Chess;

public class Knight extends ChessPiece
{
    Knight(Coordinate start_pos, boolean isWhite, ChessBoard _board, int id)
    {
        super(start_pos, isWhite, _board, id);
        type = EPieceType.KNIGHT;
    }

    @Override
    public EMoveResult AttemptMove(Coordinate target)
    {
        // get the content of the square
        ESquareContents square = board.AtCoordinate(target);

        // ensure that we do not try to capture a piece of the same color
        if( (is_white && (square == ESquareContents.WHITE_PIECE)) || (!is_white && (square == ESquareContents.BLACK_PIECE)))
        {
            return EMoveResult.PLACE_ON_OWN_PIECE;
        }

        // the delta from the target position
        int x_delta = target.GetX() - position.GetX();
        int y_delta = target.GetY() - position.GetY();

        // valid move deltas
        // x y
        // 2 1
        // 1 2
        // 2-1
        // 1-2
        //-1-2
        //-2-1
        //-2 1
        //-1 2

        // look at this programming war crime
        if( (x_delta == 2 && y_delta == 1) ||(x_delta == -1 && y_delta == -2)||
                (x_delta == 1 && y_delta == 2)||(x_delta == -2 && y_delta == -1)||
                (x_delta == 2 && y_delta == -1)||(x_delta == -2 && y_delta == 1)||
                (x_delta == 1 && y_delta == -2)||(x_delta == -1 && y_delta == 2))
        {
            // if there is a piece at this position then capture it
            if(square != ESquareContents.SQUARE_EMPTY)
            {
                board.CapturePiece(target);
            }
            MovePieceToPosition(target);
            return EMoveResult.VALID_MOVE;
        }

        return EMoveResult.OUT_OF_PIECE_RANGE;
    }

    @Override
    public boolean TestForInCheck()
    {
        // what must the square contain for it to be a king on the opposite team
        ESquareContents needed_content;
        if(is_white)
        {
            needed_content = ESquareContents.BLACK_PIECE;
        }
        else
        {
            needed_content = ESquareContents.WHITE_PIECE;
        }

        // get the x and y components of the position
        int x_start = position.GetX();
        int y_start = position.GetY();

        // check each possible position that a knight is allowed to move to
        boolean result = SubTestForInCheck(x_start + 2, y_start + 1, needed_content);
        result = result || SubTestForInCheck(x_start + 1, y_start + 2, needed_content);
        result = result || SubTestForInCheck(x_start + 2, y_start - 1, needed_content);
        result = result || SubTestForInCheck(x_start + 1, y_start - 2, needed_content);
        result = result || SubTestForInCheck(x_start -1, y_start - 2, needed_content);
        result = result || SubTestForInCheck(x_start -2, y_start -1, needed_content);
        result = result || SubTestForInCheck(x_start - 2, y_start + 1, needed_content);
        result = result || SubTestForInCheck(x_start -1, y_start + 2, needed_content);

        return result;
    };

    /**
     * tests to see if there is a king of opposite color at the given position
     * @param x: the x component of the position
     * @param y; the y component of the position
     * @param square: the contents of the square required
     * @return: true if the position contains a square of the opposite color
     */
    private boolean SubTestForInCheck(int x, int y, ESquareContents square)
    {
        if((x > 7) || (x < 0) || (y > 7) || (y < 0))
        {
            return false;
        }
        Coordinate pos = new Coordinate(x, y);

        if((board.AtCoordinate(pos) == square) && (board.GetPieceType(pos) == EPieceType.KING))
        {
            System.out.println(String.format("%s could check the king", type.GetDisplayName()));
            return true;
        }

        return false;
    }
}
