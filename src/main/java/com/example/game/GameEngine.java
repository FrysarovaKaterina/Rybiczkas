package com.example.game;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.io.*;
import java.util.*;

public class GameEngine implements Runnable {
    private String[] levels;
    private int currentLevel = 0;
    private EngineConfig config;
    private boolean loadNextLevel = false;
    private boolean stopGame = false;

    public void stopMainloop() {
        stopGame = true;
    }

    private GraphicsContext gc;
    private Set<KeyCode> keysPressed = new HashSet<>();
    private ArrayList<Sprite> activeSprites = new ArrayList<>();
    private ArrayList<Sprite> toSpawn = new ArrayList<>();
    private Set<Sprite> toDelete = new HashSet<Sprite>();

    public GameEngine(GraphicsContext gc, EngineConfig config, String[] levels) {
        for (String l : levels) {
            System.out.println(l);
        }
        this.gc = gc;
        this.config = config;
        this.levels = levels;
    }

    void spawnSprite(Sprite s) {
        toSpawn.add(s);
    }

    void deleteSprite(Sprite s) {
        toDelete.add(s);
    }

    @Override
    public void run() {

        if (!tryLoadLevel(0))
            throw new RuntimeException("failed to load level");
       // loadMenu();

        while (!stopGame) {
            if (loadNextLevel) {
                if (currentLevel + 1 >= levels.length)
                    endGame(false);
                tryLoadLevel(currentLevel + 1);
            }

            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

            // energy, lives .setValue todo

            handleCollisions();

            // call move todo
            // call render
            for (Sprite s : activeSprites) {
                if (s instanceof Alive && ((Alive) s).energy <= 0 && ((Alive) s).lives <= 0) {
                    deleteSprite(s);
                    continue;
                }

                s.render(gc);
            }
            for (Sprite s : toSpawn) {
                activeSprites.add(s);
            }
            toSpawn.clear();
            for (Sprite s : toDelete) {
                activeSprites.remove(s);
            }
            toDelete.clear();

            //**************************
            //System.out.println(keysPressed);
            //***************************

            try {
                Thread.sleep(1000 / config.FPS);
                //System.out.println(keysPressed);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void endGame(boolean failed) {
        //todo
        System.out.println(failed ? "You hilariously failed you dumbo" : "huh u win ok");

        stopMainloop();
        Platform.exit();
    }

    public boolean tryLoadLevel(int index) {
        currentLevel = index;
        activeSprites.clear();
        toDelete.clear();
        toSpawn.clear();
        stopGame = false;
        loadNextLevel = false;

        Player plr = new Player (500, 500,this);
        FileReader fr = null;
        try {
            fr = new FileReader("levels/" + levels[index]);
        } catch (FileNotFoundException e) {
            return false;
        }
        try {
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();


            while (line != null) {
                System.out.println(line);
                var split = line.split(" ");
                switch (split[0]) {
                    case "background":
                        spawnSprite(new Background(split [1],config));
                        break;
                    case "piranha":
                        spawnSprite(new Piranha(Integer.parseInt(split[1]), Integer.parseInt(split[2]), config));
                        break;
                    case "squid":
                        spawnSprite(new Squid(Integer.parseInt(split[1]), Integer.parseInt(split[2]), config));
                        break;
                    case "chest":
                        spawnSprite(new Chest(Integer.parseInt(split[1]), Integer.parseInt(split[2]), config));
                        break;
                    case "key":
                        spawnSprite(new Collectable(new Key(), Integer.parseInt(split[1]), Integer.parseInt(split[2]), this));
                        break;
                    case "door":
                        spawnSprite(new Door(Integer.parseInt(split[1]), Integer.parseInt(split[2]), this));
                        break;
                    case "player":
                        plr = new Player(Integer.parseInt(split[1]), Integer.parseInt(split[2]), this);
                        break;
                    case "jellyfish":
                        spawnSprite(new JellyFish(Integer.parseInt(split[1]), Integer.parseInt(split[2]),plr, config));
                        break;
                    case "shark":
                        spawnSprite(new Shark(Integer.parseInt(split[1]), Integer.parseInt(split[2]),plr, config));
                        break;
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            return false;
        }
        spawnSprite(plr);
        spawnSprite(new Lives(plr, 1750, 10, config));
        spawnSprite(new Energy(plr, 1750, 75, config));
        return true;
    }

    //KeyListener methods
    public void addKeyToSet(KeyCode key) {
        keysPressed.add(key);
    }

    public void removeKeyFromSet(KeyCode keyrem) {
        keysPressed.remove(keyrem);
    }

    public boolean isKeyPressed(KeyCode kc) {
        return keysPressed.contains(kc);
    }

    private double square(double x) {
        return x * x;
    }

    private void handleCollisions() {
        //HashMap<Sprite, java.lang.Boolean> collisionsMap = new HashMap<Sprite, Boolean>();
        // todo optimize
        for (Sprite s : activeSprites) {

            if (s instanceof ColliderObject) {
                for (Sprite s2 : activeSprites) {
                    if (s2 instanceof Chest && ((Chest) s2).opened == false) {
                        double distanceSquared2 = square(s2.positionX - s.positionX) + square(s2.positionY - s.positionY);
                        if (distanceSquared2 <= square(((ColliderObject) s).radius + ((ColliderObject) s2).radius)) {
                            ((ColliderObject) s).collisionEnter((ColliderObject) s2);
                        }
                    }
                    if (s == s2 || (s instanceof Chest && ((Chest) s).opened) || (s2 instanceof Player && ((Player) s2).shieldActive)) {
                        continue;
                    }
                    if (s instanceof Door && ((Door) s).opened) {
                        deleteSprite(s2);
                    }
                    if (s2 instanceof ColliderObject) {
                        double distanceSquared = square(s2.positionX - s.positionX) + square(s2.positionY - s.positionY);
                        if (distanceSquared <= square(((ColliderObject) s).radius + ((ColliderObject) s2).radius)) {
                            if (!((ColliderObject) s).collisions.contains(s2)) {
                                ((ColliderObject) s).collisions.add((ColliderObject) s2);
                                ((ColliderObject) s).collisionEnter((ColliderObject) s2);
                            }
                        } else if (((ColliderObject) s).collisions.contains(s2)) {
                            ((ColliderObject) s).collisions.remove((ColliderObject) s2);
                            ((ColliderObject) s).collisionLeave((ColliderObject) s2);
                        }
                    }
                }
            }

        }

    }

    public EngineConfig getEngineConfig() {
        return config;
    }

    public ArrayList<Sprite> getActiveSprites() {
        return activeSprites;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void nextLevel() {
        loadNextLevel = true;
    }
    public void loadMenu (){

    }

    public int countEnemies() {
        int count = 0;
        for (Sprite s:activeSprites) {
            if (s instanceof Alive && !(s instanceof Player) && !(s instanceof Bubble) && !(s instanceof Background))
                count++;
        }
        return count;
    }
}