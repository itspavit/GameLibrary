package GameLogic.Chess;

public class Pawn extends ChessPiece
{
    Pawn(Coordinate start_pos, boolean isWhite, ChessBoard _board, int id)
    {
        super(start_pos, isWhite, _board, id);
        type = EPieceType.PAWN;
    }

    @Override
    public EMoveResult AttemptMove(Coordinate target)
    {
        // get the content of the square at this coordinate
        ESquareContents square = board.AtCoordinate(target);

        // do not let the player place pieces on top of their own pieces
        if(is_white && (square == ESquareContents.WHITE_PIECE) )
        {
            return EMoveResult.PLACE_ON_OWN_PIECE;
        }
        else if(!is_white && (square == ESquareContents.BLACK_PIECE) )
        {
            return EMoveResult.PLACE_ON_OWN_PIECE;
        }


        // cases for moving one or two moves forward onto empty squares
        if(is_white)
        {
            // cases for moving onto an empty square
            if(square == ESquareContents.SQUARE_EMPTY)
            {
                // pawn moving one space forward into an empty square
                if( (target.GetX() == position.GetX()) && (target.GetY() == (position.GetY()+1)) )
                {
                    MovePieceToPosition(target);

                    // if the pawn traversed the board then it can be promoted
                    if(target.GetY() == 7)
                    {
                        return EMoveResult.PROMOTE_PAWN;
                    }

                    return EMoveResult.VALID_MOVE;
                }

                // pawn moving two spaces forward on first move into empty square
                if((position.GetY() == 1) && (target.GetX() == position.GetX()) && (target.GetY() == 3))
                {
                    MovePieceToPosition(target);
                    return EMoveResult.VALID_MOVE;
                }
            }
        }
        else
        {
            // cases for moving onto an empty square
            if(square == ESquareContents.SQUARE_EMPTY)
            {
                // pawn moving one space forward into an empty square
                if( (target.GetX() == position.GetX()) && (target.GetY() == (position.GetY()-1)) )
                {
                    MovePieceToPosition(target);

                    // if the pawn traversed the board then it can be promoted
                    if(target.GetY() == 0)
                    {
                        return EMoveResult.PROMOTE_PAWN;
                    }

                    return EMoveResult.VALID_MOVE;
                }

                // pawn moving two spaces forward on first move into empty square
                if((position.GetY() == 6) && (target.GetX() == position.GetX()) && (target.GetY() == 4))
                {
                    MovePieceToPosition(target);
                    return EMoveResult.VALID_MOVE;
                }
            }
        }


        // cases for capturing diagonally
        if((target.GetX() == (position.GetX() + 1)) || (target.GetX() == (position.GetX() - 1)))
        {
            // white piece capture black piece
            if(is_white && (square == ESquareContents.BLACK_PIECE) && (target.GetY() == (position.GetY() + 1)))
            {
                // capture the piece
                board.CapturePiece(target);
                // move to the new position
                MovePieceToPosition(target);
                // if the pawn traversed the board then it can be promoted
                if(target.GetY() == 7)
                {
                    return EMoveResult.PROMOTE_PAWN;
                }
                return EMoveResult.VALID_MOVE;
            }
            // black piece capture white piece
            else if(!is_white && (square == ESquareContents.WHITE_PIECE) && (target.GetY() == (position.GetY() - 1)))
            {
                // capture the piece
                board.CapturePiece(target);
                // move to the new position
                MovePieceToPosition(target);

                // if the pawn traversed the board then it can be promoted
                if(target.GetY() == 0)
                {
                    return EMoveResult.PROMOTE_PAWN;
                }
                return EMoveResult.VALID_MOVE;
            }

            // conditions for en passant
            if(square == ESquareContents.SQUARE_EMPTY)
            {
                if(is_white && (target.GetY() == 5) && (position.GetY() == 4))
                {
                    Coordinate enpassant = new Coordinate(target.GetY()-1,target.GetX());
                    if((board.AtCoordinate( enpassant) == ESquareContents.BLACK_PIECE) && (board.GetPieceType(enpassant) == EPieceType.PAWN))
                    {
                        // capture the piece
                        board.CapturePiece(enpassant);
                        // move to the new position
                        MovePieceToPosition(target);
                        return EMoveResult.VALID_MOVE;
                    }
                }
                else if(!is_white && (target.GetY() == 2) && (position.GetY() == 3))
                {
                    Coordinate enpassant = new Coordinate(target.GetY()+1,target.GetX());
                    if((board.AtCoordinate( enpassant) == ESquareContents.WHITE_PIECE) && (board.GetPieceType(enpassant) == EPieceType.PAWN))
                    {
                        // capture the piece
                        board.CapturePiece(enpassant);
                        // move to the new position
                        MovePieceToPosition(target);
                        return EMoveResult.VALID_MOVE;
                    }
                }
            }
        }

        return EMoveResult.OUT_OF_PIECE_RANGE;
    }

    @Override
    public boolean TestForInCheck()
    {
        if(is_white)
        {
            Coordinate pos_to_test = new Coordinate(position.GetX()+1, position.GetY()+1);
            if( (board.AtCoordinate(pos_to_test) == ESquareContents.BLACK_PIECE) && (board.GetPieceType(pos_to_test) == EPieceType.KING))
            {
                return true;
            }
            pos_to_test = new Coordinate(position.GetX()+1, position.GetY()-1);
            if( (board.AtCoordinate(pos_to_test) == ESquareContents.BLACK_PIECE) && (board.GetPieceType(pos_to_test) == EPieceType.KING))
            {
                return true;
            }
        }
        else
        {
            Coordinate pos_to_test = new Coordinate(position.GetX()-1, position.GetY()+1);
            if( (board.AtCoordinate(pos_to_test) == ESquareContents.WHITE_PIECE) && (board.GetPieceType(pos_to_test) == EPieceType.KING))
            {
                return true;
            }
            pos_to_test = new Coordinate(position.GetX()-1, position.GetY()-1);
            if( (board.AtCoordinate(pos_to_test) == ESquareContents.WHITE_PIECE) && (board.GetPieceType(pos_to_test) == EPieceType.KING))
            {
                return true;
            }
        }
        return false;
    };
}
