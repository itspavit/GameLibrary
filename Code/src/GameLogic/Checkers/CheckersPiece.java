package GameLogic.Checkers;

import java.util.ArrayList;

public abstract class CheckersPiece {

    protected Coord position;
    protected CheckersLogic logic;

    public CheckersPiece(Coord position) {
        this.position = position;
    }

    public CheckersPiece(int x, int y) {
        this.position = new Coord(x, y);
    }

    public void addGameLogic(CheckersLogic logic) {
        this.logic = logic;
    }


    public abstract EPieceTypes getType();

    public boolean onPlayerTeam() {
        return getType() == EPieceTypes.Player || getType() == EPieceTypes.PlayerKing;
    }

    public ArrayList<Coord> getMoves() {
        ArrayList<Coord> moves = new ArrayList<>();

        for (int i = -2; i <= 2; i++) {
            if (canMove(new Coord(i, i).add(position))) {
                moves.add(new Coord(i, i).add(position));
            }
            if (canMove(new Coord(i, -i).add(position))) {
                moves.add(new Coord(i, i).add(position));
            }
        }
        return moves;
    }
    public boolean doMove(Coord dest){
        if(canMove(dest)){
            Coord change = dest.sub(position);
            // removing the piece if they jump
            if(Math.abs(change.x)==2){
                CheckersPiece piece = logic.getPiece(position.add(change.div(2)));
                System.out.println("before jump:"+logic.getPieces());
                System.out.println("removing :"+piece+ "at "+position.add(change.div(2)));
                logic.getPieces().remove(piece);
                System.out.println("after jump:"+logic.getPieces());
            }
            position = dest;
            return  true;
        }
        return false;
    }

    // checks general logic for kings (doesnt check direction)
    public boolean canMove(Coord dest) {
        // pieces cant move to an out of bounds space
        if (dest.x > 7 || dest.y > 7 || dest.x < 0 || dest.y < 0) {
            System.out.println(dest + " out of bounds");
            return false;
        }
        if (logic.getPiece(dest) != null) {
            System.out.println("there is already a piece at " + dest);
            return false;
        }
        int xDiff = Math.abs(position.x - dest.x);
        int yDiff = Math.abs(position.y - dest.y);

        // doing a normal movement (no jump)
        if (xDiff == 1 && yDiff == 1) {
            // if a jump is available it must be taken
            for (CheckersPiece p : logic.getPieces()) {
                if (p.onPlayerTeam() == onPlayerTeam() && p.canJump()) {
                    System.out.println("can't do a normal move, piece at " + p.getPosition() + " can jump");
                    return false;
                }
            }
            return true;
        }
        // jumping if they move 2 spaces
        if (xDiff == 2 && yDiff == 2) {
            return canJump(dest);
        }
        //returning false if they move any other way
        return false;
    }

    /**
     * @param dest this is assumed to be 2 places diagonally away from the piece in a direction it can move
     * @return if the piece can move to the given spot
     */
    protected boolean canJump(Coord dest) {
        if (logic.getPiece(dest)!=null ||dest.x > 7 || dest.y > 7 || dest.x < 0 || dest.y < 0) {
            return false;
        }
        // calculate the location of the jumped piece (pos + (movement/2))
        Coord jumpedLoc = position.add(dest.sub(position).div(2));
        CheckersPiece jumpedPiece = logic.getPiece(jumpedLoc);
        return jumpedPiece != null && jumpedPiece.onPlayerTeam() != onPlayerTeam();

    }

    protected  boolean canJump() {
        // check all diagonals by default (king jump logic)
        int x = position.x;
        int y = position.y;
        return (canJump( new Coord(x - 2, y - 2)) ||
                canJump( new Coord(x + 2, y - 2)) ||
                canJump( new Coord(x - 2, y + 2)) ||
                canJump( new Coord(x + 2, y + 2)));
    }

    public Coord getPosition() {
        return position;
    }

    public void setPosition(Coord position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return getType()+":"+position;
    }
}
