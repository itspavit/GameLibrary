package GameLogic.Chess;

public enum EMoveResult
{
    PUT_SELF_IN_CHECK,
    OUT_OF_PIECE_RANGE,
    PLACE_ON_OWN_PIECE,
    VALID_MOVE,
    PROMOTE_PAWN,
    NOT_YOUR_PIECE
}
