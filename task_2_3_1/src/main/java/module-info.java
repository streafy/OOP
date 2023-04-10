module ru.nsu.fit {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.fit to javafx.fxml;
    exports ru.nsu.fit;
}