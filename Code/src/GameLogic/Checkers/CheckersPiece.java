package GameLogic.Checkers;

import java.util.ArrayList;

public abstract class CheckersPiece {

    private Coord position;
    private ArrayList<CheckersPiece> gamePieces = new ArrayList<>();

    public CheckersPiece(Coord position) {
        this.position = position;
    }
    public CheckersPiece(int x, int y) {
        this.position = new Coord(x, y);
    }
    public abstract Boolean canMove(Coord destination);
    public  abstract EPieceTypes getType();

    public ArrayList<CheckersPiece> getMoves() {
        return null;
    }

    public Coord getPosition() {
        return position;
    }
    public void setPosition(Coord position) {
        this.position = position;
    }
}
