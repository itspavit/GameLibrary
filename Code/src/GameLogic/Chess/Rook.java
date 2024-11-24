package GameLogic.Chess;

/**
 * the Rook chess piece can move any number of spaces in the x or y direction
 */
public class Rook extends ChessPiece
{
    Rook(Coordinate start_pos, boolean isWhite, ChessBoard _board, int id)
    {
        super(start_pos, isWhite, _board, id);
        type = EPieceType.ROOK;
    }

    @Override
    public EMoveResult AttemptMove(Coordinate target)
    {
        System.out.println(String.format("Rook move: %d, %d", target.GetX(), target.GetY()));
        // get the contents of the square at the intended move target
        ESquareContents square = board.AtCoordinate(target);

        // do not allow capture of piece of the same color
        if(( is_white && ( square == ESquareContents.WHITE_PIECE)) || (!is_white && (square == ESquareContents.BLACK_PIECE)))
        {
            return EMoveResult.PLACE_ON_OWN_PIECE;
        }

        // calculate the change in position
        int x_delta = target.GetX() - position.GetX();
        int y_delta = target.GetY() - position.GetY();

        // a Rook can only move in one direction or the other (pre-condition is that target != position)
        if( (x_delta == 0) || (y_delta == 0) )
        {
            System.out.println(String.format("Rook is moving: dx %d, dy %d", x_delta, y_delta));
            // the steps are used when tracing the movement of the piece to its target position
            int step = 1;

            // if the x delta is zero then move in the y direction
            if(x_delta == 0)
            {
                // if the y delta is negative then move in the negative y direction
                if(y_delta < 0)
                {
                    step = -1;
                    y_delta = -y_delta;
                }

                System.out.println(String.format("move along y: y_delta: %d, x_delta %d", y_delta, x_delta));

                // loop through every position to check if the move is valid
                ESquareContents content;
                for(int i = 1; i < y_delta; i++)
                {
                    // check that there is nothing blocking this piece from moving along this path
                    content = board.AtCoordinate(new Coordinate(position.GetX(), position.GetY() + i*step));
                    // if the path is blocked then do not move the piece
                    if(content != ESquareContents.SQUARE_EMPTY)
                    {
                        return EMoveResult.OUT_OF_PIECE_RANGE;
                    }
                }
            }
            else
            {
                if(x_delta < 0)
                {
                    step = -1;
                    x_delta = -x_delta;
                }
                // loop through every position to check if the move is valid
                ESquareContents content;
                for(int i = 1; i < x_delta; i++)
                {
                    // check that there is nothing blocking this piece from moving along this path
                    content = board.AtCoordinate(new Coordinate(position.GetX() + i*step, position.GetY()));
                    // if the path is blocked then do not move the piece
                    if(content != ESquareContents.SQUARE_EMPTY)
                    {
                        return EMoveResult.OUT_OF_PIECE_RANGE;
                    }
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

        ESquareContents square;
        EPieceType piece;
        int x = position.GetX();
        int y = position.GetY();

        // scan in positive x direction
        for(int i = x + 1; i <= 7; i++)
        {
            // calculate the coordinate to check
            Coordinate coord = new Coordinate(i, y);

            // get square type at the coordinate
            square = board.AtCoordinate(coord);

            // empty square so continue
            if(square == ESquareContents.SQUARE_EMPTY)
            {
                continue;
            }

            // if the piece at the square is the opposite color then check if it is a king
            if(needed_content == square)
            {
                piece = board.GetPieceType(coord);
                if(piece == EPieceType.KING)
                {
                    System.out.println(String.format("%s could check the king", type.GetDisplayName()));
                    return true;
                }
                else
                {
                    // non-king blocking path so stop
                    break;
                }
            }
            else
            {
                // piece of wrong color blocking path so stop
                break;
            }
        }

        // scan in negative x direction
        for(int i = x-1; i >= 0; i--)
        {
            // calculate the coordinate to check
            Coordinate coord = new Coordinate(i, y);

            // get square type at the coordinate
            square = board.AtCoordinate(coord);

            // empty square so continue
            if(square == ESquareContents.SQUARE_EMPTY)
            {
                continue;
            }

            // if the piece at the square is the opposite color then check if it is a king
            if(needed_content == square)
            {
                piece = board.GetPieceType(coord);
                if(piece == EPieceType.KING)
                {
                    System.out.println(String.format("%s could check the king", type.GetDisplayName()));
                    return true;
                }
                else
                {
                    // non-king blocking path so stop
                    break;
                }
            }
            else
            {
                // piece of wrong color blocking path so stop
                break;
            }
        }

        // scan in positive y direction
        for(int i = y+1; i <= 7; i++)
        {
            // calculate the coordinate to check
            Coordinate coord = new Coordinate(x, i);

            // get square type at the coordinate
            square = board.AtCoordinate(coord);

            // empty square so continue
            if(square == ESquareContents.SQUARE_EMPTY)
            {
                continue;
            }

            // if the piece at the square is the opposite color then check if it is a king
            if(needed_content == square)
            {
                piece = board.GetPieceType(coord);
                if(piece == EPieceType.KING)
                {
                    System.out.println(String.format("%s could check the king", type.GetDisplayName()));
                    return true;
                }
                else
                {
                    // non-king blocking path so stop
                    break;
                }
            }
            else
            {
                // piece of wrong color blocking path so stop
                break;
            }
        }

        // scan in negative y direction
        for(int i = y-1; i >= 0; i--)
        {
            // calculate the coordinate to check
            Coordinate coord = new Coordinate(x, i);

            // get square type at the coordinate
            square = board.AtCoordinate(coord);

            // empty square so continue
            if(square == ESquareContents.SQUARE_EMPTY)
            {
                continue;
            }

            // if the piece at the square is the opposite color then check if it is a king
            if(needed_content == square)
            {
                piece = board.GetPieceType(coord);
                if(piece == EPieceType.KING)
                {
                    System.out.println(String.format("%s could check the king", type.GetDisplayName()));
                    return true;
                }
                else
                {
                    // non-king blocking path so stop
                    break;
                }
            }
            else
            {
                // piece of wrong color blocking path so stop
                break;
            }
        }
        return false;
    };
}
