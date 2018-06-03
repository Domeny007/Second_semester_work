package ru.kpfu.itis.tictactoe.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ru.kpfu.itis.tictactoe.GameClient;
import ru.kpfu.itis.tictactoe.model.game.Cell;
import ru.kpfu.itis.tictactoe.model.game.CellState;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;
import ru.kpfu.itis.tictactoe.views.CellView;
import ru.kpfu.itis.tictactoe.views.SwitchButton;
import ru.kpfu.itis.tictactoe.GameClient;
import ru.kpfu.itis.tictactoe.views.CellView;
import ru.kpfu.itis.tictactoe.views.SwitchButton;

import java.util.ArrayList;

public class MainViewController implements ViewController {
    @FXML
    private ToolBar toolBar;

    @FXML
    private Button startButton;

    @FXML
    private GridPane fieldGridPane;

    @FXML
    private HBox statusBar;

    @FXML
    private Label statusLabel;

    private SwitchButton switchButton;

    private ArrayList<CellView> cellViews = new ArrayList<>();

    private ToggleButton unbeatableBotToggleButton;

    private GameClient gameClient;

    @FXML
    public void initialize() {
        initToolBar();
        initCellViews();
        initStatusBar();
    }

    private void initToolBar() {
        switchButton = new SwitchButton();
        startButton.setOnMouseClicked(event -> newGame());
        unbeatableBotToggleButton = new ToggleButton("непобедимый");
        unbeatableBotToggleButton.setSelected(true);

        toolBar.getItems().addAll(switchButton, unbeatableBotToggleButton);
    }

    private void newGame() {
        if (gameClient.createNewGame(switchButton.getRole(), unbeatableBotToggleButton.isSelected())) {
            fieldGridPane.setDisable(false);
            clearField();
        }
    }

    private void initCellViews() {
        for (int i = 0; i < fieldGridPane.getColumnConstraints().size(); i++) {
            for (int j = 0; j < fieldGridPane.getRowConstraints().size(); j++) {
                CellView cell = new CellView(i, j);
                cellViews.add(cell);
                fieldGridPane.add(cell, i, j);
                cell.setOnMouseClicked(event -> {
                    if (gameClient.makeMove(cell.getCoordinates())) {
                        if (gameClient.getRole() == PlayerRole.X) {
                            cell.setCross();
                        } else {
                            cell.setCircle();
                        }
                    }
                });
            }
        }
        fieldGridPane.setDisable(true);
    }

    private void clearField() {
        cellViews.forEach(cellView -> cellView.clear());
    }

    private void initStatusBar() {
        statusLabel.setText("Для начала нажмите \"Новая игра\"");
    }

    public GameClient getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void newStatus(String status) {
        statusLabel.setText(status);
    }

    @Override
    public void gameOver() {
        fieldGridPane.setDisable(true);
    }

    @Override
    public void setCell(Cell cell) {
        int x = cell.getCoordinates().getX();
        int y = cell.getCoordinates().getY();
        if (cell.getState() == CellState.X) {
            cellViews.get(x*3 + y).setCross();
        } else if (cell.getState() == CellState.O) {
            cellViews.get(x*3 + y).setCircle();
        }
    }

    @Override
    public void highlight(Cell cell) {
        int x = cell.getCoordinates().getX();
        int y = cell.getCoordinates().getY();
        cellViews.get(x*3 + y).setHighlight(true);
    }

    @Override
    public void reset() {
        clearField();
    }

}
