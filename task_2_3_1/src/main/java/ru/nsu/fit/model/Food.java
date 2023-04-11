package ru.nsu.fit.model;

import java.awt.*;

public class Food {

    private static final int DEFAULT_FOOD_COORDINATE_X = 2;
    private static final int DEFAULT_FOOD_COORDINATE_Y = 3;

    private final Point location;

    public Food() {
        this.location = new Point(DEFAULT_FOOD_COORDINATE_X, DEFAULT_FOOD_COORDINATE_Y);
    }

    public Food(int x, int y) {
        this.location = new Point(x, y);
    }
}
