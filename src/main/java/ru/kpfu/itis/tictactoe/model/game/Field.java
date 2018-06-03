package ru.kpfu.itis.tictactoe.model.game;

import java.util.Arrays;

public class Field {
    private final int size;
    private Cell[] cells;
    private int emptyCellCount;

    public Field(int size) {
        this.size = size;
        cells = new Cell[size*size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i*size + j] = new Cell(new Point(i, j));
            }
        }
        emptyCellCount = size * size;
    }

    public Field(Field field) {
        size = field.getSize();
        emptyCellCount = field.getEmptyCellCount();
        cells = Arrays.stream(field.getCells())
                .map(cell -> cell == null ? null : new Cell(cell))
                .toArray(Cell[]::new);
    }

    public boolean registerMove(Move move) {
        Cell cell = getCell(move.getCellCoordinates());
        if (cell == null) {
            return false;
        }
        cell.setState(move.getCellState());
        emptyCellCount--;
        return true;
    }

    public Cell getCell(Point coordinates) {
        if (coordinates == null) {
            return null;
        }
        return getCell(coordinates.getX(), coordinates.getY());
    }

    public Cell getCell(int x, int y) {
        if (x >= size || y >= size) {
            return null;
        }
        return cells[x*size + y];
    }

    public int getSize() {
        return size;
    }

    public Cell[] getCells() {
        return cells;
    }

    public boolean isEmpty() {
        return emptyCellCount <= 0;
    }

    public boolean isEmpty(Point c) {
        Cell cell = getCell(c);
        return cell.isEmpty();
    }

    public int getEmptyCellCount() {
        return emptyCellCount;
    }

    public boolean setCell(Point point, CellState state) {
        Cell cell = getCell(point);
        if (cell == null) {
            return false;
        }
        if (cell.isEmpty()) {
            if (state != CellState.EMPTY) {
                emptyCellCount--;
            }
        } else {
            if (state == CellState.EMPTY) {
                emptyCellCount++;
            }
        }
        cell.setState(state);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                Cell cell = getCell(i, j);
                out.append(cell.getState() == CellState.EMPTY ? "_" : cell.getState());
                out.append(" ");
            }
            out.append("\n");
        }
        return out.toString();
    }
}
