package ru.kpfu.itis.tictactoe.model.controllers;

import ru.kpfu.itis.tictactoe.model.game.CellState;
import ru.kpfu.itis.tictactoe.model.game.Point;
import ru.kpfu.itis.tictactoe.model.players.Player;
import ru.kpfu.itis.tictactoe.model.game.Move;
import ru.kpfu.itis.tictactoe.model.game.CellState;
import ru.kpfu.itis.tictactoe.model.game.Move;
import ru.kpfu.itis.tictactoe.model.game.Point;
import ru.kpfu.itis.tictactoe.model.players.Player;

public class PlayerController {
    private Player xPlayer;
    private Player oPlayer;
    private Move buffer;
    private boolean interruptRequest;
    private GameController gameController;

    public boolean move(Player player, Point coordinates) {
        if (gameController.getGame().getField().isEmpty(coordinates)) {
            Move move = new Move();
            move.setCellState(player == xPlayer ? CellState.X : CellState.O);
            move.setCellCoordinates(coordinates);
            move.setSource(player);
            buffer = move;
            return true;
        }
        return false;
    }

    public PlayerController() {
    }

    public PlayerController(Player xPlayer, Player oPlayer) {
        this.xPlayer = xPlayer;
        this.oPlayer = oPlayer;
    }

    public void xPlayerMove() throws InterruptedException {
        xPlayer.makeMoveNotify();
        waitMove();
    }

    public void oPlayerMove() throws InterruptedException {
        oPlayer.makeMoveNotify();
        waitMove();
    }

    private void waitMove() throws InterruptedException  {
        while(buffer == null) {
            if (isInterrupted()) {
                break;
            }
            Thread.sleep(100);
        }
    }

    public Player getXPlayer() {
        return xPlayer;
    }

    public void setXPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

    public Player getOPlayer() {
        return oPlayer;
    }

    public void setOPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    public void interruptRequest(Player player) {
        interruptRequest = true;
    }

    public void interruptNotify() {
        xPlayer.interruptNotify();
        oPlayer.interruptNotify();
    }

    private boolean isInterrupted() {
        if (interruptRequest) {
            gameController.interrupt();
            return true;
        }
        return false;
    }

    public void gameOverNotify() {
        xPlayer.gameOverNotify();
        oPlayer.gameOverNotify();
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public Move getBuffer() {
        return buffer;
    }

    public void setBuffer(Move buffer) {
        this.buffer = buffer;
    }

    public void xPlayerRefreshNotify() {
        xPlayer.refreshFieldNotify();
    }

    public void oPlayerRefreshNotify() {
        oPlayer.refreshFieldNotify();
    }
}
