package ru.nsu.fit.presenter;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import ru.nsu.fit.model.Game;
import ru.nsu.fit.model.Snake;
import ru.nsu.fit.model.utils.GameStatus;
import ru.nsu.fit.model.utils.SnakeDirection;
import ru.nsu.fit.view.GameFieldView;

public class JavafxPresenter implements Presenter {

    private final GameFieldView gameFieldView;
    private final Game game;
    private AnimationTimer timer;
    private final Snake snake;

    public JavafxPresenter(GameFieldView gameFieldView, Game game) {
        this.gameFieldView = gameFieldView;
        this.game = game;

        snake = game.getSnake();
    }

    public void startGameLoop() {
        gameFieldView.getScene()
                     .setOnKeyPressed(event -> handleKeyPressed(event.getCode()));

        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 200_000_000) {
                    lastUpdate = now;

                    updateGame();
                }
            }
        };
        timer.start();
    }

    public void updateGame() {
        if (game.getGameStatus() == GameStatus.GAME_OVER) {
            gameFieldView.showGameOverScreen(game.getScore());
            timer.stop();
            return;
        }

        gameFieldView.showScore(game.getScore());
        game.moveSnake();

        gameFieldView.renderGameField(snake.getBody(), game.getFood().getPoint());
    }

    public void handleKeyPressed(KeyCode keyCode) {
        switch (keyCode) {
            case UP -> snake.setCurrentDirection(SnakeDirection.UP);
            case DOWN -> snake.setCurrentDirection(SnakeDirection.DOWN);
            case LEFT -> snake.setCurrentDirection(SnakeDirection.LEFT);
            case RIGHT -> snake.setCurrentDirection(SnakeDirection.RIGHT);
        }
    }
}
