package GameLogic.Chess;

import java.util.Scanner;

public class GUISimulator
{
    // create the scanner for getting user input
    private static final Scanner scanner = new Scanner(System.in);
    // should the game continue
    static boolean in_play = true;
    public static void main(String[] args)
    {
        System.out.println("Game started");
        PlayerSimulator p1 = new PlayerSimulator();
        PlayerSimulator p2 = new PlayerSimulator();
        GUIOutputSimulator gui = new GUIOutputSimulator();

        ChessGameManager gameManager = new ChessGameManager(p1, p2, gui, false);

        while(in_play)
        {
            String input = GetNonEmptyStringFromUser();
            if(p1.IsMyTurn())
            {
                p1.HandlePlayerInput(input);
            }
            else
            {
                p2.HandlePlayerInput(input);
            }
        }

    }


    public static void StopGame()
    {
        in_play = false;
    }

    /**
     * loops until the user enters a non-empty string
     * @return : returns the string the user entered
     */
    private static String GetNonEmptyStringFromUser()
    {

        boolean validInput = false;
        String inputString = "";
        // loop until the user enters a non-empty string
        while(!validInput)
        {
            inputString = scanner.nextLine();

            if(inputString.isBlank())
            {
                // if string was empty then output message telling the user the problem and continue looping
                System.out.println("Value entered was blank. Please enter a non-empty string");
            }
            else
            {
                // if the string is not empty and allowed then stop the loop
                validInput = true;
            }
        }
        return inputString;
    }
}
