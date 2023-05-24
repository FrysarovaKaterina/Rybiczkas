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

import java.awt.*;
import java.awt.event.KeyAdapter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.LogManager;

/**
 * The main class of the whole game.
 * Initializes the game canvas (fullscreen only) and starts the thread with game loop.
 * Sets key event listener to provide user interaction via keyboard.
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var params = getParameters();
        if (params.getNamed().containsKey("log") && params.getNamed().get("log").equals("disable"))
            LogManager.getLogManager().reset();
        Canvas canvas = new Canvas(1920, 1080);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        String[] levels = new File("levels").list();
        Arrays.sort(levels);
        GameEngine engine = new GameEngine(gc, new EngineConfig(1920, 1080, 60), levels);
        new Thread(engine).start();

        Scene scene = new Scene(new Group(canvas), 1920, 1080);
        scene.setOnKeyPressed(keyEvent -> {
            KeyCode code = keyEvent.getCode();
            engine.addKeyToSet(code);
        });
        scene.setOnKeyReleased(keyEvent -> {
            KeyCode code = keyEvent.getCode();
            engine.removeKeyFromSet(code);
        });
        stage.setTitle("gaming");
        stage.setScene(scene);
        stage.show();
    }
}