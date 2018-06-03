package ru.kpfu.itis.tictactoe.model.sessions;

import ru.kpfu.itis.tictactoe.model.game.*;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;
import ru.kpfu.itis.tictactoe.model.game.Cell;
import ru.kpfu.itis.tictactoe.model.game.Field;
import ru.kpfu.itis.tictactoe.model.game.GameResult;
import ru.kpfu.itis.tictactoe.model.game.Point;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;

import java.util.List;

public interface GameSession {
    Field getGameField();

    boolean makeMove(Point coordinates);

    GameResult getGameResult();

    PlayerRole getRole();

    boolean isAlive();

    void interruptRequest();

    Cell getLastModifiedCell();

    List<Cell> getWinSequence();
}
