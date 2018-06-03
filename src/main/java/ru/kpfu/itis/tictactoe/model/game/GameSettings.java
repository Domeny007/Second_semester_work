package ru.kpfu.itis.tictactoe.model.game;

public class GameSettings {
    private int winNumberOfCells;
    private int fieldSize;

    public GameSettings(int winNumberOfCells, int fieldSize) {
        this.winNumberOfCells = winNumberOfCells;
        this.fieldSize = fieldSize;
    }

    public GameSettings(GameSettings settings) {
        this.winNumberOfCells = settings.winNumberOfCells;
        this.fieldSize = settings.fieldSize;
    }

    public int getWinNumberOfCells() {
        return winNumberOfCells;
    }

    public void setWinNumberOfCells(int winNumberOfCells) {
        this.winNumberOfCells = winNumberOfCells;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }
}
