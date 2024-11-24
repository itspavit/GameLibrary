package GameLogic.Chess;

import org.junit.Test;

public class BishopTests
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
     * Test the move capability of the Bishop for cases: valid unobstructed move empty square, valid move enemy in square,
     * obstructed by allied piece, obstructed by enemy piece, obstructed by the end of the board
     */
    @Test
    public void MoveUpRight()
    {

    }


    /**
     * Test the move capability of the Bishop for cases: valid unobstructed move empty square, valid move enemy in square,
     * obstructed by allied piece, obstructed by enemy piece, obstructed by the end of the board
     */
    @Test
    public void MoveDownRight()
    {

    }


    /**
     * Test the move capability of the Bishop for cases: valid unobstructed move empty square, valid move enemy in square,
     * obstructed by allied piece, obstructed by enemy piece, obstructed by the end of the board
     */
    @Test
    public void MoveDownLeft()
    {

    }


    /**
     * Test the move capability of the Bishop for cases: valid unobstructed move empty square, valid move enemy in square,
     * obstructed by allied piece, obstructed by enemy piece, obstructed by the end of the board
     */
    @Test
    public void MoveUpLeft()
    {

    }


    /**
     * test that the Bishop does not allow moving to invalid positions
     */
    @Test
    public void InValidMovePosition()
    {

    }
}
