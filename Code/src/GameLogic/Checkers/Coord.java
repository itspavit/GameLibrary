package GameLogic.Checkers;

public class Coord {
    public int x, y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coord obj) {
       return obj.x==x && obj.y == y;
    }
}
