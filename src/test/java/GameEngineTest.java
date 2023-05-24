import com.example.game.Door;
import com.example.game.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.util.Arrays;

public class GameEngineTest {
    @Test
    public void handleCollisionsTest() {
        var engine = new GameEngine(new Canvas(1920,1080).getGraphicsContext2D(), new EngineConfig(1920,1080,60), new String[]{}) {
            public void addSpriteImmediate(Sprite s) {
                activeSprites.add(s);
            }
        };

        Bubble bubble = new Bubble(100,100, Alive.Side.RIGHT, engine.getEngineConfig());
        Piranha piranha = new Piranha (120, 100, engine.getEngineConfig());

        engine.addSpriteImmediate(bubble);
        engine.addSpriteImmediate(piranha);

        Assertions.assertFalse(bubble.collisions.contains(piranha));
        Assertions.assertFalse(piranha.collisions.contains(bubble));
        engine.handleCollisions();
        Assertions.assertTrue(bubble.collisions.contains(piranha));
        Assertions.assertFalse(bubble.collisions.contains(bubble));
        Assertions.assertTrue(piranha.collisions.contains(bubble));

        bubble.positionX = 1000;
        bubble.positionY = 1000;
        engine.handleCollisions();
        Assertions.assertFalse(bubble.collisions.contains(piranha));
        Assertions.assertFalse(piranha.collisions.contains(bubble));
    }

}
