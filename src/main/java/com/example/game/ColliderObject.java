package com.example.game;


import javafx.scene.image.Image;

import java.util.List;

public abstract class ColliderObject extends Sprite {
    public int radius;

    public ColliderObject(List<Image> textures, int animationFPS, int positionX, int positionY, int radius) {
        super(textures, animationFPS, positionX, positionY);
        this.radius = radius;
    }

    public void collide(ColliderObject other) { /* do nothing by default */ }
}
