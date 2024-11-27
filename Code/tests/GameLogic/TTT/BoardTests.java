package GameLogic.TTT;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTests {
    Board board;

    @Before
    public void setup(){
        board = new Board();
        System.out.println(board.toString());
    }


    @Test
    public void initialization(){
        String string = "| | | |\n| | | |\n| | | |\n";
        assertEquals(board.toString(), string);
    }

    @Test
    public void makeMove(){
        assertTrue(board.makeMove(1, 1, 'X'));
        String string = "| | | |\n| |X| |\n| | | |\n";
        assertEquals(board.toString(), string);
    }

    @Test
    public void makeMove2(){
        assertTrue(board.makeMove(1, 1, 'X'));
        String string = "| | | |\n| |X| |\n| | | |\n";
        assertFalse(board.makeMove(1, 1, 'O'));
        assertEquals(board.toString(), string);
    }

    @Test
    public void checkDraw(){
        assertFalse(board.isDraw());
    }

    @Test
    public void checkDraw2(){
        board.makeMove(0, 0, 'X');
        board.makeMove(0, 1, 'X');
        board.makeMove(0, 2, 'O');
        board.makeMove(1, 0, 'O');
        board.makeMove(1, 1, 'O');
        board.makeMove(1, 2, 'X');
        board.makeMove(2, 0, 'X');
        board.makeMove(2, 1, 'X');
        board.makeMove(2, 2, 'O');
        String string = "|X|X|O|\n|O|O|X|\n|X|X|O|\n";
        assertEquals(board.toString(), string);
        assertTrue(board.isDraw());
    }

    @Test
    public void checkWinRows(){
        board.makeMove(0, 0, 'X');
        board.makeMove(0, 1, 'X');
        board.makeMove(0, 2, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void checkWinColumns(){
        board.makeMove(0, 0, 'X');
        board.makeMove(1, 0, 'X');
        board.makeMove(2, 0, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void checkWinDiagonalLtR(){
        board.makeMove(0, 0, 'X');
        board.makeMove(1, 1, 'X');
        board.makeMove(2, 2, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void checkWinDiagonalRtL(){
        board.makeMove(0, 2, 'X');
        board.makeMove(1, 1, 'X');
        board.makeMove(2, 0, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void checkWinFalse(){
        board.makeMove(0, 0, 'X');
        board.makeMove(0, 1, 'X');
        board.makeMove(1, 2, 'X');
        assertFalse(board.checkWin('X'));
    }

}
