package GameLogic.Checkers;

import java.util.ArrayList;

public class CheckersLogic {
    private ArrayList<CheckersPiece> pieces = new ArrayList<>();
    private ETurn turn;

    public CheckersPiece getPiece(Coord location){
        return  null;
    }
    public ArrayList<CheckersPiece> getPieces() {
        return pieces;
    }

    public ETurn getTurn() {
        return turn;
    }
}
