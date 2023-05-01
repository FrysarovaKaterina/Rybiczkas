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
        Image texture = textures.get((frames_counter / (60/animationFPS)) % textures.size());
        gc.drawImage(texture, positionX-texture.getWidth()/2, positionY-texture.getHeight()/2);
        frames_counter = frames_counter+1 % (textures.size() * (60/animationFPS)) + 1; //todo check fix
    }





}

