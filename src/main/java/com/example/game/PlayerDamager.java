package com.example.game;

import java.util.Set;

public class PlayerDamager {
    private int framesSinceLastHurt = 9999999;
    private int timerMax;

    /**
     * Constructs a new PlayerDamager object with the specified timer value.
     *
     * @param timerMax The maximum amount of frames that must pass before the Player can be hurt again.
     */
    public PlayerDamager(int timerMax) {
        this.timerMax = timerMax;
    }

    public void max() {
        framesSinceLastHurt = 99999999;
    }

    /**
     * Checks for collisions with the player and
     * applies damage if necessary based on the timer value.
     *
     * @param collisions A set of ColliderObject objects representing the objects the PlayerDamager
     *                   is colliding with.
     * @param damage     The amount of damage to apply to the player character.
     */
    public void update(Set<ColliderObject> collisions, int damage) {
        framesSinceLastHurt = (framesSinceLastHurt + 1) % Integer.MAX_VALUE;
        for (ColliderObject other : collisions) {
            if (other instanceof Player && framesSinceLastHurt >= timerMax) {
                if (!((Player) other).shieldActive)
                    ((Player) other).energy -= damage;
                framesSinceLastHurt = 0;
            }
        }
    }
}
