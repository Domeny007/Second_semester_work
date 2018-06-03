package ru.kpfu.itis.tictactoe.model;

import ru.kpfu.itis.tictactoe.model.game.Cell;
import ru.kpfu.itis.tictactoe.model.game.CellState;
import ru.kpfu.itis.tictactoe.model.game.Field;
import ru.kpfu.itis.tictactoe.model.game.Move;
import ru.kpfu.itis.tictactoe.model.game.Point;
import ru.kpfu.itis.tictactoe.model.utils.StandardWinChecker;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WinCheckerTest {
    private int winNumberOfCells;
    private Field field;

    @Before
    public void init() {
        winNumberOfCells = 3;
        field = new Field(3);
    }

    private Move createMove(int x, int y, CellState cellState) {
        Move move = new Move();
        move.setCellCoordinates(new Point(x, y));
        move.setCellState(cellState);
        return move;
    }

    @Test
    public void getWinSequenceVerticalTest() {
        field.registerMove(createMove(0, 0, CellState.X));
        field.registerMove(createMove(0, 1, CellState.X));
        field.registerMove(createMove(0, 2, CellState.X));

        StandardWinChecker checker = new StandardWinChecker(field);
        Move move = createMove(0, 2, CellState.X);
        List<Cell> result = checker.getWinSequence(move);
        assertNotNull(result);
    }

    @Test
    public void getWinSequenceHorizontalTest() {
        field.registerMove(createMove(0, 0, CellState.X));
        field.registerMove(createMove(1, 0, CellState.X));
        field.registerMove(createMove(2, 0, CellState.X));

        StandardWinChecker checker = new StandardWinChecker(field);
        Move move = createMove(0, 0, CellState.X);
        List<Cell> result = checker.getWinSequence(move);
        assertNotNull(result);
    }

    @Test
    public void getWinSequenceAscendingDiagonalTest() {
        field.registerMove(createMove(0, 0, CellState.X));
        field.registerMove(createMove(1, 1, CellState.X));
        field.registerMove(createMove(2, 2, CellState.X));

        StandardWinChecker checker = new StandardWinChecker(field);
        Move move = createMove(0, 0, CellState.X);
        List<Cell> result = checker.getWinSequence(move);
        assertNotNull(result);
    }

    @Test
    public void getWinSequenceDescendingDiagonalTest() {
        field.registerMove(createMove(0, 2, CellState.X));
        field.registerMove(createMove(1, 1, CellState.X));
        field.registerMove(createMove(2, 0, CellState.X));

        StandardWinChecker checker = new StandardWinChecker(field);
        Move move = createMove(1, 1, CellState.X);
        List<Cell> result = checker.getWinSequence(move);
        assertNotNull(result);
    }
}