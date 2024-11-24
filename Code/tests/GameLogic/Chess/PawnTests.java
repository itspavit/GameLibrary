package GameLogic.Chess;

import org.junit.Before;
import org.junit.Test;

public class PawnTests
{

    /**
     * creates an empty chess board, and stores the reference in empty_board variable
     */
    private ChessBoard CreateEmptyBoard(){

        // initialize the empty board
        ChessBoard empty_board = new ChessBoard(true);
        // clear the contents of all squares
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                empty_board.ClearSquare(new Coordinate(i, j));
            }
        }
        // return the empty board
        return empty_board;
    }

    /**
     * test the pawn move forward functionality for 5 cases: empty square, square with enemy piece, square with
     * allied piece, end of board, past end of board
     */
    @Test
    public void MoveForward1()
    {

    }

    /**
     * test the pawn move forward 2 functionality for 5 cases: at starting position and empty square, not at starting
     * position and empty square, at starting position and enemy piece, at starting position and allied piece, not at
     * starting position and non-empty square.
     */
    @Test
    public void MoveForward2()
    {

    }

    /**
     * test the pawn capture functionality for 5 cases: empty square at target, allied piece at target, enemy piece at
     * target, end of board and enemy piece, past end of board
     */
    @Test
    public void CaptureDiagonal()
    {

    }

    /**
     * test the pawn enpassent functionality for 2 cases: valid enpassent (enemy pawn just moved 2 spaces, with empty square behind),
     * enemy pawn moved 2 spaces and time has elapsed
     */
    @Test
    public void Enpassent()
    {

    }

    /**
     * test that when a pawn reaches the end of the board, it returns the promote pawn state
     */
    @Test
    public void Promotion()
    {

    }

    /**
     * test that the pawn does not allow moving to invalid positions
     */
    @Test
    public void InValidMovePosition()
    {

    }

}
