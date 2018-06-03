package ru.kpfu.itis.tictactoe.model.game;

public enum CellState {
    EMPTY, X, O;

    public CellState inverse() {
        return this == X ? O : X;
    }
}
