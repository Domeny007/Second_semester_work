package ru.kpfu.itis.tictactoe.model.players;

public enum PlayerRole {
    X, O;

    public PlayerRole inverse() {
        return this == X ? O : X;
    }
}
