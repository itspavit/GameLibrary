package GameLogic.Checkers;

import java.util.ArrayList;

public abstract class CheckersPiece {
    private Coord position;
    private ArrayList<CheckersPiece> gamePieces = new ArrayList<>();

    public abstract Boolean canMove(Coord destination);

    public ArrayList<CheckersPiece> getMoves() {
        return null;
    }

    public Coord getPosition() {
        return position;
    }
}
