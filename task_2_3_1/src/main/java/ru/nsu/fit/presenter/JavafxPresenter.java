package ru.nsu.fit.presenter;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.nsu.fit.model.Game;
import ru.nsu.fit.model.Snake;
import ru.nsu.fit.model.utils.GameStatus;
import ru.nsu.fit.model.utils.SnakeDirection;
import ru.nsu.fit.view.javafx.JavafxGameFieldView;

import java.net.URL;
import java.util.ResourceBundle;

public class JavafxPresenter implements Presenter, Initializable {

    private JavafxGameFieldView gameFieldView;
    private Game game;

    @FXML
    private Pane rootContainer;
    @FXML
    private HBox gameFieldContainer;
    @FXML
    private VBox gameInfoContainer;
    @FXML
    private Label gameInfoLabel;

    private AnimationTimer timer;
    private Snake snake;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gameFieldView = new JavafxGameFieldView(gameFieldContainer, gameInfoLabel);
    }

    public void initGame(Game game) {
        this.game = game;
        this.snake = game.getSnake();
    }

    public void startGameLoop(Scene scene) {
        scene.setOnKeyPressed(event -> handleKeyPressed(event.getCode()));

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
