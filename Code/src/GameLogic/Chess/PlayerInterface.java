package GameLogic.Chess;

public interface PlayerInterface
{
    public void SetGameManager(ChessGameManager _manager);
    public void SetToYourTurn();
    public void SetToOthersTurn();
    public void SetToPromotePawn();
    public void FinishPromotePawn();
}
