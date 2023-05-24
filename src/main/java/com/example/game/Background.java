package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Background extends Sprite {
    private String texture;

    /**
     * Simple method for setting the background texture/picture as a Sprite,  setting default parametrs such as position or FPS
     */
    public Background(String texture, EngineConfig engineConfig) {
        super(1, engineConfig.Width / 2, engineConfig.Height / 2, engineConfig);
        this.texture = texture;
    }

    @Override
    public void loadTextures() {
        Image background1 = new Image(texture);
        textures.add(background1);
    }

}
