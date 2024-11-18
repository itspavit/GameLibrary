package GameLogic.Checkers;

import java.util.Scanner;

public class InputManager {
    private CheckersLogic logic;

    public void addLogic(CheckersLogic logic) {
        this.logic = logic;

    }

    public void simulateInput(String str) {
        if(str.length()!=5){
            System.out.println("invalid input:"+str);
            return;
        }
        String start = str.substring(0,2);
        String end = str.substring(3,5);
        System.out.println("player input:"+str+" start:"+start+" end:"+end);
        if( !logic.playerMove(coordFromInput(start), coordFromInput(end))){
            System.out.println(str+" is not a playable move");
        }
    }
    private Coord coordFromInput(String s){
        int x = Integer.parseInt(String.valueOf(s.charAt(0)));
        int y = Integer.parseInt(String.valueOf(s.charAt(1)));
        return new Coord(x,y);
    }
}