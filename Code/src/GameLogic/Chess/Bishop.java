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
        // get the contents of the square at the intended move target
        ESquareContents square = board.AtCoordinate(target);

        // do not allow pawn to capture piece of the same color
        if( ( is_white && ( square == ESquareContents.WHITE_PIECE)) || (!is_white && (square == ESquareContents.BLACK_PIECE)))
        {
            return EMoveResult.PLACE_ON_OWN_PIECE;
        }

        // calculate the change in position
        int x_delta = target.GetX() - position.GetX();
        int y_delta = target.GetY() - position.GetY();

        // a bishop must move diagonally so the x and y magnitudes must be equivalent
        if( (x_delta == y_delta) || (x_delta == -y_delta) )
        {
            // the steps are used when tracing the move of the bishop to its target
            int x_step = 1;
            int y_step = 1;

            if (x_delta < 0)
            {
                x_step = -1;
                // this gets the total number of spaces to move diagonally
                x_delta = -x_delta;
            }

            if (y_delta < 0)
            {
                y_step = -1;
            }
            // loop through every position to check if the move is valid
            ESquareContents content;
            for(int i = 1; i < x_delta; i++)
            {
                // check that there is nothing blocking this piece from moving along this path
                content = board.AtCoordinate(new Coordinate(position.GetX() + i*x_step, position.GetY() + i*y_step));
                // if the path is blocked then do not move the piece
                if(content != ESquareContents.SQUARE_EMPTY)
                {
                    return EMoveResult.OUT_OF_PIECE_RANGE;
                }
            }

            // if there is a piece at the move to position, then capture that piece
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
        boolean result = false;
        result = SubTestForInCheck(1,1);
        result = result || SubTestForInCheck(1, -1);
        result = result || SubTestForInCheck(-1, 1);
        result = result || SubTestForInCheck(-1, -1);
        return result;
    };

    /**
     * this checks to see if there is a king in range in a given direction
     * @param x_step: the x direction
     * @param y_step: the y direction
     * @return : true if there is a king in range in this direction
     */
    private boolean SubTestForInCheck(int x_step, int y_step)
    {
        // the count is the maximum distance that the piece can move before being out of bounds
        int count = 0;
        // the starting x and y coordinates
        int x = position.GetX();
        int y = position.GetY();
        // find the count value
        if(x_step > 0)
        {
            count = 7 - x;
        }
        else
        {
            count = x;
        }

        if((y_step > 0) && ((7 - y) < count))
        {
            count = 7 - y;
        }
        else if((y_step < 0) && (y < count ))
        {
            count = y;
        }

        ESquareContents square;
        EPieceType piece;

        for(int i = 1; i <= count; i++)
        {
            // calculate the coordinate to check
            Coordinate coord = new Coordinate((x + i*x_step), (y + i*y_step));

            // get square type at the coordinate
            square = board.AtCoordinate(coord);
            // empty square so continue
            if(square == ESquareContents.SQUARE_EMPTY)
            {
                continue;
            }

            // if the piece at the square is the opposite color then check if it is a king
            if( (is_white && (square == ESquareContents.BLACK_PIECE)) || (!is_white && (square == ESquareContents.WHITE_PIECE)))
            {
                piece = board.GetPieceType(coord);
                if(piece == EPieceType.KING)
                {
                    System.out.println(String.format("%s could check the king", type.GetDisplayName()));
                    System.out.println(String.format("king found at %d, %d: by %s at %d, %d", coord.GetX(),coord.GetY(), type.GetDisplayName(), position.GetX(), position.GetY() ));
                    return true;
                }
                else
                {
                    // non-king blocking path so stop
                    return false;
                }
            }
            else
            {
                // piece of wrong color blocking path so stop
                return false;
            }
        }

        // did not find a king in range
        return false;
    }
}
