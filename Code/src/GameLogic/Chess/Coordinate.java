package GameLogic.Chess;

public class Coordinate
{
    // the min and max index values for the chess board, used to bound the coordinate
    static final int MAX_TILE_INDEX = 7;
    static final int MIN_TILE_INDEX = 0;

    // the x and y components of the coordinate
    private int x = 0;
    private int y = 0;

    /**
     * initialize a coordinate
     * @param _x: the x value of the coordinate
     * @param _y; the y value of the coordinate
     */
    Coordinate(int _x, int _y)
    {

        // ensure that the x and y values are appropriately bounded
        if(_x < MIN_TILE_INDEX || _x > MAX_TILE_INDEX)
        {
            this.x = 0;
        }
        else
        {
            this.x = _x;
        }

        if(_y < MIN_TILE_INDEX || _y > MAX_TILE_INDEX)
        {
            this.y = 0;
        }
        else
        {
            this.y = _y;
        }
    }

    // getters and setters
    int GetX()
    {
        return x;
    }

    int GetY()
    {
        return y;
    }


    void SetX(int _x)
    {
        if(_x < MIN_TILE_INDEX || _x > MAX_TILE_INDEX)
        {
            this.x = 0;
        }
        else
        {
            this.x = _x;
        }
    }

    void SetY(int _y)
    {
        if(_y < MIN_TILE_INDEX || _y > MAX_TILE_INDEX)
        {
            this.y = 0;
        }
        else
        {
            this.y = _y;
        }
    }
}
