package ru.kpfu.itis.tictactoe.model.game;

import ru.kpfu.itis.tictactoe.model.players.Player;

public class Move {
    private Point cellCoordinates;
    private CellState cellState;
    private Player source;

    public Move() {
    }

    public Move(Point cellCoordinates, CellState cellState) {
        this.cellCoordinates = cellCoordinates;
        this.cellState = cellState;
    }

    public Point getCellCoordinates() {
        return cellCoordinates;
    }

    public void setCellCoordinates(Point cellCoordinates) {
        this.cellCoordinates = cellCoordinates;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getSource() {
        return source;
    }

    public void setSource(Player source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return cellState + " " + cellCoordinates;
    }
}
