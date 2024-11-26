public class Board {
    private char[][] grid;      //the 2D gameboard array
    //private because we don't want it modified at all

    public Board() {
        grid = new char[3][3];      //3x3 2D array created, can change this for different gameboard sizes
        initializeBoard();          //now there's an instance of the board at the start of a game
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';       //each cell init with a space char since this is a char 2D array
            }
        }
    }


    //Make a move
    public boolean makeMove(int row, int col, char symbol) {
        //first check if position empty
        if (grid[row][col] == ' ') {
            grid[row][col] = symbol;
            return true;
        }
        return false;
    }

    //Check if there's a draw
    public boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    //check for win
    public boolean checkWin(char symbol) {
        //check rows
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) {
                return true;
            }
        }

        //check cols
        for (int j = 0; j < 3; j++) {
            if (grid[0][j] == symbol && grid[1][j] == symbol && grid[2][j] == symbol) {
                return true;
            }
        }

        //check diagonals
        if (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) {
            return true;
        }
        if (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol) {
            return true;
        }

        return false;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append("|");
            for (int j = 0; j < 3; j++) {
                sb.append(grid[i][j]).append('|');
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    //main method
    public static void main(String[] args) {
        Board board = new Board();

        System.out.println(board.toString());
    }

}


/*
[row][col]: here's an example of the board in 3x3
valid inputs for and nxn board i and j: 0 - (n-1)

[0][0]    [0][1]    [0][2]
[1][0]    [1][1]    [1][2]
[2][0]    [2][1]    [2][2]

*/

