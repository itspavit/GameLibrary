package GameLogic.Chess;

public enum EPieceType
{
    // Characters a chosen that kinda look like the pieces on the board
    PAWN("n"),
    KNIGHT("Z"),
    BISHOP("A"),
    ROOK("H"),
    QUEEN("M"),
    KING("T"),
    NOTHING("_");

    // the name to be displayed on the output board
    private final String name;
    /**
     * Initialize the enum value
     */
    private EPieceType(String _name)
    {
        name = _name;
    }

    /**
     * the display name for this piece type
     * @return
     */
    public String GetDisplayName()
    {
        return name;
    }
}
