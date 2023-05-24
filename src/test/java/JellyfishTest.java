import com.example.game.*;

import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class JellyfishTest {
    @Test
    public void enemiesNotHurtingPlayer() {
        var engine = new GameEngine(new Canvas(1920,1080).getGraphicsContext2D(), new EngineConfig(1920,1080,60), new String[]{}) {
            public void addSpriteImmediate(Sprite s) {
                activeSprites.add(s);
            }
        };

        Player plr = new Player(50,50,engine);
        plr.energy=10;
        JellyFish jellyfish = new JellyFish(1000, 100, plr, engine.getEngineConfig());

        engine.addSpriteImmediate(plr);
        engine.addSpriteImmediate(jellyfish);

        engine.handleCollisions();
        jellyfish.update();
        plr.update();

        Assertions.assertTrue(plr.energy == 10 - jellyfish.getDamage());
    }
}
