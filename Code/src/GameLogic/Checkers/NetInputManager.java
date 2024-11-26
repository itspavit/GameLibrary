package GameLogic.Checkers;

import java.util.Scanner;

public class NetInputManager {

    private CheckersLogic logic;

    public void addLogic(CheckersLogic logic) {
        this.logic = logic;
    }

    public boolean simulateInput(String str) {
        if (str.length() != 5) {
            System.out.println("invalid input:" + str);
            return false;
        }
        String start = str.substring(0, 2);
        String end = str.substring(3, 5);
        System.out.println("opponent input:" + str + " start:" + start + " end:" + end);
        if (!logic.opponentMove(coordFromInput(start), coordFromInput(end))) {
            System.out.println(str + " is not a playable move");
            return false;
        }
        return true;
    }

    private Coord coordFromInput(String s) {
        int x = Integer.parseInt(String.valueOf(s.charAt(0)));
        int y = Integer.parseInt(String.valueOf(s.charAt(1)));
        return new Coord(x, y);
    }

}
