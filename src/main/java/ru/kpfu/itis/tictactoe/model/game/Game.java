package ru.kpfu.itis.tictactoe.model.game;

import ru.kpfu.itis.tictactoe.model.controllers.GameController;

import java.util.LinkedList;
import java.util.List;

public abstract class Game extends Thread {
    protected Field field;
    protected LinkedList<Move> moveList;
    protected final GameSettings settings;
    protected GameController gameController;
    protected GameResult result;
    protected GameState gameState;
    protected List<Cell> winSequence;

    public Game(GameSettings settings) {
        this.settings = new GameSettings(settings);
        this.field = new Field(settings.getFieldSize());
        result = GameResult.UNDEFINED;
        moveList = new LinkedList<>();
    }

    @Override
    public void run() {
        while (gameState != GameState.INTERRUPTED && gameState != GameState.OVER) {
            if (isInterrupted()) {
                gameController.interrupt();
            }
            switch (gameState) {
                case WAIT_TO_START:
                    gameState = GameState.X_PLAYER_MOVE;

                case X_PLAYER_MOVE:
                    gameController.xPlayerMove();
                    break;

                case O_PLAYER_MOVE:
                    gameController.oPlayerMove();
                    break;

                default:
                    break;
            }
        }
        gameController.gameOver();
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public LinkedList<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(LinkedList<Move> moveList) {
        this.moveList = moveList;
    }

    public GameSettings getSettings() {
        return settings;
    }

    public GameResult getResult() {
        return result;
    }

    public void setResult(GameResult result) {
        this.result = result;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Cell> getWinSequence() {
        return winSequence;
    }

    public void setWinSequence(List<Cell> winSequence) {
        this.winSequence = winSequence;
    }
}
