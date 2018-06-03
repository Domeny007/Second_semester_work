package ru.kpfu.itis.tictactoe.model.sessions;

import ru.kpfu.itis.tictactoe.model.controllers.PlayerController;
import ru.kpfu.itis.tictactoe.model.game.*;
import ru.kpfu.itis.tictactoe.model.players.Player;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;
import ru.kpfu.itis.tictactoe.model.controllers.PlayerController;
import ru.kpfu.itis.tictactoe.model.game.*;
import ru.kpfu.itis.tictactoe.model.players.Player;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;

import java.util.List;

public class LocalGameSession implements GameSession {
    private final Game game;
    private final PlayerController playerController;
    private final Player player;
    private final PlayerRole playerRole;

    public LocalGameSession(Game game, PlayerController playerController, Player player, PlayerRole playerRole) {
        this.game = game;
        this.playerController = playerController;
        this.player = player;
        this.playerRole = playerRole;
    }

    @Override
    public final Field getGameField() {
        return game.getField();
    }

    @Override
    public boolean makeMove(Point coordinates) {
        if ((game.getGameState() == GameState.X_PLAYER_MOVE && playerRole == PlayerRole.X) ||
                (game.getGameState() == GameState.O_PLAYER_MOVE && playerRole == PlayerRole.O)) {
            return playerController.move(player, coordinates);
        }
        return false;
    }


    @Override
    public GameResult getGameResult() {
        return game.getResult();
    }

    @Override
    public PlayerRole getRole() {
        return playerRole;
    }

    @Override
    public boolean isAlive() {
        if (game.getGameState() == GameState.X_PLAYER_MOVE ||
                game.getGameState() == GameState.O_PLAYER_MOVE) {
            return true;
        }
        return false;
    }

    @Override
    public void interruptRequest() {
        playerController.interruptRequest(player);
    }

    @Override
    public Cell getLastModifiedCell() {
        if (game.getMoveList() != null && game.getMoveList().peek() != null) {
            Point c = game.getMoveList().peek().getCellCoordinates();
            return game.getField().getCell(c);
        }
        return null;
    }

    @Override
    public List<Cell> getWinSequence() {
        return game.getWinSequence();
    }
}
