package com.example.game;

import javafx.scene.image.Image;

import java.util.List;

public class Key extends Item {
    public Key() {
        super("key", List.of(new Image[]{new Image("key.png")}));
    }

    /**
     * there is no scpeial "use" of key, the player just has to have it in the inventory to use it.
     */
    @Override
    public void use(Alive target) {
    }
}
