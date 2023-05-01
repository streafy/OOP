package ru.nsu.fit.presenter;

import ru.nsu.fit.model.Game;
import ru.nsu.fit.model.utils.GameStatus;
import ru.nsu.fit.view.console.ConsoleGameFiledView;

public class ConsolePresenter implements Presenter {

    private final ConsoleGameFiledView gameFiledView;
    private final Game game;

    public ConsolePresenter(ConsoleGameFiledView gameFiledView, Game game) {
        this.gameFiledView = gameFiledView;
        this.game = game;
    }

    public void startGameLoop() {
        while (game.getGameStatus() != GameStatus.GAME_OVER) {

        }
    }
}
