package com.example.game;

import javafx.scene.image.Image;

import java.util.List;

public class Item {
    String name;
    boolean used = false;
    List<Image> textures;

    public Item(String name, List<Image> textures) {
        this.name = name;
        this.textures = textures;
    }

    /**
     * Adding item to player's inventory.
     */
    public void addToInventory(Player plr) {
        plr.inventory.add(this);
    }

    public void use(Alive target) {
        /*do nothing by default*/
    }
}
