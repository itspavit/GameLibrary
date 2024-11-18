package GameLogic.Checkers;

import java.util.ArrayList;

public class CheckersLogic {
    private ArrayList<CheckersPiece> pieces = new ArrayList<>();
    private ETurn turn;

    public CheckersLogic(ETurn turn) {
        for (int i = 0; i < 8; i += 2) {
            pieces.add(new PlayerPiece(i, 0));
            pieces.add(new PlayerPiece(i + 1, 1));

            pieces.add(new OpponentPiece(i + 1, 7));
            pieces.add(new OpponentPiece(i, 6));
        }

    }

    public synchronized boolean playerMove(Coord start, Coord dest) {
        CheckersPiece piece = getPiece(start);
        if (piece.getType() != EPieceTypes.Player && piece.getType() != EPieceTypes.PlayerKing) {
            return false;
        }
        System.out.println("moving piece:" + piece.getType() + " to " + dest);
        piece.setPosition(dest);
        return true;
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

    private void jumpPiece(CheckersPiece piece) {
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
