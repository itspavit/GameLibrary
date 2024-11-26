package GameLogic.Chess;

import java.util.Locale;

public class PlayerSimulator implements PlayerInterface
{
    ChessGameManager manager;
    boolean my_turn = false;
    boolean promote_pawn = false;

    public boolean IsMyTurn()
    {
        return my_turn;
    }

    public void SetGameManager(ChessGameManager _manager)
    {
        manager = _manager;
    }

    public void SetToYourTurn()
    {
        my_turn = true;
    }

    public void SetToOthersTurn()
    {
        my_turn = false;
    }

    public void HandlePlayerInput(String input)
    {
        // do nothing if it is not my turn
        if(!my_turn)
        {
            return;
        }

        // convert the input to lower case
        input = input.toLowerCase(Locale.ROOT);

        // if the input is s then stop the game
        if(input.equals("s"))
        {
            manager.HandleGameEnded();
            GUISimulator.StopGame();
        }
        else
        {
            if(promote_pawn)
            {
                if(input.equals("q"))
                {
                    manager.HandlePromotion(EPieceType.QUEEN);
                }
                else if(input.equals("k"))
                {
                    manager.HandlePromotion(EPieceType.KNIGHT);
                }
                else
                {
                    System.out.print("pick piece type to promote pawn to (Queen(Q) or Knight(K)): ");
                }
            }
            else
            {
                Coordinate coord = GetCoordinateFromString(input);
                if(coord != null)
                {
                    manager.HandlePositionClicked(this, coord);
                }
                else
                {
                    System.out.print("Invalid Input, Enter Coordinate: ");
                }

            }
        }

    }

    public void SetToPromotePawn()
    {
        promote_pawn = true;
    }
    public void FinishPromotePawn()
    {
        promote_pawn = false;
    }

    private static Coordinate GetCoordinateFromString(String input)
    {
        // coordinate must have at least 3 characters, first number, space second number: x_y
        if(input.length() < 3)
        {
            return null;
        }

        // get the characters at the first and third positions in the string
        String first_num = String.format("%c", input.charAt(0));
        String second_num = String.format("%c", input.charAt(2));

        // x and y components
        int x = 0;
        int y = 0;

        // attempt to parse the strings for integers
        try{
            x = Integer.parseInt(first_num);
        }catch (NumberFormatException e)
        {
            return null;
        }

        try{
            y = Integer.parseInt(second_num);
        }catch (NumberFormatException e)
        {
            return null;
        }

        // x and y must be less than 7
        if(x > 7 || y > 7)
        {
            return null;
        }

        // return the coordinate
        return new Coordinate(x,y);
    }
}
