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
        for(CheckersPiece p : pieces){
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
        for(CheckersPiece p : pieces){
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
        //the player can only move their own pieces
        if (piece.getType() != EPieceTypes.Player && piece.getType() != EPieceTypes.PlayerKing) {
            System.out.println("can't move opponent pieces");
            return false;
        }
    return    piece.doMove(dest);
    }

    public synchronized boolean opponentMove(Coord start, Coord dest) {
        System.out.println("aaaaaa");
        CheckersPiece piece = getPiece(start);
        if (turn == ETurn.PLAYER) {
        //    return false;
        }
        System.out.println("aaaaaa");
        if (piece.getType() != EPieceTypes.Opponent && piece.getType() != EPieceTypes.OpponentKing) {
            return false;
        }
        boolean x = piece.doMove(dest);
        System.out.println("success:"+x);
        return x;
    }

    /**
     * general rules for both players (eg. only moving 1 space, destination is empty, etc.)
     *
     * @param start location of the piece to move
     * @param dest  where the piece is moving
     * @return if the move is successful
     */
    private boolean doGeneralRules(Coord start, Coord dest, boolean playerTurn) {
        CheckersPiece piece = getPiece(start);
        // pieces cant move to an occupied space
        if(dest.x>7 || dest.y>7 || dest.x<0 || dest.y<0) {

            System.out.println(dest+" out of bounds");
            return  false;
        }
        if (getPiece(dest) != null) {
            System.out.println("there is already a piece at " + dest);
            return false;
        }
        int xDiff = Math.abs(start.x - dest.x);
        int yDiff = Math.abs(start.y - dest.y);

        // doing a normal movement (no jump)
        if (xDiff == 1 && yDiff == 1) {
            // if a jump is available it must be taken
            for (CheckersPiece p : pieces) {
                if (p.onPlayerTeam() == playerTurn && canJump(p)) {
                    System.out.println("can't do a normal move, piece at " + p.getPosition() + " can jump");
                    return false;
                }
            }
            System.out.println("moving piece:" + piece.getType() + " to " + dest);
            piece.setPosition(dest);
            return true;
        }
        // jumping if they move 2 spaces
        if (xDiff == 2 && yDiff == 2) {
            if (!jumpPiece(start, dest)) {
                return false;
            }
            piece.setPosition(dest);
            return true;
        }
        //returning false if they move any other way
        return false;
    }

    private boolean canJump(CheckersPiece piece) {
        int x = piece.getPosition().x;
        int y = piece.getPosition().y;
        if (piece.getType() == EPieceTypes.PlayerKing || piece.getType() == EPieceTypes.OpponentKing) {
            // check all diagonals for piece on other team
            return (canJump(piece, new Coord(x - 2, y - 2)) ||
                    canJump(piece, new Coord(x + 2, y - 2)) ||
                    canJump(piece, new Coord(x - 2, y + 2)) ||
                    canJump(piece, new Coord(x + 2, y + 2)));
        }
        if (piece.getType() == EPieceTypes.Player) {
            return (canJump(piece, new Coord(x - 2, y - 2)) ||
                    canJump(piece, new Coord(x + 2, y - 2)));
        }
        if (piece.getType() == EPieceTypes.Opponent) {
            return (canJump(piece, new Coord(x - 2, y + 2)) ||
                    canJump(piece, new Coord(x + 2, y + 2)));
        }
        return false;
    }

    /**
     * @param piece
     * @param dest  this is assumed to be 2 places diagonally away from the piece in a direction it can move
     * @return
     */
    private boolean canJump(CheckersPiece piece, Coord dest) {
        if(getPiece(dest)!=null || dest.x>7 || dest.y>7 || dest.x<0 || dest.y<0) {
            return  false;
        }
        Coord pos = piece.getPosition();
        // calculate the location of the jumped piece (pos + (movement/2))
        Coord jumpedLoc= pos.add(dest.sub(pos).div(2));
        CheckersPiece jumpedPiece = getPiece(jumpedLoc);
        return jumpedPiece != null && jumpedPiece.onPlayerTeam() != piece.onPlayerTeam();
    }


    private boolean jumpPiece(Coord start, Coord dest) {
        CheckersPiece piece = getPiece(start);
        int moveX = dest.x - start.x; // will be +/- 2 depending on jump direction
        int moveY = dest.y - start.y; // will be +/- 2 depending on jump direction
        Coord jumpLoc = new Coord(moveX / 2 + start.x, moveY / 2 + start.y);
        CheckersPiece jumpedPiece = getPiece(jumpLoc);
        if (jumpedPiece == null) {
            System.out.println("there is no piece to jump at " + jumpLoc);
            return false;
        }
        // you can't jump a piece on the same team
        if (piece.onPlayerTeam() == jumpedPiece.onPlayerTeam()) {
            return false;
        }

        // removing the jumped piece
        System.out.println("jumping " + jumpedPiece.getType() + " at " + jumpLoc);
        pieces.remove(jumpedPiece);
        return true;
    }

    public ArrayList<Coord> getPlayerMoves(){
        return  null;

    }
    public ArrayList<Coord> getOpponentsMoves(){
        return  null;

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
