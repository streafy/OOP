package ru.nsu.fit.model;

import ru.nsu.fit.model.utils.GameStatus;

import java.awt.*;
import java.util.Random;

public class Game {

    private final int gameFieldWidth;
    private final int gameFieldHeight;

    private GameStatus gameStatus = GameStatus.PLAYING;

    private final Snake snake;
    private Food food;

    private int score = 0;

    public Game(int gameFieldWidth, int gameFieldHeight, Snake snake, Food food) {
        this.gameFieldWidth = gameFieldWidth;
        this.gameFieldHeight = gameFieldHeight;

        this.snake = snake;
        this.food = food;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }

    public boolean isOutOfBounds(Point point) {
        return point.x < 0 || point.x >= gameFieldWidth || point.y < 0 || point.y >= gameFieldHeight;
    }

    public boolean isBodyCollision(Point point) {
        return snake.getBody().stream().anyMatch(bodyPoint -> bodyPoint.equals(point));
    }

    public boolean isFoodCollision(Point point) {
        return point.equals(food.getPoint());
    }

    public void moveSnake() {
        Point newHead = snake.getNewHead();

        if (isOutOfBounds(newHead) || isBodyCollision(newHead)) {
            gameStatus = GameStatus.GAME_OVER;
            return;
        }

        if (isFoodCollision(newHead)) {
            snake.eatFood(newHead);
            score++;
            this.food = getNewFood();
        }

        newHead = snake.getNewHead();
        snake.move(newHead);
    }

    private Food getNewFood() {
        Random random = new Random();
        return new Food(random.nextInt(gameFieldWidth - 2) + 1, random.nextInt(gameFieldHeight - 2) + 1);
    }
}
