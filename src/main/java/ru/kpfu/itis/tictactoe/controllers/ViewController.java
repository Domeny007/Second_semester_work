package ru.kpfu.itis.tictactoe.controllers;

import ru.kpfu.itis.tictactoe.model.game.Cell;

public interface ViewController {
    void newStatus(String status);

    void gameOver();

    void setCell(Cell cell);

    void highlight(Cell cell);

    void reset();
}
