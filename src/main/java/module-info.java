module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.example.game to javafx.fxml;
    exports com.example.game;
}