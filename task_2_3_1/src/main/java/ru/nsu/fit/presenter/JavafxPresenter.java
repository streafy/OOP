package ru.nsu.fit.presenter;

import javafx.animation.AnimationTimer;
import ru.nsu.fit.model.Snake;
import ru.nsu.fit.view.GameFieldView;

public class JavafxPresenter implements Presenter {

    private GameFieldView gameFieldView;
    private Snake snake;

    public JavafxPresenter(GameFieldView gameFieldView, Snake snake) {
        this.gameFieldView = gameFieldView;
        this.snake = snake;
    }

    public void onKeyboardButtonPressed() {

    }

    public void onFoodEaten() {

    }

    public void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                snake.moveUp();
                gameFieldView.renderGameField();
                gameFieldView.renderSnake(snake.getSnakeLocation());
            }
        }.start();
    }
}
