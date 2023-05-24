package com.example.game;

import java.util.logging.Logger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;

import java.io.*;
import java.util.*;

/**
 * Basically the game loop running all the important stuff making the game work...
 * Loading levels, checking collisions, rendering sprites, working with keylistener, and eventually finishing the game loop.
 */
public class GameEngine implements Runnable {
    private String[] levels;
    private int currentLevel = 0;
    private EngineConfig config;
    private boolean loadNextLevel = false;
    private boolean stopGame = false;

    private GraphicsContext gc;
    private Set<KeyCode> keysPressed = new HashSet<>();
    protected ArrayList<Sprite> activeSprites = new ArrayList<>();
    private ArrayList<Sprite> toSpawn = new ArrayList<>();
    private Set<Sprite> toDelete = new HashSet<Sprite>();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public GameEngine(GraphicsContext gc, EngineConfig config, String[] levels) {
        this.gc = gc;
        this.config = config;
        this.levels = levels;
        logger.info("Init.");
        for (String l : levels) {
            logger.info("Level name in level dir: " + l);
        }
    }

    // adds Sprite to the list of sprites that should be rendered in the next iteration of the game loop
    public void spawnSprite(Sprite s) {
        toSpawn.add(s);
        logger.info("Sprite to spawn: " + s);
    }

    //sprite will not be rendered anymore
    public void deleteSprite(Sprite s) {
        toDelete.add(s);
        logger.info("Sprite to delete: " + s);
    }

    public void removeAllSprites() {
        activeSprites.forEach((Sprite s) -> {
            deleteSprite(s);
        });
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Loading levels, rendering the screen
     */
    @Override
    public void run() {
        if (!tryLoadLevel(0))
            throw new RuntimeException("failed to load level");

        while (!stopGame) {
            //if there is no higher level that could be loaded, ends game with parameter false, meaning player won
            if (loadNextLevel) {
                if (currentLevel + 1 >= levels.length)
                    endGame(false);
                else
                    tryLoadLevel(currentLevel + 1);
            }
            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

            handleCollisions();

            // call render
            for (Sprite s : activeSprites) {
                //if sprite died (is out of energy and lives) deletes the sprite
                if (s instanceof Alive && ((Alive) s).energy <= 0 && ((Alive) s).lives <= 0) {
                    deleteSprite(s);
                    continue;
                }
                s.render(gc);
            }
            for (Sprite s : toSpawn) {
                s.loadTextures();
                activeSprites.add(s);
            }
            toSpawn.clear();
            for (Sprite s : toDelete) {
                activeSprites.remove(s);
            }
            toDelete.clear();
            //manages the FPS of the game's rendering
            try {
                Thread.sleep(1000 / config.FPS);
            } catch (Exception e) {
                logger.severe("exception on game loop thrown: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Checking if the game should be ended, eventually displaying win/lose screen.
     */
    public boolean endGame(boolean failed) {
        logger.info("endGame, game ends now failed=" + failed);
        activeSprites.forEach((Sprite s) -> {
            deleteSprite(s);
        });

        Image bck = failed ? new Image("loser.png") : new Image("winnin.png");
        // setting current level value according to the game state on
        // either =currentLevel-1 if the player died, so the current level (in which the player died) can be played again after calling nextLevel
        // or =-1 meaning the player won and after calling "nextLevel", level0 (welcome screen) will be displayed
        currentLevel = failed ? currentLevel - 1 : -1;
        Sprite background = new Sprite(1, config.Width / 2, config.Height / 2, config);
        background.textures.add(bck);
        spawnSprite(background);
        spawnSprite(new Door(1600, 850, this));
        spawnSprite(new Player(config.Width / 2, config.Height / 2, this));
        loadNextLevel = false;
        return failed;
    }

    /**
     * Loads level from levels folder based on the currentLevel index
     * Reads the level text file and spawns sprites accordingly
     * Always spawns player(with lives and energy) even if not specified in the text file (but the position of PLayer may be specified in the text file)
     */
    public boolean tryLoadLevel(int index) {
        logger.info("Loading new level now, index = " + index + ", name = " + levels[index]);
        currentLevel = index;
        activeSprites.clear();
        toDelete.clear();
        toSpawn.clear();
        stopGame = false;
        loadNextLevel = false;

        Player plr = new Player(500, 500, this);
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
                logger.finest("level data: " + line);
                var split = line.split(" ");
                switch (split[0]) {
                    case "background":
                        spawnSprite(new Background(split[1], config));
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
                        spawnSprite(new JellyFish(Integer.parseInt(split[1]), Integer.parseInt(split[2]), plr, config));
                        break;
                    case "shark":
                        spawnSprite(new Shark(Integer.parseInt(split[1]), Integer.parseInt(split[2]), plr, config));
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

    /**
     * KeyListener methods
     */
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

    /**
     * Checks collisions of all active sprites
     * Adds colliding sprites to the array of collisions
     * calls collisionEnter / collisionLeave
     */
    public void handleCollisions() {
        for (int i = 0; i < activeSprites.size(); i++) {
            if (!(activeSprites.get(i) instanceof ColliderObject col1))
                continue;

            for (int j = 0; j < activeSprites.size(); j++) {
                if (!(activeSprites.get(j) instanceof ColliderObject col2))
                    continue;

                if (col1 == col2) continue;

                double distanceSquared = square(col2.positionX - col1.positionX) + square(col2.positionY - col1.positionY);
                double collisionDistanceSquared = square(col1.radius + col2.radius);

                if (distanceSquared <= collisionDistanceSquared) {
                    if (!col1.collisions.contains(col2)) {
                        col1.collisions.add(col2);
                        col1.collisionEnter(col2);
                    }
                } else {
                    if (col1.collisions.contains(col2)) {
                        col1.collisions.remove(col2);
                        col1.collisionLeave(col2);
                    }
                }
            }
        }
    }

    public EngineConfig getEngineConfig() {
        return config;
    }

    public void nextLevel() {
        loadNextLevel = true;
    }

    /**
     * Counts enemies alive (used for opening door/portal to the next level)
     */
    public int countEnemies() {
        int count = 0;
        for (Sprite s : activeSprites) {
            if (s instanceof Alive && !(s instanceof Player) && !(s instanceof Bubble) && !(s instanceof Background))
                count++;
        }
        return count;
    }
}