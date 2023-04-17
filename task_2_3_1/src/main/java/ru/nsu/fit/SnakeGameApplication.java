package ru.nsu.fit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.nsu.fit.model.Food;
import ru.nsu.fit.model.Game;
import ru.nsu.fit.model.Snake;
import ru.nsu.fit.presenter.JavafxPresenter;
import ru.nsu.fit.view.GameFieldView;

import java.io.IOException;

public class SnakeGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeGameApplication.class.getResource("/ru/nsu/fit/game-field-view.fxml"));
        Pane rootContainer = fxmlLoader.load();
        Scene scene = new Scene(rootContainer, 800, 500);
        stage.setTitle("SnakeGame");
        stage.setScene(scene);
        stage.show();

        Snake snake = new Snake();
        Food food = new Food();
        Game game = new Game(10, 10, snake, food);

        GameFieldView gameFieldView = new GameFieldView(scene);

        JavafxPresenter javafxPresenter = new JavafxPresenter(gameFieldView, game);

        javafxPresenter.startGameLoop();
    }

    public static void main(String[] args) {
        launch();
    }
}