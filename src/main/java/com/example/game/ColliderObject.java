package com.example.game;


import javafx.scene.image.Image;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ColliderObject extends Sprite {
    public int radius;
    public Set<ColliderObject> collisions = new HashSet<>();

    public ColliderObject(List<Image> textures, int animationFPS, int positionX, int positionY, int radius) {
        super(textures, animationFPS, positionX, positionY);
        this.radius = radius;
    }
    public void collisionEnter(ColliderObject other) { /* do nothing by default */ }
    public void collisionLeave(ColliderObject other) {  /* do nothing by default */ }
}
