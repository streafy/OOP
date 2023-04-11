package ru.nsu.fit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ru.nsu.fit.model.Snake;
import ru.nsu.fit.presenter.JavafxPresenter;
import ru.nsu.fit.view.GameFieldView;

import java.io.IOException;

public class SnakeGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeGameApplication.class.getResource("/ru/nsu/fit/game-field-view.fxml"));
        HBox rootContainer = fxmlLoader.load();
        Scene scene = new Scene(rootContainer, 800, 500);
        stage.setTitle("SnakeGame");
        stage.setScene(scene);
        stage.show();

        GameFieldView gameFieldView = new GameFieldView(scene);
        Snake snake = new Snake();
        JavafxPresenter javafxPresenter = new JavafxPresenter(gameFieldView, snake);

        gameFieldView.renderSnake(snake.getSnakeLocation());
        javafxPresenter.startGameLoop();
    }

    public static void main(String[] args) {
        launch();
    }
}