module ru.nsu.fit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ru.nsu.fit to javafx.fxml;
    opens ru.nsu.fit.presenter to javafx.fxml;
    exports ru.nsu.fit;
}