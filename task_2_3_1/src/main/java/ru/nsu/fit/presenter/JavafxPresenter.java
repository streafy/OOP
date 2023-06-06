package ru.nsu.fit.presenter;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.nsu.fit.model.Food;
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

    private int gameFieldWidth = 20;
    private int gameFieldHeight = 20;

    int cellSize = 25;

    @FXML
    private Button restartButton;

    private Scene scene;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gameFieldView = new JavafxGameFieldView(gameFieldContainer, gameInfoLabel);

        Food food = new Food();
        this.snake = new Snake();
        this.game = new Game(20, 20, snake, food);
    }

    public void initGame(Game game) {
        this.game = game;
        this.snake = game.getSnake();
    }

    @FXML
    private void restartGame() {
        timer.stop();
        gameFieldView.resize(gameFieldWidth, gameFieldHeight, cellSize);

        Food food = new Food();
        this.snake = new Snake();
        this.game = new Game(gameFieldWidth, gameFieldHeight, snake, food);

        startGameLoop();
    }

    @FXML
    private void onSetSmallGameFieldPressed() {
        gameFieldWidth = 10;
        gameFieldHeight = 10;
        cellSize = 50;
    }

    @FXML
    private void onSetMediumGameFieldPressed() {
        gameFieldWidth = 20;
        gameFieldHeight = 20;
        cellSize = 25;
    }

    @FXML
    private void onSetLargeGameFieldPressed() {
        gameFieldWidth = 50;
        gameFieldHeight = 50;
        cellSize = 10;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void startGameLoop() {
        scene.setOnKeyPressed(event -> handleKeyPressed(event.getCode()));

        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= (200_000_000 - (game.getScore() * 20_000_000L))) {
                    lastUpdate = now;

                    updateGame();
                }
            }
        };
        timer.start();
    }

    @Override
    public void updateGame() {
        rootContainer.requestFocus();

        if (game.getGameStatus() == GameStatus.GAME_OVER) {
            gameFieldView.showGameOverScreen(game.getScore());
            timer.stop();
            return;
        }

        gameFieldView.showScore(game.getScore());
        game.moveSnake();
        gameFieldView.renderGameField(snake.getBody(), game.getFood().getPoint());
    }

    private void handleKeyPressed(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                snake.setCurrentDirection(SnakeDirection.UP);
                break;
            case DOWN:
                snake.setCurrentDirection(SnakeDirection.DOWN);
                break;
            case LEFT:
                snake.setCurrentDirection(SnakeDirection.LEFT);
                break;
            case RIGHT:
                snake.setCurrentDirection(SnakeDirection.RIGHT);
                break;
        }
    }
}
