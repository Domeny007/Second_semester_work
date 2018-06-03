package ru.kpfu.itis.tictactoe.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.kpfu.itis.tictactoe.GameClient;
import ru.kpfu.itis.tictactoe.controllers.MainViewController;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private Parent root;
    private MainViewController mainViewController;
    private GameClient gameClient;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Крестики-Нолики");

        try {
            loadFxml();
            initRoot();
            initMainController();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    private void loadFxml() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/views/mainView.fxml"));
        root = loader.load();
        mainViewController = loader.getController();
    }

    private void initRoot() {
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initMainController() {
        gameClient = new GameClient();
        gameClient.setViewController(mainViewController);
        mainViewController.setGameClient(gameClient);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        gameClient.stop();
    }
}
