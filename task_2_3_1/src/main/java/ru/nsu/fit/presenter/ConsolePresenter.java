package ru.nsu.fit.presenter;

import ru.nsu.fit.model.Game;
import ru.nsu.fit.model.Snake;
import ru.nsu.fit.model.utils.GameStatus;
import ru.nsu.fit.model.utils.SnakeDirection;
import ru.nsu.fit.view.console.ConsoleGameFiledView;

import java.util.Scanner;

public class ConsolePresenter implements Presenter {

    private final ConsoleGameFiledView gameFieldView;
    private final Game game;
    private final Snake snake;

    private final Scanner sc = new Scanner(System.in);

    public ConsolePresenter(ConsoleGameFiledView gameFiledView, Game game) {
        this.gameFieldView = gameFiledView;
        this.game = game;
        this.snake = game.getSnake();
    }

    public void startGameLoop() {
        while (game.getGameStatus() != GameStatus.GAME_OVER) {
            String turn = sc.next();

            handleTurnInput(turn);
            updateGame();
        }
    }

    @Override
    public void updateGame() {
        if (game.getGameStatus() == GameStatus.GAME_OVER) {
            gameFieldView.showGameOverScreen(game.getScore());
            return;
        }

        gameFieldView.showScore(game.getScore());
        game.moveSnake();
        gameFieldView.renderGameField(snake.getBody(), game.getFood().getPoint());
    }

    private void handleTurnInput(String turn) {
        switch (turn.toUpperCase()) {
            case "UP":
                snake.setCurrentDirection(SnakeDirection.UP);
                break;
            case "DOWN":
                snake.setCurrentDirection(SnakeDirection.DOWN);
                break;
            case "LEFT":
                snake.setCurrentDirection(SnakeDirection.LEFT);
                break;
            case "RIGHT":
                snake.setCurrentDirection(SnakeDirection.RIGHT);
                break;
        }
    }
}
