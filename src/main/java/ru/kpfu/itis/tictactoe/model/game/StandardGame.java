package ru.kpfu.itis.tictactoe.model.game;

public class StandardGame extends Game {
    public StandardGame() {
        super(new GameSettings(3, 3));
        gameState = GameState.WAIT_TO_START;
    }

}
