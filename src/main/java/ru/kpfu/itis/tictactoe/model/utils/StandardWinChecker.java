package ru.kpfu.itis.tictactoe.model.utils;

import ru.kpfu.itis.tictactoe.model.game.Cell;
import ru.kpfu.itis.tictactoe.model.game.CellState;
import ru.kpfu.itis.tictactoe.model.game.Field;
import ru.kpfu.itis.tictactoe.model.game.Move;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;
import ru.kpfu.itis.tictactoe.model.game.Cell;
import ru.kpfu.itis.tictactoe.model.game.CellState;
import ru.kpfu.itis.tictactoe.model.game.Field;
import ru.kpfu.itis.tictactoe.model.game.Move;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;

import java.util.ArrayList;
import java.util.List;

public class StandardWinChecker implements WinChecker {
    private Field field;

    public StandardWinChecker() {
    }

    public StandardWinChecker(Field field) {
        this.field = field;
    }

    public PlayerRole getWinner(Move move) {
        List<Cell> winSequence = getWinSequence(move);
        if (winSequence == null) {
            return null;
        }
        return winSequence.get(0).getState() == CellState.X ? PlayerRole.X : PlayerRole.O;
    }

    @Override
    public List<Cell> getWinSequence(Move move) {
        final int x = move.getCellCoordinates().getX();
        final int y = move.getCellCoordinates().getY();
        List<Cell> winSequence = null;
        if ((winSequence = horizontalCheck(x, y)) != null){} else
        if ((winSequence = verticalCheck(x, y)) != null){} else
        if ((winSequence = ascendingDiagonalCheck(x, y)) != null) {} else
        if ((winSequence = descendingDiagonalCheck(x, y)) != null) {}
        return winSequence;
    }

    private List<Cell> horizontalCheck(int x, int y) {
        ArrayList<Cell> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(field.getCell(i, y));
        }
        return checkList(list) ? list : null;
    }

    private List<Cell> verticalCheck(int x, int y) {
        ArrayList<Cell> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(field.getCell(x, i));
        }
        return checkList(list) ? list : null;
    }

    private List<Cell> ascendingDiagonalCheck(int x, int y) {
        if (x == y) {
            ArrayList<Cell> list = new ArrayList<>();
            for (int i = 0, j = 0; i < 3; i++, j++) {
                list.add(field.getCell(i, j));
            }
            return checkList(list) ? list : null;
        }
        return null;
    }

    private List<Cell> descendingDiagonalCheck(int x, int y) {
        if (y - 2 == x || x - 2 == y || (x == 2 && y == 2)) {
            ArrayList<Cell> list = new ArrayList<>();
            for (int i = 0, j = 2; i < 3; i++, j--) {
                list.add(field.getCell(i, j));
            }
            return checkList(list) ? list : null;
        }
        return null;
    }

    private boolean checkList(ArrayList<Cell> list) {
        if (list.get(0).getState() == CellState.EMPTY) {
            return false;
        }
        if (list.get(0).getState() == list.get(1).getState() &&
                list.get(1).getState() == list.get(2).getState()) {
            return true;
        }
        return false;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
