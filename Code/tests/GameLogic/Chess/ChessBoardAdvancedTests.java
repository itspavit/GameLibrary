package GameLogic.Chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardAdvancedTests {
    private ChessBoard board;
    private Coordinate coordinate;
    private Coordinate coordinate2;

    @Before
    public void setup(){
        board = new ChessBoard(true);
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                coordinate = new Coordinate(i, j);
                board.ClearSquare(coordinate);
            }

        }
    }

    @Test
    public void clearBoard(){
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                coordinate = new Coordinate(i, j);
                assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
            }
        }
    }

    @Test
    public void pawnMove(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 2);
        Pawn pawn = new Pawn(coordinate, true, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.PAWN);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.PAWN);
    }

    @Test
    public void pawnDoubleMove(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 3);
        Pawn pawn = new Pawn(coordinate, true, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.PAWN);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.PAWN);
    }

    @Test
    public void pawnMoveInvalid(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 4);
        Pawn pawn = new Pawn(coordinate, true, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.PAWN);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void pawnMoveBlocked(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 2);
        Pawn pawn = new Pawn(coordinate, true, board, -1000);
        Pawn pawn2 = new Pawn(coordinate2, true, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.PAWN);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.PLACE_ON_OWN_PIECE);
    }

    @Test
    public void pawnMoveCapture(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(1, 2);
        Pawn pawn = new Pawn(coordinate, true, board, -1000);
        Pawn pawn2 = new Pawn(coordinate2, false, board, -1000);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.PAWN);
    }

    @Test
    public void pawnMoveBlockedBlack(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 2);
        Pawn pawn = new Pawn(coordinate, true, board, -1000);
        Pawn pawn2 = new Pawn(coordinate2, false, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.PAWN);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void pawnMovePromote(){
        coordinate = new Coordinate(0, 6);
        coordinate2 = new Coordinate(0, 7);
        Pawn pawn = new Pawn(coordinate, true, board, -1000);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.PROMOTE_PAWN);
    }

    @Test
    public void pawnCheck(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(1, 2);
        Pawn pawn = new Pawn(coordinate, true, board, -1000);
        King king2 = new King(coordinate2, false, board, 8);
        assertSame(board.GetGameStatus(), EGameState.P2_IN_CHECK);
    }

    @Test
    public void RookMoveHorizontalR(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(7, 0);
        Rook rook = new Rook(coordinate, true, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.ROOK);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.ROOK);
    }

    @Test
    public void RookMoveHorizontalL(){
        coordinate = new Coordinate(7, 0);
        coordinate2 = new Coordinate(0, 0);
        Rook rook = new Rook(coordinate, true, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.ROOK);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.ROOK);
    }

    @Test
    public void RookMoveVerticalU(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(0, 7);
        Rook rook = new Rook(coordinate, true, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.ROOK);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.ROOK);
    }

    @Test
    public void RookMoveVerticalD(){
        coordinate = new Coordinate(0, 7);
        coordinate2 = new Coordinate(0, 0);
        Rook rook = new Rook(coordinate, true, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.ROOK);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.ROOK);
    }

    @Test
    public void RookMoveInvalid(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(7, 7);
        Rook rook = new Rook(coordinate, true, board, -1000);
        assertSame(board.GetPieceType(coordinate), EPieceType.ROOK);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void rookMoveBlocked(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 2);
        Rook rook = new Rook(coordinate, true, board, -1000);
        Pawn pawn2 = new Pawn(coordinate2, true, board, -1000);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.PLACE_ON_OWN_PIECE);
    }

    @Test
    public void rookMoveBlocked2(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 2);
        Rook rook = new Rook(coordinate, true, board, -1000);
        Pawn pawn2 = new Pawn(coordinate2, true, board, -1000);
        coordinate2 = new Coordinate(0, 7);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void RookMoveCapture(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 6);
        Rook rook = new Rook(coordinate, true, board, -1000);
        Pawn pawn2 = new Pawn(coordinate2, false, board, -1000);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.ROOK);
    }

    @Test
    public void RookMoveBlockedBlack(){
        coordinate = new Coordinate(0, 1);
        coordinate2 = new Coordinate(0, 2);
        Rook rook = new Rook(coordinate, true, board, -1000);
        Pawn pawn2 = new Pawn(coordinate2, false, board, -1000);
        coordinate2 = new Coordinate(0, 7);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void RookCheck(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(0, 7);
        Pawn pawn = new Pawn(coordinate, true, board, -1000);
        King king2 = new King(coordinate2, false, board, 8);
        assertSame(board.GetGameStatus(), EGameState.P2_IN_CHECK);
    }

    @Test
    public void BishopMoveUR(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(7, 7);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        assertSame(board.GetPieceType(coordinate), EPieceType.BISHOP);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.BISHOP);
    }

    @Test
    public void BishopMoveUL(){
        coordinate = new Coordinate(7, 0);
        coordinate2 = new Coordinate(0, 7);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        assertSame(board.GetPieceType(coordinate), EPieceType.BISHOP);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.BISHOP);
    }

    @Test
    public void BishopMoveDR(){
        coordinate = new Coordinate(0, 7);
        coordinate2 = new Coordinate(7, 0);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        assertSame(board.GetPieceType(coordinate), EPieceType.BISHOP);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.BISHOP);
    }

    @Test
    public void BishopMoveDL(){
        coordinate = new Coordinate(7, 7);
        coordinate2 = new Coordinate(0, 0);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        assertSame(board.GetPieceType(coordinate), EPieceType.BISHOP);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.BISHOP);
    }

    @Test
    public void BishopMoveInvalid(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(0, 1);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        assertSame(board.GetPieceType(coordinate), EPieceType.BISHOP);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void BishopMoveBlocked(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(1, 1);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        Pawn pawn2 = new Pawn(coordinate2, true, board, -1000);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.PLACE_ON_OWN_PIECE);
    }

    @Test
    public void BishopMoveBlocked2(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(1, 1);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        Pawn pawn2 = new Pawn(coordinate2, true, board, -1000);
        coordinate2 = new Coordinate(7, 7);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void BishopMoveCapture(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(5, 5);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        Pawn pawn2 = new Pawn(coordinate2, false, board, -1000);
        board.AttemptMove(coordinate, coordinate2, true);
        assertSame(board.GetPieceType(coordinate), EPieceType.NOTHING);
        assertSame(board.GetPieceType(coordinate2), EPieceType.BISHOP);
    }

    @Test
    public void BishopMoveBlockedBlack(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(1, 1);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        Pawn pawn2 = new Pawn(coordinate2, false, board, -1000);
        coordinate2 = new Coordinate(7, 7);
        assertSame(board.AttemptMove(coordinate, coordinate2, true), EMoveResult.OUT_OF_PIECE_RANGE);
    }

    @Test
    public void BishopCheck(){
        coordinate = new Coordinate(0, 0);
        coordinate2 = new Coordinate(7, 7);
        Bishop bishop= new Bishop(coordinate, true, board, 32);
        King king2 = new King(coordinate2, false, board, 8);
        assertSame(board.GetGameStatus(), EGameState.P2_IN_CHECK);
    }

}
