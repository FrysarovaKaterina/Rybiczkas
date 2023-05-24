package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Collectable extends ColliderObject {
    Item item;
    GameEngine engine;

    public Collectable(Item item, int positionX, int positionY, GameEngine engine) {
        super(1, positionX, positionY, 25, engine.getEngineConfig());
        this.engine = engine;
        this.item = item;
    }

    @Override
    public void loadTextures() {
        textures = item.textures;
    }

    /**
     * If player collides a collectable object, an item representing the collectable will be added to the player's inventory.
     * The visible representation of Collectable object disappears from the screen.
     */
    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player) {
            ((Player) other).inventory.add(item);
            engine.deleteSprite(this);
        }
    }
}
