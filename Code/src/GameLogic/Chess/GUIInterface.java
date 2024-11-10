package GameLogic.Chess;

public interface GUIInterface
{
    public void SetSelectedPeice(Coordinate pos, boolean is_selected);
    public void UpdateBoard(BoardFormatGUI formated_board);
    public void DisplayGameFinished(EGameState game_state, int p1_moves, int p2_moves, int p1_captures, int p2_captures);
    public void DisplayInCheck(boolean p1_in_check);
    public void DisplayMessage(String message, boolean is_p1);
}
