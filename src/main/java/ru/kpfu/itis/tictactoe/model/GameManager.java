package ru.kpfu.itis.tictactoe.model;

import ru.kpfu.itis.tictactoe.model.bot.MinMaxStandardBot;
import ru.kpfu.itis.tictactoe.model.bot.RandomStandardBot;
import ru.kpfu.itis.tictactoe.model.bot.StandardBot;
import ru.kpfu.itis.tictactoe.model.controllers.GameController;
import ru.kpfu.itis.tictactoe.model.controllers.PlayerController;
import ru.kpfu.itis.tictactoe.model.errors.GameException;
import ru.kpfu.itis.tictactoe.model.game.Game;
import ru.kpfu.itis.tictactoe.model.game.StandardGame;
import ru.kpfu.itis.tictactoe.model.players.Player;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;
import ru.kpfu.itis.tictactoe.model.sessions.GameSession;
import ru.kpfu.itis.tictactoe.model.sessions.LocalGameSession;

public class GameManager {
    public static GameSession startSingleGame(Player player, PlayerRole role, boolean unbeatableBot)
            throws GameException {
        StandardBot bot = unbeatableBot ? new MinMaxStandardBot() : new RandomStandardBot();

        Game game = new StandardGame();
        GameController gameController = new GameController(game);
        PlayerController playerController = role == PlayerRole.X ?
                new PlayerController(player, bot) :
                new PlayerController(bot, player);

        gameController.setPlayerController(playerController);
        playerController.setGameController(gameController);
        game.setGameController(gameController);

        GameSession localGameSession = new LocalGameSession(game, playerController, player, role);
        GameSession botGameSession = new LocalGameSession(game, playerController, bot, role.inverse());
        bot.setSession(botGameSession);

        game.start();

        return localGameSession;
    }
}
