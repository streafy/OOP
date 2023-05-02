package ru.nsu.fit.view.console;

import ru.nsu.fit.view.GameFieldView;

import java.awt.*;
import java.util.List;

public class ConsoleGameFiledView implements GameFieldView {

    private static final int DEFAULT_ROW_COUNT = 20;
    private static final int DEFAULT_COL_COUNT = 20;

    private final int rowCount;
    private final int colCount;

    public ConsoleGameFiledView() {
        this(DEFAULT_ROW_COUNT, DEFAULT_COL_COUNT);
    }

    public ConsoleGameFiledView(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    @Override
    public void renderGameField(List<Point> snakeBody, Point food) {
        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < colCount; y++) {
                Point point = new Point(x, y);
                String pointSymbol = ".";
                if (point.equals(food)) {
                    pointSymbol = "*";
                }
                if (snakeBody.stream().anyMatch(point::equals)) {
                    pointSymbol = "#";
                }
                System.out.print(pointSymbol);
            }
            System.out.println();
        }
    }

    public void showScore(int score) {
        System.out.println("Score: " + score);
    }

    public void showGameOverScreen(int score) {
        System.out.println("Game Over!");
        System.out.println("Your Score: " + score);
    }
}
