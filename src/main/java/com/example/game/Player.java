package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.*;

public class Player extends Alive {
    private int bubbleCounter =5;
    private int shieldCounter = 1000;
    boolean shieldActive = false;
    ArrayList<Item> inventory = new ArrayList<Item>();
    Set<Sprite> enemiz = new Set<Sprite>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Sprite> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] ts) {
            return null;
        }

        @Override
        public boolean add(Sprite sprite) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Sprite> collection) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }
    };
    ArrayList<String> provisoryInventory = new ArrayList<String>();
    private GameEngine engine;
    boolean bubbleAvailable = true;
    private int speed = 7;
    private int counter =0;
    Image pl1 = new Image("playa1.png");
    Image pl2 = new Image("playa2.png");
    Image pl3 = new Image("playa3.png");
    Image pl4 = new Image("playa4.png");

    Image shield = new Image("shield.png");
    public Player(int positionX, int positionY, GameEngine engine) {
        super(new ArrayList<Image>(), 2, positionX, positionY, 100, 1, 5, engine.getEngineConfig());
        textures.add(pl1);
        textures.add(pl2);
        textures.add(pl3);
        textures.add(pl4);
        this.engine = engine;
    }

    @Override
    public void update() {
        if (shieldActive){
            shieldCounter--;
        }
        if (shieldCounter<=0){
            shieldActive=false;
            textures.remove(shield);
            textures.add(pl1);
            textures.add(pl2);
            textures.add(pl3);
            textures.add(pl4);
            shieldCounter=1000;
        }
        counter++;
        if (counter==20){
            bubbleAvailable=true;
            counter=0;
        }
        if (energy <= 0) {
            lives--;
            energy = 5;
            if (lives <= 0)
                engine.endGame(true);
        }
        animationDisabled = true;
        if (engine.isKeyPressed(KeyCode.RIGHT)){
            positionX +=speed;
            facing = Side.RIGHT;
            animationDisabled = false;
        }
        if (engine.isKeyPressed(KeyCode.E)&& this.provisoryInventory.contains("S")){
            provisoryInventory.remove("S");
            textures.add(shield);
            textures.remove(pl1);
            textures.remove(pl2);
            textures.remove(pl3);
            textures.remove(pl4);
            shieldActive=true;
        }
        if (engine.isKeyPressed(KeyCode.LEFT)){
            positionX -=speed;
            facing = Side.LEFT;
            animationDisabled = false;
        }
        if (engine.isKeyPressed(KeyCode.UP)){
            positionY -=speed;
            animationDisabled = false;
        }
        if (engine.isKeyPressed(KeyCode.DOWN)){
            positionY +=speed;
        }
        if (engine.isKeyPressed(KeyCode.SPACE) && bubbleCounter!=0 && bubbleAvailable){
            engine.spawnSprite(new Bubble(positionX, positionY, facing, engineConfig));
            bubbleAvailable=false;
            bubbleCounter--;
        }
        if(positionX < 0)
            positionX = 0;
        else if(positionX > engineConfig.Width)
            positionX = engineConfig.Width;
        if(positionY > engineConfig.Height)
            positionY = engineConfig.Height;
        else if (positionY<0)
            positionY = 0;
        if(positionY<=30 && engine.isKeyPressed(KeyCode.Q)){
            bubbleCounter=5;
        }
    }
}
