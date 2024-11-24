package GameLogic.Chess;

public class GUIOutputSimulator implements GUIInterface
{
    BoardFormatSquare displayBoard[][] = new BoardFormatSquare[8][8];

    public void SetSelectedPeice(Coordinate pos, boolean is_selected)
    {
        // get the content of the square
        BoardFormatSquare content = displayBoard[pos.GetX()][pos.GetY()];
        // if the square is not empty then set the selected state
        if(content.square_type != ESquareContents.SQUARE_EMPTY)
        {
            content.is_selected = is_selected;
            PrintBoard();
            if(is_selected)
            {
                System.out.println(String.format("Piece at: %d, %d selected", pos.GetX(), pos.GetY()));
            }
            else
            {
                System.out.println(String.format("Piece at: %d, %d unselected", pos.GetX(), pos.GetY()));
            }
        }
    }

    public void UpdateBoard(BoardFormatSquare[][] formatted_board)
    {
        // ensure that the given formatted board is the correct size
        if((formatted_board.length == 8) && (formatted_board[0].length == 8))
        {
            // make this the new board
            displayBoard = formatted_board;
        }

        // print the board to the screen
        PrintBoard();
    }

    private void PrintBoard()
    {
        // loop through each cell and create the string output for that square
        System.out.println("Board Layout: ");
        StringBuilder board_string = new StringBuilder();
        for(int y = 7; y >= 0; y--)
        {
            board_string.append("|");
            for(int x = 0; x < 8; x++)
            {
                String temp;
                BoardFormatSquare square = displayBoard[x][y];
                // is the square black or white
                if( (x+y)%2 == 0)
                {
                    if(square.is_selected)
                    {
                        temp = String.format("[%s%s]|", square.square_type.GetDisplayName(), square.piece_type.GetDisplayName());
                    }
                    else
                    {
                        temp = String.format(" %s%s |", square.square_type.GetDisplayName(), square.piece_type.GetDisplayName());
                    }
                }
                else
                {
                    if(square.is_selected)
                    {
                        temp = String.format("[%s%s]|", square.square_type.GetDisplayName(), square.piece_type.GetDisplayName());
                    }
                    else
                    {
                        temp = String.format(" %s%s |", square.square_type.GetDisplayName(), square.piece_type.GetDisplayName());
                    }
                }
                board_string.append(temp);
            }
            board_string.append("\n");
        }

        System.out.print(board_string.toString());
    }

    public void DisplayGameFinished(EGameState game_state, int p1_moves, int p2_moves, int p1_captures, int p2_captures)
    {
        if(game_state == EGameState.P1_WINS)
        {
            System.out.println("Player 1 wins");
        }
        else if(game_state == EGameState.P2_WINS)
        {
            System.out.println("Player 2 wins");
        }
        else if(game_state == EGameState.DRAW)
        {
            System.out.println("Draw");
        }

        System.out.println("Player 1 moves: " + p1_moves);
        System.out.println("Player 2 moves: " + p2_moves);
        System.out.println("Player 1 captures: " + p1_captures);
        System.out.println("Player 2 captures: " + p2_captures);
        GUISimulator.StopGame();
    }

    public void DisplayInCheck(boolean p1_in_check)
    {
        if(p1_in_check)
        {
            System.out.println("Player 1 is in check");
        }
        else
        {
            System.out.println("Player 2 is in check");
        }

    }

    public void DisplayMessage(String message, boolean is_p1)
    {
        if(is_p1){
            System.out.print("Player 1: " + message);
        }
        else
        {
            System.out.print("Player 2: " + message);
        }
    }

    public void DisplayPromotPawn(boolean is_p1)
    {
        if(is_p1)
        {
            System.out.print("Player 1 pick piece type to promote pawn to (Queen(Q) or Knight(K)): ");
        }
        else
        {
            System.out.print("Player 2 pick piece type to promote pawn to (Queen(Q) or Knight(K)): ");
        }
    }
}
