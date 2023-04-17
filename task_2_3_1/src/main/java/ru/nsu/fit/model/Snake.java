package ru.nsu.fit.model;

import ru.nsu.fit.model.utils.SnakeDirection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private static final int DEFAULT_SNAKE_SPAWN_COORDINATE_X = 4;
    private static final int DEFAULT_SNAKE_SPAWN_COORDINATE_Y = 4;

    private final List<Point> body = new ArrayList<>();

    private SnakeDirection currentDirection = SnakeDirection.UP;

    public Snake() {

        body.add(new Point(DEFAULT_SNAKE_SPAWN_COORDINATE_X, DEFAULT_SNAKE_SPAWN_COORDINATE_Y));
        body.add(new Point(DEFAULT_SNAKE_SPAWN_COORDINATE_X, DEFAULT_SNAKE_SPAWN_COORDINATE_Y + 1));
    }

    public List<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return body.get(0);
    }

    public SnakeDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(SnakeDirection currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void move(Point newHead) {
        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    public void eatFood(Point newHead) {
        body.add(0, newHead);
        System.out.println(body.size());
    }

    public Point getNewHead() {
        Point oldHead = getHead();
        return new Point(oldHead.x + currentDirection.getDx(), oldHead.y + currentDirection.getDy());
    }
}
