package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

//import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Sprite {
    public List<Image> textures = new ArrayList<Image>();
    public boolean animationDisabled = false;
    public int animationFPS;
    public boolean flipped = false;
    public int positionX;
    public int positionY;

    protected EngineConfig engineConfig;
    private int frames_counter = 0;
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    public Sprite(int animationFPS, int positionX, int positionY, EngineConfig engineConfig) {
        this.animationFPS = animationFPS;
        this.positionX = positionX;
        if (this.positionX > engineConfig.Width) {
            this.positionX = engineConfig.Width - 500;
        }
        if (this.positionX < 0) {
            this.positionX = 500;
        }
        this.positionY = positionY;
        if (this.positionY > engineConfig.Height) {
            this.positionY = engineConfig.Height - 500;
        }
        if (this.positionY < 0) {
            this.positionY = 500;
        }
        this.engineConfig = engineConfig;
    }

    public void render(GraphicsContext gc) {
        if (positionX < -500 || positionX > engineConfig.Width + 500 || positionY < -500 || positionY > engineConfig.Height + 500)
            return;

        Image texture;
        if (animationDisabled)
            texture = textures.get(0);
        else
            texture = textures.get((frames_counter / (engineConfig.FPS / animationFPS)) % textures.size());
        if (flipped) {
            gc.drawImage(texture, positionX + texture.getWidth() / 2, positionY - texture.getHeight() / 2, -texture.getWidth(), texture.getHeight());
        } else
            gc.drawImage(texture, positionX - texture.getWidth() / 2, positionY - texture.getHeight() / 2);

        frames_counter = frames_counter + 1 % (textures.size() * (engineConfig.FPS / animationFPS)) + 1;
    }

    public void loadTextures() {
    }

    ;
}

