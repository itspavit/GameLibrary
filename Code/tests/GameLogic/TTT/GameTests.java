package GameLogic.TTT;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;
public class GameTests {
    Game game;

    @Rule public ExpectedException thrown = ExpectedException.none();


    @Test
    public void invalidMove(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);
        thrown.expect(InvalidMoveException.class);
        thrown.expectMessage("Invalid move, must choose a number from 0-2.");
        game = new Game();
        game.play();
        System.setIn(sysInBackup);
    }
}
