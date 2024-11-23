package GameLogic.Chess;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
public class ChessBoardTests {
    private ChessBoard board;
    private Coordinate coordinate;
    private Coordinate coordinate2;

    @Before
    public void setup(){
        board = new ChessBoard(true);
    }

    @Test
    public void testRook(){
        coordinate = new Coordinate(0, 0);
        assertEquals(EPieceType.ROOK, board.GetPieceType(coordinate));
        coordinate = new Coordinate(0, 7);
        assertEquals(EPieceType.ROOK, board.GetPieceType(coordinate));
        coordinate = new Coordinate(7, 0);
        assertEquals(EPieceType.ROOK, board.GetPieceType(coordinate));
        coordinate = new Coordinate(7, 7);
        assertEquals(EPieceType.ROOK, board.GetPieceType(coordinate));
    }

    @Test
    public void testKnight(){
        coordinate = new Coordinate(1, 0);
        assertEquals(EPieceType.KNIGHT, board.GetPieceType(coordinate));
        coordinate = new Coordinate(1, 7);
        assertEquals(EPieceType.KNIGHT, board.GetPieceType(coordinate));
        coordinate = new Coordinate(6, 0);
        assertEquals(EPieceType.KNIGHT, board.GetPieceType(coordinate));
        coordinate = new Coordinate(6, 7);
        assertEquals(EPieceType.KNIGHT, board.GetPieceType(coordinate));
    }

    @Test
    public void testBishop(){
        coordinate = new Coordinate(2, 0);
        assertEquals(EPieceType.BISHOP, board.GetPieceType(coordinate));
        coordinate = new Coordinate(2, 7);
        assertEquals(EPieceType.BISHOP, board.GetPieceType(coordinate));
        coordinate = new Coordinate(5, 0);
        assertEquals(EPieceType.BISHOP, board.GetPieceType(coordinate));
        coordinate = new Coordinate(5, 7);
        assertEquals(EPieceType.BISHOP, board.GetPieceType(coordinate));
    }

    @Test
    public void testQueen(){
        coordinate = new Coordinate(4, 0);
        assertEquals(EPieceType.QUEEN, board.GetPieceType(coordinate));
        coordinate = new Coordinate(4, 7);
        assertEquals(EPieceType.QUEEN, board.GetPieceType(coordinate));
    }

    @Test
    public void testKing(){
        coordinate = new Coordinate(3, 0);
        assertEquals(EPieceType.KING, board.GetPieceType(coordinate));
        coordinate = new Coordinate(3, 7);
        assertEquals(EPieceType.KING, board.GetPieceType(coordinate));
    }

    @Test
    public void testPawn(){
        for (int i = 0; i<= 7; i++){
            coordinate = new Coordinate(i, 1);
            assertEquals(EPieceType.PAWN, board.GetPieceType(coordinate));
            coordinate = new Coordinate(i, 6);
            assertEquals(EPieceType.PAWN, board.GetPieceType(coordinate));
        }
    }

    @Test
    public void testNull(){
        for (int i = 0; i<= 7; i++){
            for (int j = 2; j <= 5; j++) {
                coordinate = new Coordinate(i, j);
                assertEquals(EPieceType.NOTHING, board.GetPieceType(coordinate));
            }
        }
    }

    @Test
    public void testCapturesAtStart(){
        assertTrue(board.GetP1Captures() == 0);
        assertTrue(board.GetP2Captures() == 0);
    }

    @Test
    public void pawnMove(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 3);
        board.AttemptMove(coordinate,coordinate2, true );
        assertTrue(board.GetPieceType(coordinate) == EPieceType.NOTHING);
        assertTrue(board.GetPieceType(coordinate2) == EPieceType.PAWN);
    }

    @Test
    public void wrongColorMove(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 3);
        assertTrue(board.AttemptMove(coordinate,coordinate2, false) == EMoveResult.NOT_YOUR_PIECE);
    }

    @Test
    public void pawnMoveRange(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 4);
        assertTrue(board.AttemptMove(coordinate,coordinate2, true ) == EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void pawnInvalidMove(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(5, 4);
        assertTrue(board.AttemptMove(coordinate,coordinate2, true ) == EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void pawnCapturePawn(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 3);
        board.AttemptMove(coordinate,coordinate2, true );
        coordinate = new Coordinate(1, 6);
        coordinate2 = new Coordinate(1, 4);
        board.AttemptMove(coordinate, coordinate2, false);
        coordinate = new Coordinate(0, 3);
        coordinate2 = new Coordinate(1, 4);
        board.AttemptMove(coordinate, coordinate2, true);
        assertTrue(board.GetPieceType(coordinate2) == EPieceType.PAWN);
        assertTrue(board.GetPieceType(coordinate) == EPieceType.NOTHING);
        assertTrue(board.GetP1Captures() == 1);
    }

    @Test
    public void rookMoveBlocked(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(0, 1);
        assertTrue(board.AttemptMove(coordinate, coordinate2, true) == EMoveResult.PLACE_ON_OWN_PIECE);
    }

    @Test
    public void rookMoveBlocked2(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(0, 3);
        assertTrue(board.AttemptMove(coordinate, coordinate2, true) == EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void rookMove(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 3);
        board.AttemptMove(coordinate, coordinate2, true);
        coordinate = new Coordinate(0,0);
        coordinate2 = new Coordinate(0, 2);
        board.AttemptMove(coordinate, coordinate2, true);
        assertTrue(board.GetPieceType(coordinate) == EPieceType.NOTHING);
        assertTrue(board.GetPieceType(coordinate2) == EPieceType.ROOK);
        coordinate = new Coordinate(5, 2);
        board.AttemptMove(coordinate2, coordinate, true);
        assertTrue(board.GetPieceType(coordinate) == EPieceType.ROOK);
        assertTrue(board.GetPieceType(coordinate2) == EPieceType.NOTHING);
    }

    @Test
    public void rookInvalidMove(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 3);
        board.AttemptMove(coordinate, coordinate2, true);
        coordinate = new Coordinate(0,0);
        coordinate2 = new Coordinate(0, 2);
        board.AttemptMove(coordinate, coordinate2, true);
        assertTrue(board.GetPieceType(coordinate) == EPieceType.NOTHING);
        assertTrue(board.GetPieceType(coordinate2) == EPieceType.ROOK);
        coordinate = new Coordinate(5, 4);
        assertTrue(board.AttemptMove(coordinate2, coordinate, true) == EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void rookCapturePawn(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 3);
        board.AttemptMove(coordinate, coordinate2, true);
        coordinate = new Coordinate(0,0);
        coordinate2 = new Coordinate(0, 2);
        board.AttemptMove(coordinate, coordinate2, true);
        coordinate = new Coordinate(5, 2);
        board.AttemptMove(coordinate2, coordinate, true);
        coordinate2 = new Coordinate(5, 6);
        board.AttemptMove(coordinate, coordinate2, true);
        assertTrue(board.GetP1Captures() == 1);
        assertTrue(board.GetPieceType(coordinate2) == EPieceType.ROOK);
    }

    @Test
    public void rookCaptureBlocked(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 3);
        board.AttemptMove(coordinate, coordinate2, true);
        coordinate = new Coordinate(0,0);
        coordinate2 = new Coordinate(0, 2);
        board.AttemptMove(coordinate, coordinate2, true);
        coordinate = new Coordinate(5, 2);
        board.AttemptMove(coordinate2, coordinate, true);
        coordinate2 = new Coordinate(5, 7);
        assertTrue(board.AttemptMove(coordinate, coordinate2, true) == EMoveResult.OUT_OF_PIECE_RANGE);
    }

}
