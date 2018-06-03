package ru.kpfu.itis.tictactoe.model.controllers;

import ru.kpfu.itis.tictactoe.model.game.*;
import ru.kpfu.itis.tictactoe.model.utils.*;
import ru.kpfu.itis.tictactoe.model.game.*;
import ru.kpfu.itis.tictactoe.model.utils.StandardWinChecker;
import ru.kpfu.itis.tictactoe.model.utils.WinChecker;

import java.util.List;

public class GameController {
    private WinChecker checker;
    private Game game;
    private PlayerController playerController;

    public GameController(Game game) {
        this.game = game;
        checker = new StandardWinChecker(game.getField());
    }

    private List<Cell> checkWinSequence() {
        if (game.getMoveList().peek() != null) {
            return checker.getWinSequence(game.getMoveList().peek());
        }
        return null;
    }

    private void registerMove() {
        Move move = playerController.getBuffer();
        playerController.setBuffer(null);
        if (!game.getField().registerMove(move)) {
            throw new NullPointerException();
        }
        game.getMoveList().push(move);
    }

    public void xPlayerMove() {
        try {
            playerController.xPlayerMove();
            if (game.getGameState() != GameState.INTERRUPTED) {
                registerMove();
                playerController.oPlayerRefreshNotify();
                if (checkWin()) {
                    game.setGameState(GameState.OVER);
                } else if (checkDraw()) {
                    game.setGameState(GameState.OVER);
                } else {
                    game.setGameState(GameState.O_PLAYER_MOVE);
                }
            }
        } catch (InterruptedException e) {
            interrupt();
            e.printStackTrace();
        }
    }

    public void oPlayerMove() {
        try {
            playerController.oPlayerMove();
            if (game.getGameState() != GameState.INTERRUPTED) {
                registerMove();
                playerController.xPlayerRefreshNotify();
                if (checkWin()) {
                    game.setGameState(GameState.OVER);
                } else if (checkDraw()) {
                    game.setGameState(GameState.OVER);
                } else {
                    game.setGameState(GameState.X_PLAYER_MOVE);
                }
            }
        } catch (InterruptedException e) {
            interrupt();
            e.printStackTrace();
        }
    }

    private boolean checkDraw() {
        if (game.getField().isEmpty()) {
            game.setResult(GameResult.DRAW);
            return true;
        }
        return false;
    }

    private boolean checkWin() {
        List<Cell> winSeq = checkWinSequence();
        if (winSeq != null) {
            game.setWinSequence(winSeq);
            game.setResult(winSeq.get(0).getState() == CellState.X ? GameResult.X_WIN : GameResult.O_WIN);
            return true;
        }
        return false;
    }

    public void interrupt() {
        playerController.interruptNotify();
        game.setGameState(GameState.INTERRUPTED);
    }

    public void gameOver() {
        if (game.getGameState() != GameState.INTERRUPTED) {
            playerController.gameOverNotify();
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
