package ru.kpfu.itis.tictactoe.views;

import javafx.css.PseudoClass;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import ru.kpfu.itis.tictactoe.model.game.Point;

public class CellView extends StackPane {
    private Circle circle;
    private Cross cross;
    private boolean filled = false;
    private final PseudoClass FILLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("filled");
    private final PseudoClass HIGHLIGHT_PSEUDO_CLASS = PseudoClass.getPseudoClass("highlight");
    private Point coordinates;

    public CellView(int x, int y) {
        super();
        initCircle();
        initCross();
        this.getChildren().add(circle);
        this.getChildren().add(cross);
        this.getStyleClass().add("game-grid-cell");
        this.coordinates = new Point(x, y);
    }

    private void initCircle() {
        circle = new Circle(25);
        circle.getStyleClass().add("cell-circle");
        circle.setVisible(false);
    }

    private void initCross() {
        cross = new Cross(50);
        cross.getStyleClass().add("cell-cross");
        cross.setVisible(false);
    }

    public void setCircle() {
        circle.toFront();
        circle.setVisible(true);
        cross.setVisible(false);
        setFilled(true);
    }

    public void setCross() {
        cross.toFront();
        cross.setVisible(true);
        circle.setVisible(false);
        setFilled(true);
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
        this.pseudoClassStateChanged(FILLED_PSEUDO_CLASS, filled);
    }

    public void setHighlight(boolean highlight) {
        setFilled(false);
        this.pseudoClassStateChanged(HIGHLIGHT_PSEUDO_CLASS, highlight);
        circle.pseudoClassStateChanged(HIGHLIGHT_PSEUDO_CLASS, highlight);
    }

    public void clear() {
        setFilled(false);
        setHighlight(false);
        circle.setVisible(false);
        cross.setVisible(false);
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }
}
