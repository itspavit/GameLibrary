package GameLogic.Chess;

public enum ESquareContents
{
    // black gets - like in electrical wiring
    // sorta went with the electrical analogy so white is +
    BLACK_PIECE("-"),
    WHITE_PIECE("+"),
    SQUARE_EMPTY(" ");

    // the display name of the enum
    private final String name;

    /**
     * initialize the enum value
     * @param _name: the display name for the enum
     */
    private ESquareContents(String _name)
    {
        name = _name;
    }

    public String GetDisplayName()
    {
        return name;
    }
}
