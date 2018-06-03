package ru.kpfu.itis.tictactoe.model.utils;

import ru.kpfu.itis.tictactoe.model.game.Cell;
import ru.kpfu.itis.tictactoe.model.game.Move;
import ru.kpfu.itis.tictactoe.model.game.Cell;
import ru.kpfu.itis.tictactoe.model.game.Move;

import java.util.List;

public interface WinChecker {
    List<Cell> getWinSequence(Move move);
}
