package com.example.game;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ColliderObject extends Sprite {
    public int radius;
    public Set<ColliderObject> collisions = new HashSet<>();

    public ColliderObject(int animationFPS, int positionX, int positionY, int radius, EngineConfig engineConfig) {
        super(animationFPS, positionX, positionY, engineConfig);
        this.radius = radius;
    }

    public void collisionEnter(ColliderObject other) { /* do nothing by default */ }

    public void collisionLeave(ColliderObject other) {  /* do nothing by default */ }

    /**
     * Rendering the images on the canvas.
     * (The commented code is very useful for debugging of collisions)
     */
    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        //gc.strokeArc(positionX-radius/2, positionY-radius/2, radius, radius, 0, 360, ArcType.ROUND);
    }
}
