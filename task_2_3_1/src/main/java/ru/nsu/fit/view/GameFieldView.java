package ru.nsu.fit.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.List;

public class GameFieldView {

    private static final int DEFAULT_ROW_COUNT = 10;
    private static final int DEFAULT_COL_COUNT = 10;

    private final Scene scene;
    private final Pane rootContainer;
    private final HBox gameFieldContainer;

    private final int rowCount;
    private final int colCount;

    private final Label gameInfoLabel;

    public GameFieldView(Scene scene) {
        this(scene, DEFAULT_ROW_COUNT, DEFAULT_COL_COUNT);
    }

    public GameFieldView(Scene scene, int rowCount, int colCount) {
        this.scene = scene;
        this.rowCount = rowCount;
        this.colCount = colCount;

        rootContainer = (Pane) scene.getRoot();
        gameFieldContainer = (HBox) rootContainer.getChildren().get(0);

        gameInfoLabel = (Label) rootContainer.lookup("#gameInfoLabel");

        initGameField(rowCount, colCount);
    }

    public Scene getScene() {
        return scene;
    }

    private void initGameField(int rowCount, int colCount) {
        for (int i = 0; i < colCount; i++) {
            VBox rowContainer = new VBox();
            gameFieldContainer.getChildren().add(rowContainer);
            for (int j = 0; j < rowCount; j++) {
                Rectangle rectangle = new Rectangle(50, 50);
                rectangle.setFill(Color.CADETBLUE);
                rowContainer.getChildren().add(rectangle);
            }
        }
    }

    public void renderGameField(List<Point> snakeBody, Point food) {
        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < colCount; y++) {
                changeCellColor(x, y, Color.CADETBLUE);
            }
        }
        renderSnake(snakeBody);
        changeCellColor(food.x, food.y, Color.RED);
    }

    public void renderSnake(List<Point> snakeCoordinates) {
        snakeCoordinates.forEach(point -> changeCellColor(point.x, point.y, Color.GREEN));
    }

    private void changeCellColor(int x, int y, Color color) {
        VBox column = (VBox) gameFieldContainer.getChildren().get(x);
        Rectangle cell = (Rectangle) column.getChildren().get(y);
        cell.setFill(color);
    }

    public void showScore(int score) {
        gameInfoLabel.setText("Score: " + score);
    }

    public void showGameOverScreen(int score) {
        gameInfoLabel.setText("Game Over!\nYour Score: " + score);
    }
}
