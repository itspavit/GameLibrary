package GameLogic.Checkers;

import java.util.ArrayList;

public class CheckersLogic {
    private ArrayList<CheckersPiece> pieces = new ArrayList<>();
    private ETurn turn;

    public CheckersLogic(ETurn turn) {
        for (int i=0;i<8;i+=2){
            pieces.add(new PlayerPiece(i,0));
            pieces.add(new PlayerPiece(i+1,1));

            pieces.add(new OpponentPiece(i+1,7));
            pieces.add(new OpponentPiece(i,6));
        }

    }
    public synchronized boolean playerMove(Coord start, Coord dest){
        CheckersPiece piece = getPiece(start);
        if(piece == null){
            return false;
        }
        System.out.println("piece:"+piece.getType());
        return true;
    }
    public synchronized boolean opponentMove(Coord start, Coord dest){
        return true;
    }
    private void jumpPiece(CheckersPiece piece){
    }
    public CheckersPiece getPiece(Coord location){
        for(CheckersPiece p : pieces){
            if (p.getPosition().equals(location)){
                return p;
            }
        }
        return  null;
    }
    public ArrayList<CheckersPiece> getPieces() {
        return pieces;
    }
    public ETurn getTurn() {
        return turn;
    }
}
