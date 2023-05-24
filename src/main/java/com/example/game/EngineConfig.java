package com.example.game;

public class EngineConfig {
    public int Width;
    public int Height;
    public int FPS;

    /**
     * Created for passing important configurations of the game engine.
     */
    public EngineConfig(int width, int height, int FPS) {
        Width = width;
        Height = height;
        this.FPS = FPS;
    }
}
