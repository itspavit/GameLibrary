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
    public Coord sub(Coord c){
        return  new Coord(x - c.x, y - c.y);
    }
    public Coord add(Coord c){
        return  new Coord(x + c.x, y + c.y);
    }
    public Coord div(int v){
        return  new Coord( x/v, y/v);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
