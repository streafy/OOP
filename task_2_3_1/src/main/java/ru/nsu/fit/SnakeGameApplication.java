package ru.nsu.fit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.nsu.fit.presenter.JavafxPresenter;

import java.io.IOException;

public class SnakeGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeGameApplication.class.getResource("/ru/nsu/fit/game-field-view.fxml"));
        Pane rootContainer = fxmlLoader.load();

        JavafxPresenter javafxPresenter = fxmlLoader.getController();

        Scene scene = new Scene(rootContainer, 800, 500);
        stage.setTitle("SnakeGame");
        stage.setScene(scene);
        stage.show();

        javafxPresenter.setScene(scene);
        javafxPresenter.startGameLoop();
    }

    public static void main(String[] args) {
        launch();
    }
}