package GameLogic.Checkers;

import java.util.ArrayList;

public class CheckersLogic {
    private ArrayList<CheckersPiece> pieces = new ArrayList<>();
    private ETurn turn;

    /**
     * extra constructor that can start with a custom board state for testing
     *
     * @param turn   whos turn it is
     * @param pieces game pieces
     */
    public CheckersLogic(ETurn turn, ArrayList<CheckersPiece> pieces) {
        this.turn = turn;
        this.pieces = pieces;
        for (CheckersPiece p : pieces) {
            p.addGameLogic(this);
        }
    }

    public CheckersLogic(ETurn turn) {
        this.turn = turn;
        for (int i = 0; i < 8; i += 2) {
            pieces.add(new PlayerPiece(i, 6));
            pieces.add(new PlayerPiece(i + 1, 7));

            pieces.add(new OpponentPiece(i + 1, 1));
            pieces.add(new OpponentPiece(i, 0));
        }
        for (CheckersPiece p : pieces) {
            p.addGameLogic(this);
        }
    }

    public synchronized boolean playerMove(Coord start, Coord dest) {
        CheckersPiece piece = getPiece(start);
        // can't play on the other player's turn
        if (turn == ETurn.OPPONENT) {
            System.out.println("not your turn!");
            return false;
        }
        if(piece ==  null){
            return  false;
        }
        //the player can only move their own pieces
        if (piece.getType() != EPieceTypes.Player && piece.getType() != EPieceTypes.PlayerKing) {
            System.out.println("can't move opponent pieces");
            return false;
        }
        boolean success = piece.doMove(dest);
        if (success) {
            Coord change = dest.sub(start);
            turn = ETurn.OPPONENT;
            if(Math.abs(change.x)==2 && getPiece(dest).canJump()){
                turn = ETurn.PLAYER;
            }
        }
        return success;
    }

    public synchronized boolean opponentMove(Coord start, Coord dest) {
        CheckersPiece piece = getPiece(start);
        if (turn == ETurn.PLAYER) {
            return false;
        }
        if(piece ==  null){
            return  false;
        }
        if (piece.getType() != EPieceTypes.Opponent && piece.getType() != EPieceTypes.OpponentKing) {
            return false;
        }
        boolean success = piece.doMove(dest);
        if (success) {
            Coord change = dest.sub(start);
            turn = ETurn.PLAYER;
            if(Math.abs(change.x)==2 && piece.canJump()){
                turn = ETurn.OPPONENT;
            }
        }
        return success;
    }

    public ArrayList<Coord> getPlayerMoves() {
        return null;

    }

    public ArrayList<Coord> getOpponentsMoves() {
        return null;

    }

    public CheckersPiece getPiece(Coord location) {
        for (CheckersPiece p : pieces) {
            if (p.getPosition().equals(location)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<CheckersPiece> getPieces() {
        return pieces;
    }

    public ETurn getTurn() {
        return turn;
    }
}
