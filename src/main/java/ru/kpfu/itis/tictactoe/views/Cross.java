package ru.kpfu.itis.tictactoe.views;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Cross extends Group {
    Line ascendingLine;
    Line descendingLine;

    public Cross(int size) {
        super();
        ascendingLine = new Line(0, size, size, 0);
        descendingLine = new Line(0, 0, size, size);
        ascendingLine.getStyleClass().add("cell-cross-line");
        descendingLine.getStyleClass().add("cell-cross-line");
        this.getChildren().addAll(ascendingLine, descendingLine);
    }
}
