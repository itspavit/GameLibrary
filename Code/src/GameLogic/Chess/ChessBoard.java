package GameLogic.Chess;
import java.util.ArrayList;
/**
 * the chess board handles the moving of pieces and rules of the game
 */
public class ChessBoard
{
    // the side length of a chess board
    static final int BOARD_SIZE = 8;
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

    /**
     * initialize the chess board
     */
    public ChessBoard()
    {
        // initialize the board array
        board_array = new ChessPiece[8][8];

        // initialize the piece arrays for p1 and p2
        p1_pieces = new ArrayList<>();
        p2_pieces = new ArrayList<>();
        p1_captured_pieces = new ArrayList<>();
        p2_captured_pieces = new ArrayList<>();

        //TODO: place pieces at the appropriate locations on the board and fill the player piece lists
        ChessPiece temp = new Rook(new Coordinate(0,0), true, this);
        
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

}
