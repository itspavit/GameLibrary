package GameLogic.Chess;

import org.junit.Test;

public class KnightTests
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
     * test the move functionality for cases: valid move to empty square, valid move with capture, obstructed by allied piece,
     * obstructed by end of the board
     */
    @Test
    public void MoveUP2Right1()
    {

    }


    /**
     * test the move functionality for cases: valid move to empty square, valid move with capture, obstructed by allied piece,
     * obstructed by end of the board
     */
    @Test
    public void MoveUP1Right2()
    {

    }


    /**
     * test the move functionality for cases: valid move to empty square, valid move with capture, obstructed by allied piece,
     * obstructed by end of the board
     */
    @Test
    public void MoveDown1Right2()
    {

    }


    /**
     * test the move functionality for cases: valid move to empty square, valid move with capture, obstructed by allied piece,
     * obstructed by end of the board
     */
    @Test
    public void MoveDown2Right1()
    {

    }


    /**
     * test the move functionality for cases: valid move to empty square, valid move with capture, obstructed by allied piece,
     * obstructed by end of the board
     */
    @Test
    public void MoveDown2left1()
    {

    }


    /**
     * test the move functionality for cases: valid move to empty square, valid move with capture, obstructed by allied piece,
     * obstructed by end of the board
     */
    @Test
    public void MoveDown1left2()
    {

    }


    /**
     * test the move functionality for cases: valid move to empty square, valid move with capture, obstructed by allied piece,
     * obstructed by end of the board
     */
    @Test
    public void MoveUP1Left2()
    {

    }


    /**
     * test the move functionality for cases: valid move to empty square, valid move with capture, obstructed by allied piece,
     * obstructed by end of the board
     */
    @Test
    public void MoveUP2Left1()
    {

    }


    /**
     * test that the Knight does not allow moving to invalid positions
     */
    @Test
    public void InValidMovePosition()
    {

    }
}
