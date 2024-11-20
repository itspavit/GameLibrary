package GameLogic.Checkers;

import java.util.ArrayList;

public class CheckersLogic {
    private ArrayList<CheckersPiece> pieces = new ArrayList<>();
    private ETurn turn;

    public CheckersLogic(ETurn turn) {
        for (int i = 0; i < 8; i += 2) {
            pieces.add(new PlayerPiece(i, 6));
            pieces.add(new PlayerPiece(i + 1, 7));

            pieces.add(new OpponentPiece(i + 1, 1));
            pieces.add(new OpponentPiece(i, 0));
        }

    }

    public synchronized boolean playerMove(Coord start, Coord dest) {
        CheckersPiece piece = getPiece(start);
        //the player can only move their own pieces
        if (piece.getType() != EPieceTypes.Player && piece.getType() != EPieceTypes.PlayerKing) {
            System.out.println("can't move opponent pieces");
            return false;
        }
        // normal pieces can't move backwards
        if (piece.getType() == EPieceTypes.Player && start.y < dest.y) {
            System.out.println("normal pices can't move backwards");
            return false;
        }
        return doGeneralRules(start, dest);
    }

    public synchronized boolean opponentMove(Coord start, Coord dest) {
        CheckersPiece piece = getPiece(start);
        if (piece.getType() != EPieceTypes.Opponent && piece.getType() != EPieceTypes.OpponentKing) {
            return false;
        }
        System.out.println("moving piece:" + piece.getType() + " to " + dest);
        piece.setPosition(dest);
        return true;
    }

    /**
     * general rules for both players (eg. only moving 1 space, destination is empty, etc.)
     * @param start location of the piece to move
     * @param dest where the piece is moving
     * @return if the move is successful
     */
    private boolean doGeneralRules(Coord start, Coord dest) {
        CheckersPiece piece = getPiece(start);
        // pieces cant move to an occupied space
        if (getPiece(dest) != null) {
            System.out.println("there is already a piece at " + dest);
            return false;
        }
        int xDiff = Math.abs(start.x - dest.x);
        int yDiff = Math.abs(start.y - dest.y);
        // normal moves can only be 1 space diagonally
        if ((xDiff != 1 || yDiff != 1) && !jumpPiece(start, dest)){
            System.out.println("move must be 1 space diagonally or a jump");
            return  false;
        }
        System.out.println("moving piece:" + piece.getType() + " to " + dest);
        piece.setPosition(dest);
        return true;

    }
    private boolean jumpPiece(Coord start, Coord dest) {
        CheckersPiece piece = getPiece(dest);
        int xdiff = Math.abs(start.x - dest.x);
        int ydiff = Math.abs(start.y - dest.y);
        if (xdiff == 2 || ydiff == 2) {
            System.out.println("jumping xdiff:"+xdiff+" ydiff:"+ydiff);
            return true;
        }
        System.out.println("not a valid jump xdiff:"+xdiff+" ydiff:"+ydiff);
        return false;

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
