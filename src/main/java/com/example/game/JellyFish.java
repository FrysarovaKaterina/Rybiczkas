package com.example.game;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class JellyFish extends Alive {
    int counter =0;
    boolean electricShock = true;
    private Sprite target;
    Image jf1 = new Image("jellyfish1.png");
    Image jf2 = new Image("jellyfish2.png");
    Image jf3 = new Image("jellyfish3.png");
    Image jf4 = new Image("jellyfish4.png");

    //private PlayerDamager damager = new PlayerDamager(60);
    public JellyFish(int positionX, int positionY, Sprite trg, EngineConfig engineConfig) {
        super(new ArrayList<Image>(), 2, positionX, positionY, 60, 0, 4,engineConfig);
        textures.add(jf1);
        textures.add(jf2);
        this.target=trg;
    }

    private int damage = 4;
    private int speed = 1;
//    @Override
//    public void collisionEnter(ColliderObject other) {
//        if (other instanceof Player)
//            damager.max();
//    }

    @Override
    public void update() {
        counter++;
        if (counter==170){
            electricShock =true;}
        else if(counter==800){
            electricShock=false;
            counter=0;
            textures.remove(jf3);
            textures.remove(jf4);
            textures.add(jf1);
            textures.add(jf2);}

        if (electricShock && !(textures.contains(jf3))) {
            textures.add(jf3);
            textures.add(jf4);
            textures.remove(jf1);
            textures.remove(jf2);
        }





        //damager.update(collisions, 3);

        //damage
        if ((target instanceof Player && ((Player) target).shieldActive !=true && target.positionX<= positionX+radius && target.positionX >= positionX-radius) && electricShock){
            ((Player) target).energy-=damage;
            electricShock =false;
            textures.remove(jf3);
            textures.remove(jf4);
            textures.add(jf1);
            textures.add(jf2);


        }else if(target instanceof Player && ((Player) target).shieldActive !=true && target.positionY<= positionY+radius && target.positionY >= positionY-radius&& electricShock) {
            ((Player) target).energy -= damage;
            electricShock = false;
            textures.remove(jf3);
            textures.remove(jf4);
            textures.add(jf1);
            textures.add(jf2);


        }

        //movement
        if (positionY >= 1080)
            speed = -speed;
        else if (positionY <= 0)
            speed = -speed;

        positionY +=speed;

    }

}