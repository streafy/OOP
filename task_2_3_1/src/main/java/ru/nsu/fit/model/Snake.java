package ru.nsu.fit.model;

import ru.nsu.fit.model.utils.SnakeMoveDirection;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private static final int DEFAULT_SNAKE_SIZE = 2;
    private static final int DEFAULT_SNAKE_SPAWN_COORDINATE_X = 4;
    private static final int DEFAULT_SNAKE_SPAWN_COORDINATE_Y = 4;
    private static final SnakeMoveDirection DEFAULT_SNAKE_DIRECTION = SnakeMoveDirection.TOP;

    private final List<Point> snakeLocation = new ArrayList<>();
    private int snakeLength;

    public Snake() {
        snakeLength = DEFAULT_SNAKE_SIZE;
        snakeLocation.add(new Point(DEFAULT_SNAKE_SPAWN_COORDINATE_X, DEFAULT_SNAKE_SPAWN_COORDINATE_Y));
        snakeLocation.add(new Point(DEFAULT_SNAKE_SPAWN_COORDINATE_X, DEFAULT_SNAKE_SPAWN_COORDINATE_Y - 1));
    }

    public List<Point> getSnakeLocation() {
        return snakeLocation;
    }

    public void moveUp() {
        snakeLocation.forEach(point -> {
            if (point.y == 0) {
                point.y = 9;
            } else {
                point.y--;
            }
        });
    }
}
