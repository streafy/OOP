package ru.nsu.fit.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.model.utils.GameStatus;
import ru.nsu.fit.model.utils.SnakeDirection;

public class GameTest {

    private Food food;
    private Snake snake;
    private Game game;

    @BeforeEach
    public void initGame() {
        this.food = new Food();
        this.snake = new Snake();
        this.game = new Game(20, 20, snake, food);
    }

    @Test
    public void testSnakeMoveUp() {
        int oldHeadX = snake.getHead().x;
        int oldHeadY = snake.getHead().y;

        snake.setCurrentDirection(SnakeDirection.UP);
        game.moveSnake();

        Assertions.assertEquals(oldHeadX, snake.getHead().x);
        Assertions.assertEquals(oldHeadY - 1, snake.getHead().y);
    }

    @Test
    public void testSnakeMoveDown() {
        int oldHeadX = snake.getHead().x;
        int oldHeadY = snake.getHead().y;

        snake.setCurrentDirection(SnakeDirection.DOWN);
        game.moveSnake();

        Assertions.assertEquals(oldHeadX, snake.getHead().x);
        Assertions.assertEquals(oldHeadY, snake.getHead().y);

        Assertions.assertEquals(GameStatus.GAME_OVER, game.getGameStatus());
    }

    @Test
    public void testSnakeMoveLeft() {
        int oldHeadX = snake.getHead().x;
        int oldHeadY = snake.getHead().y;

        snake.setCurrentDirection(SnakeDirection.LEFT);
        game.moveSnake();

        Assertions.assertEquals(oldHeadX - 1, snake.getHead().x);
        Assertions.assertEquals(oldHeadY, snake.getHead().y);
    }

    @Test
    public void testSnakeMoveRight() {
        int oldHeadX = snake.getHead().x;
        int oldHeadY = snake.getHead().y;

        snake.setCurrentDirection(SnakeDirection.RIGHT);
        game.moveSnake();

        Assertions.assertEquals(oldHeadX + 1, snake.getHead().x);
        Assertions.assertEquals(oldHeadY, snake.getHead().y);
    }

    @Test
    public void testSnakeEatFood() {
        int initialSnakeSize = snake.getBody().size();

        game.moveSnake();
        snake.setCurrentDirection(SnakeDirection.LEFT);
        game.moveSnake();
        game.moveSnake();

        Assertions.assertEquals(initialSnakeSize + 1, snake.getBody().size());
    }

    @Test
    public void testScoreIncrease() {
        int initialScore = game.getScore();

        game.moveSnake();
        snake.setCurrentDirection(SnakeDirection.LEFT);
        game.moveSnake();
        game.moveSnake();

        Assertions.assertEquals(initialScore + 1, game.getScore());
    }

    @Test
    public void testGameOver() {
        game.moveSnake();
        game.moveSnake();
        game.moveSnake();
        game.moveSnake();
        game.moveSnake();

        Assertions.assertEquals(GameStatus.GAME_OVER, game.getGameStatus());
    }
}
