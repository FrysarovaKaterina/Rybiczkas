package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Collectable extends ColliderObject {
    Item item;
    GameEngine engine;
    public Collectable(Item item, int positionX, int positionY, GameEngine engine) {
        super(item.textures, 1, positionX, positionY, 25, engine.getEngineConfig());
        this.engine = engine;
        this.item = item;
    }

    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player) {
            ((Player) other).inventory.add(item);
            engine.deleteSprite(this);
        }
    }
}
