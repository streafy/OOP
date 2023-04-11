package ru.nsu.fit.model;

public class GameField {

    private int gameScore = 0;
    private final Snake snake;
    private Food food;

    public GameField(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
    }
}
