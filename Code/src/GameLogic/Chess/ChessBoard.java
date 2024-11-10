package GameLogic.Chess;
import java.util.ArrayList;
/**
 * the chess board handles the moving of pieces and rules of the game
 */
public class ChessBoard
{
    // the side length of a chess board
    static final int BOARD_SIZE = 8;
    static final int MAX_MOVES = 150; // the sum total number of moves before the game is an automatic draw

    // the array of chess pieces that represents the board
    ChessPiece[][] board_array; // bottom left (left white rook) is the origin, (first (x) coordinate is left to right))
    // the pieces available to player 1
    ArrayList<ChessPiece> p1_pieces;
    // the pieces available to player 2
    ArrayList<ChessPiece> p2_pieces;
    // player 1 pieces taken of the board
    ArrayList<ChessPiece> p1_captured_pieces;
    // player 2 pieces taken of the board
    ArrayList<ChessPiece> p2_captured_pieces;
    // is player one using the white pieces
    boolean p1_white = true;
    int num_moves = 0;

    /**
     * initialize the chess board
     * @param _p1_white: should player 1 use the white pieces
     */
    public ChessBoard(boolean _p1_white)
    {
        // initialize the board array
        board_array = new ChessPiece[8][8];

        // initialize the piece arrays for p1 and p2
        p1_pieces = new ArrayList<>();
        p2_pieces = new ArrayList<>();
        p1_captured_pieces = new ArrayList<>();
        p2_captured_pieces = new ArrayList<>();

        // place pieces at the appropriate locations on the board and fill the player piece lists
        // the ids are for mapping possible draw scenarios to sums of id numbers, trust me it is much simpler than
        // checking complex conditionals for remaining pieces
        // player 1 pieces
        ChessPiece temp = new Rook(new Coordinate(0,0), true, this, 1);
        p1_pieces.add(temp);
        temp = new Knight(new Coordinate(1, 0),true, this , 2);
        p1_pieces.add(temp);
        temp = new Bishop(new Coordinate(2, 0),true, this, 4 );
        p1_pieces.add(temp);
        temp = new King(new Coordinate(3, 0),true, this, 8 );
        p1_pieces.add(temp);
        temp = new Queen(new Coordinate(4, 0),true, this, 16 );
        p1_pieces.add(temp);
        temp = new Bishop(new Coordinate(5, 0),true, this, 32 );
        p1_pieces.add(temp);
        temp = new Knight(new Coordinate(6, 0),true, this, 64 );
        p1_pieces.add(temp);
        temp = new Rook(new Coordinate(7, 0),true, this, 128 );
        p1_pieces.add(temp);



        // place player 1 pawns
        for(int i =0; i < BOARD_SIZE; i++)
        {
            temp = new Pawn(new Coordinate(i, 1), true, this, -1000);
            p1_pieces.add(temp);
        }

        // player 1 pieces
        temp = new Rook(new Coordinate(0,7), false, this, 1);
        p2_pieces.add(temp);
        temp = new Knight(new Coordinate(1, 7),false, this, 2 );
        p2_pieces.add(temp);
        temp = new Bishop(new Coordinate(2, 7),false, this, 32 );
        p2_pieces.add(temp);
        temp = new King(new Coordinate(3, 7),false, this, 8 );
        p2_pieces.add(temp);
        temp = new Queen(new Coordinate(4, 7),false, this, 16 );
        p2_pieces.add(temp);
        temp = new Bishop(new Coordinate(5, 7),false, this, 4 );
        p2_pieces.add(temp);
        temp = new Knight(new Coordinate(6, 7),false, this, 64 );
        p2_pieces.add(temp);
        temp = new Rook(new Coordinate(7, 7),false, this, 128 );
        p2_pieces.add(temp);

        // place player 1 pawns
        for(int i =0; i < BOARD_SIZE; i++)
        {
            temp = new Pawn(new Coordinate(i, 6), false, this, -1000);
            p2_pieces.add(temp);
        }
    }

    /**
     * places a chess piece at a position
     * @param pos: the position to place the piece at
     * @param piece: piece to place at this position
     */
    public void PlacePieceAtPos(Coordinate pos, ChessPiece piece)
    {
        board_array[pos.GetX()][pos.GetY()] = piece;
    }

    /**
     * get the type of piece at the given coordinate
     * @param coord: the coordinate to check
     * @return: the type of piece in the square
     */
    public ESquareContents AtCoordinate(Coordinate coord)
    {
        // get the piece at that coordinate
        ChessPiece temp = board_array[coord.GetX()][coord.GetY()];

        // if the piece is null return an empty square
        if(temp == null)
        {
            return ESquareContents.SQUARE_EMPTY;
        }

        // as you can see here, this function is a racist, however he comes from a rich family and can afford to pay of any lawsuits that may arise due to this behaviour
        if(temp.is_white)
        {
            // if the piece is white then return white piece
            return ESquareContents.WHITE_PIECE;
        }
        else
        {
            // if the piece is black then return black piece
            return ESquareContents.BLACK_PIECE;
        }
    }

