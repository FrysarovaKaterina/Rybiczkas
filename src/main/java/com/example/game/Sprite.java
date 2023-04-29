package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sprite {
    List<Image> textures = new ArrayList<Image>();

    int animationFPS;
    boolean flipped;
    int positionX;
    int positionY;
    public void render(GraphicsContext gc, int renderingCounter) {
        gc.drawImage(textures.get(renderingCounter%textures.size()), positionX, positionY);
    }
}
