package com.example.game;

import javafx.scene.image.Image;

import java.util.List;

public class Item {
    String name;
    boolean used;
    List<Image> textures;

    public void addToInventory ( Player plr){
//        Item item = new Item();
//        item.name = colectbl.name;
//        item.textures=colectbl.textures;
        plr.inventory.add(this);
    }
    public void use(){
        /*do nothing by default*/
    }
}
