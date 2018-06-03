package ru.kpfu.itis.tictactoe;

import javafx.application.Platform;
import ru.kpfu.itis.tictactoe.controllers.ViewController;
import ru.kpfu.itis.tictactoe.model.GameManager;
import ru.kpfu.itis.tictactoe.model.errors.GameException;
import ru.kpfu.itis.tictactoe.model.game.Cell;
import ru.kpfu.itis.tictactoe.model.game.GameResult;
import ru.kpfu.itis.tictactoe.model.game.Point;
import ru.kpfu.itis.tictactoe.model.players.Player;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;
import ru.kpfu.itis.tictactoe.model.sessions.GameSession;

import java.util.List;

public class GameClient implements Player {
    private GameSession gameSession;
    private PlayerRole role;
    private ViewController viewController;

    public boolean createNewGame(PlayerRole role, boolean unbeatableBot) {
        try {
            if (gameSession != null && gameSession.isAlive()) {
                gameSession.interruptRequest();
            }
            gameSession = GameManager.startSingleGame(this, role, unbeatableBot);
            this.role = gameSession.getRole();
            viewController.newStatus("");
            return true;
        } catch (GameException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void makeMoveNotify() {
        Platform.runLater(() -> makeMoveNotifyHandler());
    }

    private void makeMoveNotifyHandler() {
        viewController.newStatus("ваш ход");
    }

    @Override
    public void gameOverNotify() {
        Platform.runLater(() -> gameOverNotifyHandler());
    }

    private void gameOverNotifyHandler() {
        GameResult result = gameSession.getGameResult();
        switch (result) {
            case X_WIN:
                viewController.newStatus(role == PlayerRole.X ? "Вы выиграли" : "Вы проиграли");
                showWinSequence();
                break;

            case O_WIN:
                viewController.newStatus(role == PlayerRole.O ? "Вы выиграли" : "Вы проиграли");
                showWinSequence();
                break;

            case DRAW:
                viewController.newStatus("Ничья");
                break;

            case UNDEFINED:
                viewController.newStatus("Ошибка");
                break;

            default:
                viewController.newStatus("Ошибка");
                break;
        }
        viewController.gameOver();
    }

    private void showWinSequence() {
        List<Cell> cells = gameSession.getWinSequence();
        if (cells != null) {
            cells.forEach(cell -> viewController.highlight(cell));
        }
    }

    @Override
    public void interruptNotify() {
        Platform.runLater(() -> interruptNotifyHandler());
    }

    private void interruptNotifyHandler() {

    }

    @Override
    public void refreshFieldNotify() {
        Platform.runLater(() -> refreshFieldNotifyHandler());
    }

    private void refreshFieldNotifyHandler() {
        Cell modifiedCell = gameSession.getLastModifiedCell();
        if (modifiedCell != null) {
            viewController.setCell(modifiedCell);
        }
    }

    public boolean makeMove(Point c) {
        if (gameSession == null || !gameSession.isAlive()) {
            return false;
        }
        return gameSession.makeMove(c);
    }

    public PlayerRole getRole() {
        return role;
    }

    public ViewController getViewController() {
        return viewController;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public void stop() {
        if (gameSession != null && gameSession.isAlive()) {
            gameSession.interruptRequest();
        }
    }
}