    /**
     * attempts to move a piece from origin to target
     * @param origin : the location of the piece to move
     * @param target : the location to move the piece to
     * @param is_p1 : is this player 1 or player 2
     * @return : result of the move attempt
     */
    public EMoveResult AttemptMove(Coordinate origin, Coordinate target, boolean is_p1)
    {
        // get the content of the cell at origin
        ESquareContents origin_content = AtCoordinate(origin);
        // get the content of the cell at the target location
        ESquareContents target_content = AtCoordinate(target);

        // should a white piece be moved
        boolean move_white = (is_p1 && p1_white) || (!is_p1 && !p1_white);

        // if the square at the origin is empty then there is no piece to move
        if(origin_content == ESquareContents.SQUARE_EMPTY)
        {
            return EMoveResult.NOT_YOUR_PIECE;
        }

        // if the color of the piece at the origin is not the color of the piece that should be moved then return false
        if((move_white && (origin_content == ESquareContents.BLACK_PIECE)) || (!move_white && (origin_content == ESquareContents.WHITE_PIECE)))
        {
            return EMoveResult.NOT_YOUR_PIECE;
        }

        // if the color of the piece at the target cell is the same as the piece being moved then this move is invalid
        if((move_white && (target_content == ESquareContents.WHITE_PIECE)) || (!move_white && (target_content == ESquareContents.BLACK_PIECE)))
        {
            return EMoveResult.PLACE_ON_OWN_PIECE;
        }

        // get the piece to be moved
        ChessPiece piece_to_move = board_array[origin.GetX()][origin.GetY()];
        // if the piece cannot be moved to the target location then the move is invalid
        if(piece_to_move.CanMoveToPosition(target))
        {
            return EMoveResult.OUT_OF_PIECE_RANGE;
        }

        // get the contents of the board at the target location
        ChessPiece target_piece = board_array[target.GetX()][target.GetY()];
        // move the piece to the new position
        piece_to_move.MovePieceToPosition(target);


        /*
         // check to see if this player put themselves in check, if so undo the move
        if(IsInCheck(is_p1))
        {
            // return the piece to its former position
            piece_to_move.MovePieceToPosition(origin);
            // reset the target piece
            board_array[target.GetX()][target.GetY()] = target_piece;

            return EMoveResult.PUT_SELF_IN_CHECK;
        }
         */

        // if the target cell contains a piece of the opposite color then capture that piece
        if(target_content == ESquareContents.WHITE_PIECE || target_content == ESquareContents.BLACK_PIECE)
        {
            // if player 1 is playing then a player 2 piece was captured
            if(is_p1)
            {
                p2_captured_pieces.add(target_piece);
                p2_pieces.remove(target_piece);
            }
            else
            {
                // if player 2 is playing then a player 1 piece was captured
                p1_captured_pieces.add(target_piece);
                p1_pieces.remove(target_piece);
            }
        }
        // increment the move counter
        num_moves++;

        return EMoveResult.VALID_MOVE;
    }

    /**
     * checks for a draw
     * @return
     */
    private EGameState TestForGameOver()
    {
        // the conditions for a draw are:
        // king vs king 8 v 8
        // king and bishop vs king 12 v 8 or 40 v 8
        // king and knight vs king 10 v 8 or 72 v 8
        // king and bishop versus king and bishop with the bishops on the same color 12 v 12 or 40 v 40
        // total number of moves exceeds the limit

        // if the number of moves is more than the max number of moves then return true
        if(num_moves >= MAX_MOVES)
        {
            return EGameState.DRAW;
        }

        // calculate the layout code for the player 1 pieces
        int p1_layout_code = 0;
        for(int i = 0; i < p1_pieces.size(); i++)
        {
            p1_layout_code += p1_pieces.get(i).GetID();
        }

        // calculate the layout code for the player 2 pieces
        int p2_layout_code = 0;
        for(int i = 0; i < p2_pieces.size(); i++)
        {
            p2_layout_code += p2_pieces.get(i).GetID();
        }

        boolean is_draw = false;

        // king v king
        is_draw = p1_layout_code == 8 && p2_layout_code == 8;
        // king and bishop vs king
        is_draw = is_draw && ((p1_layout_code == 12 && p2_layout_code == 8) || ( p2_layout_code == 12 && p1_layout_code == 8));
        is_draw = is_draw && ((p1_layout_code == 40 && p2_layout_code == 8) || ( p2_layout_code == 40 && p1_layout_code == 8));
        is_draw = is_draw && ((p1_layout_code == 10 && p2_layout_code == 8) || ( p2_layout_code == 10 && p1_layout_code == 8));
        is_draw = is_draw && ((p1_layout_code == 72 && p2_layout_code == 8) || ( p2_layout_code == 72 && p1_layout_code == 8));
        is_draw = is_draw && ((p1_layout_code == 40 && p2_layout_code == 40) || ( p2_layout_code == 12 && p1_layout_code == 12));

        if(is_draw)
        {
            return EGameState.DRAW;
        }

        // if player 1 is missing the king then player 2 wins
        if((p1_layout_code & 8) == 0)
        {
            return EGameState.P2_WINS;
        }

        // if player 2 is missing the king then player 1 wins
        if((p2_layout_code & 8) == 0)
        {
            return EGameState.P1_WINS;
        }

        return EGameState.IN_PLAY;
    }

    /**
     * checks to see if one side is in check or not
     * @param is_p1: if true then check if player 1 is in check
     * @return: true if the player is in check
     */
    private boolean IsInCheck(boolean is_p1)
    {
        if(is_p1)
        {
            for(int i = 0; i < p1_pieces.size(); i++)
            {
                if(p1_pieces.get(i).TestForInCheck())
                {
                    return true;
                }
            }
        }
        else
        {
            for(int i = 0; i < p2_pieces.size(); i++)
            {
                if(p2_pieces.get(i).TestForInCheck())
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * gets the status of the game
     * @return : the status of the game
     */
    public EGameState GetGameStatus()
    {
        // test if player 1 is in check
        if(IsInCheck(true))
        {
            return EGameState.P1_IN_CHECK;
        }

        // test if player 2 is in check
        if(IsInCheck(false))
        {
            return EGameState.P2_IN_CHECK;
        }

        // test for game over status
        return TestForGameOver();
    }

}
