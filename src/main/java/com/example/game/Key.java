package com.example.game;

import javafx.scene.image.Image;

import java.util.List;

public class Key extends Item{
    public Key() {
        super("key", List.of(new Image[]{new Image("key.png")}));
    }

    @Override
    public void use(Alive target) {
    }
}
