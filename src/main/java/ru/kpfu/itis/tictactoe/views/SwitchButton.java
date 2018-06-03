package ru.kpfu.itis.tictactoe.views;

import javafx.scene.control.Button;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;

public class SwitchButton extends Button {
    private boolean x = true;
    private final String X_CLASS = "switch-button-x";
    private final String O_CLASS = "switch-button-o";

    public SwitchButton() {
        super();
        setX();
        getStyleClass().add("switch-button");
        setOnMouseClicked(event -> toSwitch());
    }

    public void toSwitch() {
        clearStyleClasses();
        x = !x;
        if (x) {
            setX();
        } else {
            setO();
        }
    }

    public void setX() {
        clearStyleClasses();
        getStyleClass().add(X_CLASS);
        setText("X");
    }

    public void setO() {
        clearStyleClasses();
        getStyleClass().add(O_CLASS);
        setText("O");
    }

    public PlayerRole getRole() {
        if (x) {
            return PlayerRole.X;
        } else {
            return PlayerRole.O;
        }
    }

    private void clearStyleClasses() {
        getStyleClass().remove(X_CLASS);
        getStyleClass().remove(O_CLASS);
    }

}
