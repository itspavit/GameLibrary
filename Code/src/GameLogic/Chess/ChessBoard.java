package GameLogic.Chess;
import java.util.ArrayList;
/**
 * the chess board handles the moving of pieces and rules of the game
 */
public class ChessBoard
{
    // the side length of a chess board
    static final int BOARD_SIZE = 8;
    static final int MAX_MOVES = 75; // the number of allowed moves for each player

    // the array of chess pieces that represents the board
    private ChessPiece[][] board_array; // bottom left (left white rook) is the origin, (first (x) coordinate is left to right))
    // the pieces available to player 1
    private ArrayList<ChessPiece> p1_pieces;
    // the pieces available to player 2
    private ArrayList<ChessPiece> p2_pieces;
    // player 1 pieces taken of the board
    private ArrayList<ChessPiece> p1_captured_pieces;
    // player 2 pieces taken of the board
    private ArrayList<ChessPiece> p2_captured_pieces;
    // is player one using the white pieces
    private boolean p1_white = true;
    private int p2_moves = 0;
    private int p1_moves = 0;

    /**
     * initialize the chess board
     * @param _p1_white: should player 1 use the white pieces
     */
    public ChessBoard(boolean _p1_white)
    {
        // is player 1 using white pieces
        p1_white = _p1_white;
        // initialize the board array
        board_array = new ChessPiece[BOARD_SIZE][BOARD_SIZE];

        // initialize all positions to null
        for(int i = 0; i < BOARD_SIZE; i++)
        {
            for(int j = 0; j < BOARD_SIZE; j++)
            {
                board_array[i][j] = null;
            }
        }

        // initialize the piece arrays for p1 and p2
        p1_pieces = new ArrayList<>();
        p2_pieces = new ArrayList<>();
        p1_captured_pieces = new ArrayList<>();
        p2_captured_pieces = new ArrayList<>();

        // place pieces at the appropriate locations on the board and fill the player piece lists
        // the ids are for mapping possible draw scenarios to sums of id numbers, trust me it is much simpler than
        // checking complex conditionals for remaining pieces
        // player 1 pieces
        ChessPiece temp = new Rook(new Coordinate(0,0), _p1_white, this, -1000);
        p1_pieces.add(temp);
        temp = new Knight(new Coordinate(1, 0),_p1_white, this , 2);
        p1_pieces.add(temp);
        temp = new Bishop(new Coordinate(2, 0),_p1_white, this, 4 );
        p1_pieces.add(temp);
        temp = new King(new Coordinate(3, 0),_p1_white, this, 8 );
        p1_pieces.add(temp);
        temp = new Queen(new Coordinate(4, 0),_p1_white, this, -1000 );
        p1_pieces.add(temp);
        temp = new Bishop(new Coordinate(5, 0),_p1_white, this, 32 );
        p1_pieces.add(temp);
        temp = new Knight(new Coordinate(6, 0),_p1_white, this, 64 );
        p1_pieces.add(temp);
        temp = new Rook(new Coordinate(7, 0),_p1_white, this, -1000 );
        p1_pieces.add(temp);



        // place player 1 pawns
        for(int i =0; i < BOARD_SIZE; i++)
        {
            temp = new Pawn(new Coordinate(i, 1), _p1_white, this, -1000);
            p1_pieces.add(temp);
        }

        // player 1 pieces
        temp = new Rook(new Coordinate(0,7), !_p1_white, this, -1000);
        p2_pieces.add(temp);
        temp = new Knight(new Coordinate(1, 7),!_p1_white, this, 2 );
        p2_pieces.add(temp);
        temp = new Bishop(new Coordinate(2, 7),!_p1_white, this, 32 );
        p2_pieces.add(temp);
        temp = new King(new Coordinate(3, 7),!_p1_white, this, 8 );
        p2_pieces.add(temp);
        temp = new Queen(new Coordinate(4, 7),!_p1_white, this, -1000 );
        p2_pieces.add(temp);
        temp = new Bishop(new Coordinate(5, 7),!_p1_white, this, 4 );
        p2_pieces.add(temp);
        temp = new Knight(new Coordinate(6, 7),!_p1_white, this, 64 );
        p2_pieces.add(temp);
        temp = new Rook(new Coordinate(7, 7),!_p1_white, this, -1000 );
        p2_pieces.add(temp);

        // place player 1 pawns
        for(int i =0; i < BOARD_SIZE; i++)
        {
            temp = new Pawn(new Coordinate(i, 6), !_p1_white, this, -1000);
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
     * clears the contents of a position
     * @param pos : the position to clear
     */
    public void ClearSquare(Coordinate pos)
    {
        board_array[pos.GetX()][pos.GetY()] = null;
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
     * returns the type of piece at the given target location
     * @param target: the location to inspect
     * @return : the type of piece at that position
     */
    public EPieceType GetPieceType(Coordinate target)
    {
        ChessPiece piece = board_array[target.GetX()][target.GetY()];
        if(piece == null)
        {
            return EPieceType.NOTHING;
        }
        else
        {
            return piece.GetPieceType();
        }
    }

    /**
     * captures this piece
     * @param piece: the location of the piece to capture
     */
    public void CapturePiece(Coordinate piece)
    {
        // get the contents of the cell at the given location
        ChessPiece contents = board_array[piece.GetX()][piece.GetY()];
        // if the contents are null then return
        if(contents == null)
        {
            return;
        }

        // if the piece belongs to player 1 then capture the player 1 piece
        if(p1_pieces.contains(contents))
        {
            p1_pieces.remove(contents);
            p1_captured_pieces.add(contents);
        }
        // otherwise capture the player 2 piece
        else if(p2_pieces.contains(contents))
        {
            p2_pieces.remove(contents);
            p2_captured_pieces.add(contents);
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

        // attempt the move
        EMoveResult result = piece_to_move.AttemptMove(target);

        if((result == EMoveResult.VALID_MOVE) || (result == EMoveResult.PROMOTE_PAWN))
        {
            // increment the move counter
            if(is_p1)
            {
                p1_moves++;
            }
            else
            {
                p2_moves++;
            }
        }

        return result;
    }

    /**
     * promote a pawn
     * @param pos: the location of the pawn to promote
     */
    public void PromotePawn(Coordinate pos, EPieceType type)
    {
        // TODO: implement this
        ChessPiece pawn_to_change = board_array[pos.GetX()][pos.GetY()];

        // ensure that this is not null and a pawn
        if((pawn_to_change == null) || (pawn_to_change.GetPieceType() != EPieceType.PAWN) )
        {
            return;
        }

        // if the pawn is white and at y = 7 then it has traversed the board
        boolean has_traversed_board = (pawn_to_change.GetPosition().GetY() == 7) && (pawn_to_change.IsWhite());
        // if the pawn is black and at y = 0 then it has traversed the board
        has_traversed_board = has_traversed_board && ((pawn_to_change.GetPosition().GetY() == 0) && !pawn_to_change.IsWhite());
        // change the pawn to a queen if it has traversed the board
        if(has_traversed_board)
        {
            // if this is one of player 1 pieces then change in player ones pieces
            if(p1_pieces.contains(pawn_to_change))
            {
                // remove the pawn
                p1_pieces.remove(pawn_to_change);
                // add the new piece type
                if(type == EPieceType.KNIGHT)
                {
                    // add the queen at the position of the pawn
                    p1_pieces.add(new Knight(pos, p1_white, this, 2));
                }
                else
                {
                    // add the queen at the position of the pawn
                    p1_pieces.add(new Queen(pos, p1_white, this, -1000));
                }
            }
            // if this is one of player two's pieces then change in player two's pieces
            else if(p2_pieces.contains(pawn_to_change))
            {
                // remove the pawn
                p2_pieces.remove(pawn_to_change);
                // remove the pawn
                if(type == EPieceType.KNIGHT)
                {
                    // add the queen at the position of the pawn
                    p2_pieces.add(new Knight(pos, !p1_white, this, 2));
                }
                else
                {
                    // add the queen at the position of the pawn
                    p2_pieces.add(new Queen(pos, !p1_white, this, -1000));
                }
            }
        }
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
        if((p1_moves >= MAX_MOVES) && (p2_moves >= MAX_MOVES))
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

    public int GetP1Moves()
    {
        return p1_moves;
    }

    public int GetP2Moves()
    {
        return p2_moves;
    }

    public int GetP1Captures()
    {
        return p2_captured_pieces.size();
    }

    public int GetP2Captures()
    {
        return p1_captured_pieces.size();
    }

    /**
     * returns a formatted version of the board that the gui can use to display the game
     * @return: the formatted board
     */
    public BoardFormatGUI GetFormattedOutput()
    {
        return null;
    }

}
