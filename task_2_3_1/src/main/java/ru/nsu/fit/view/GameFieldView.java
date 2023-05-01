package ru.nsu.fit.view;

import java.awt.*;
import java.util.List;

public interface GameFieldView {

    void renderGameField(List<Point> snakeBody, Point food);
}
