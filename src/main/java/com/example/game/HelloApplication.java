package com.example.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(1920,1080);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        GameEngine engine = new GameEngine(gc);
        // todo hook up keylistener to gameengine
        new Thread(engine).start();

        Scene scene = new Scene(new Group(canvas),1920, 1080);
        stage.setTitle("gaming");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}