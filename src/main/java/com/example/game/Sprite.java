package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

//import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sprite {
    public List<Image> textures = new ArrayList<Image>();
    public boolean animationDisabled = false;
    public int animationFPS;
    public boolean flipped = false;
    public int positionX;
    public int positionY;

    private int frames_counter = 0;

    public Sprite(List<Image> textures, int animationFPS, int positionX, int positionY) {
        this.textures = textures;
        this.animationFPS = animationFPS;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void render(GraphicsContext gc) {
        if (positionX < -100 || positionX > 2300 || positionY < -100 || positionY > 1500)
            return;

        Image texture;
        if (animationDisabled)
            texture = textures.get(0);
        else
            texture = textures.get((frames_counter / (60/animationFPS)) % textures.size());
        if (flipped) {
            gc.drawImage(texture, positionX+texture.getWidth()/2, positionY-texture.getHeight()/2, -texture.getWidth(), texture.getHeight());
        } else
            gc.drawImage(texture, positionX-texture.getWidth()/2, positionY-texture.getHeight()/2);

        frames_counter = frames_counter+1 % (textures.size() * (60/animationFPS)) + 1;
    }





}

