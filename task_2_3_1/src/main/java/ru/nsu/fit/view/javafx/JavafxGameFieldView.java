package ru.nsu.fit.view.javafx;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.nsu.fit.view.GameFieldView;

import java.awt.*;
import java.util.List;

public class JavafxGameFieldView implements GameFieldView {

    private static final int DEFAULT_ROW_COUNT = 20;
    private static final int DEFAULT_COL_COUNT = 20;

    private final HBox gameFieldContainer;
    private final Label gameInfoLabel;

    private int rowCount;
    private int colCount;

    public JavafxGameFieldView(HBox gameFieldContainer, Label gameInfoLabel) {
        this(gameFieldContainer, gameInfoLabel, DEFAULT_ROW_COUNT, DEFAULT_COL_COUNT);
    }

    public JavafxGameFieldView(HBox gameFieldContainer, Label gameInfoLabel, int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.gameFieldContainer = gameFieldContainer;
        this.gameInfoLabel = gameInfoLabel;

        initGameField(rowCount, colCount, 25);
    }

    private void initGameField(int rowCount, int colCount, int cellSize) {
        for (int i = 0; i < colCount; i++) {
            VBox rowContainer = new VBox();
            gameFieldContainer.getChildren().add(rowContainer);
            for (int j = 0; j < rowCount; j++) {
                Rectangle rectangle = new Rectangle(cellSize, cellSize);
                rectangle.setFill(Color.CADETBLUE);
                rowContainer.getChildren().add(rectangle);
            }
        }
    }

    @Override
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

    public void resize(int newWidth, int newHeight, int cellSize) {
        gameFieldContainer.getChildren().forEach(row -> {
            VBox r = (VBox) row;
            r.getChildren().clear();
        });
        gameFieldContainer.getChildren().clear();

        rowCount = newWidth;
        colCount = newHeight;

        initGameField(newWidth, newHeight, cellSize);
    }
}
