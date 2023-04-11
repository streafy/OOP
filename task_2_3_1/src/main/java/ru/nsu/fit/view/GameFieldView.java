package ru.nsu.fit.view;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

public class GameFieldView {

    private HBox rootContainer;

    private static final int DEFAULT_ROW_COUNT = 10;
    private static final int DEFAULT_COL_COUNT = 10;

    public GameFieldView(Scene scene) {
        this(scene, DEFAULT_ROW_COUNT, DEFAULT_COL_COUNT);
    }

    public GameFieldView(Scene scene, int rowCount, int colCount) {
        rootContainer = (HBox) scene.getRoot();
        initGameField(rowCount, colCount);
    }

    private void initGameField(int rowCount, int colCount) {
        for (int i = 0; i < colCount; i++) {
            VBox rowContainer = new VBox();
            rootContainer.getChildren().add(rowContainer);
            for (int j = 0; j < rowCount; j++) {
                Rectangle rectangle = new Rectangle(50, 50);
                rectangle.setFill(Color.CADETBLUE);
                rowContainer.getChildren().add(rectangle);
            }
        }
    }

    public void renderSnake(List<Point> snakeCoordinates) {
        snakeCoordinates.forEach(point ->  changeCellColor(point.x, point.y, Color.GREEN));
    }

    public void changeCellColor(int x, int y, Color color) {
        VBox column = (VBox) rootContainer.getChildren().get(x);
        Rectangle cell = (Rectangle) column.getChildren().get(y);
        cell.setFill(color);
    }
}
