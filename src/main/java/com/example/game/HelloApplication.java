package com.example.game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.Component;
import java.awt.event.KeyAdapter;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(1920,1080);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        GameEngine engine = new GameEngine(gc);

        engine.tryLoadLevel("todo"); //todo level selection menu

        new Thread(engine).start();

        Scene scene = new Scene(new Group(canvas),1920, 1080);
        scene.setOnKeyPressed(keyEvent -> {
            KeyCode code = keyEvent.getCode();
            engine.addKeyToSet(code);
        });
        scene.setOnKeyReleased(keyEvent ->{
            KeyCode coderem = keyEvent.getCode();
            engine.removeKeyFromSet(coderem);
        });
        stage.setTitle("gaming");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}