package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Background extends Sprite{
    Image background1 = new Image("underwater1.png");
    public Background(String texture, EngineConfig engineConfig) {
        super(new ArrayList<Image>(), 1, 0, 0, engineConfig);
        background1 = new Image(texture);
        textures.add(background1);
    }
}
