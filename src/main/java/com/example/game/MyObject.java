package com.example.game;


import static java.lang.Math.sqrt;

public class MyObject extends Sprite{
    int radius;

    public boolean Collide(MyObject obj1, MyObject obj2){
        boolean collision = false;
        double distance = sqrt((obj2.positionX-obj1.positionX)^2+(obj2.positionY-obj1.positionY)^2);
        if (distance<= obj1.radius+obj2.radius){
            collision=true;
        }
        return collision;
    }
}
