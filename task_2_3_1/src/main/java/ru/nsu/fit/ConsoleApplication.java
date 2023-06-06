package ru.nsu.fit;

import ru.nsu.fit.model.Food;
import ru.nsu.fit.model.Game;
import ru.nsu.fit.model.Snake;
import ru.nsu.fit.presenter.ConsolePresenter;
import ru.nsu.fit.view.console.ConsoleGameFiledView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ConsoleGameFiledView gameFiledView = new ConsoleGameFiledView();

        Food food = new Food();
        Snake snake = new Snake();
        Game game = new Game(20, 20, snake, food);

        ConsolePresenter consolePresenter = new ConsolePresenter(gameFiledView, game);

        consolePresenter.startGameLoop();
    }
}
