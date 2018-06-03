package ru.kpfu.itis.tictactoe.model.game;

public class Cell {
    private Point coordinates;
    private CellState state;
    {
        state = CellState.EMPTY;
    }

    public Cell(Cell cell) {
        coordinates = new Point(cell.getCoordinates());
        state = cell.getState();
    }

    public Cell(Point coordinates) {
        this.coordinates = coordinates;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public boolean isEmpty() {
        return state == CellState.EMPTY;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "coordinates=" + coordinates +
                ", state=" + state +
                '}';
    }
}
