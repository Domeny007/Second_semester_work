package ru.kpfu.itis.tictactoe.model.bot;

import junit.framework.AssertionFailedError;
import ru.kpfu.itis.tictactoe.model.game.CellState;
import ru.kpfu.itis.tictactoe.model.game.Field;
import ru.kpfu.itis.tictactoe.model.game.Move;
import ru.kpfu.itis.tictactoe.model.game.Point;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class StandardBotTest {
    private Field field;
    private MinMaxStandardBot bot;

    @Before
    public void init() {
        field = new Field(3);
        bot = new MinMaxStandardBot();

    }

    @Test
    public void makeMoveTest1() {
        bot.setBotRole(PlayerRole.O);
        bot.setBotCellState(CellState.O);
        field.registerMove(new Move(new Point(1, 0), CellState.X));
        field.registerMove(new Move(new Point(1, 1), CellState.X));
        field.registerMove(new Move(new Point(0, 0), CellState.O));

        Point p = bot.minMaxMove(field);
        System.out.println(field);
        assertEquals(new Point(1, 2), p);
    }

    @Test
    public void makeMoveTest2() {
        bot.setBotRole(PlayerRole.O);
        bot.setBotCellState(CellState.O);
        field.registerMove(new Move(new Point(1, 0), CellState.X));
        field.registerMove(new Move(new Point(1, 1), CellState.X));
        field.registerMove(new Move(new Point(2, 0), CellState.X));
        field.registerMove(new Move(new Point(0, 0), CellState.O));
        field.registerMove(new Move(new Point(1, 2), CellState.O));

        Point p = bot.minMaxMove(field);
        assertEquals(new Point(0, 2), p);
    }

    @Test
    public void makeMoveTestSimple1() {
        bot.setBotRole(PlayerRole.X);
        bot.setBotCellState(CellState.X);
        field.registerMove(new Move(new Point(0, 0), CellState.O));
        field.registerMove(new Move(new Point(2, 2), CellState.O));
        field.registerMove(new Move(new Point(1, 2), CellState.O));
        field.registerMove(new Move(new Point(2, 0), CellState.X));
        field.registerMove(new Move(new Point(2, 1), CellState.X));
        field.registerMove(new Move(new Point(0, 1), CellState.X));
        System.out.println(field);

        Point p = bot.minMaxMove(field);
        assertEquals(new Point(1, 1), p);
    }
}