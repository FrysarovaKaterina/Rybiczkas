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

public class DoorTest {
    @Test
    public void collisionWithPlayer() {
        GameEngine engine = new GameEngine(new Canvas(1920,1080).getGraphicsContext2D(), new EngineConfig(1920,1080,60), new String[]{});
        Player plr = new Player(50,50,engine);
        Door door = new Door(50,50,engine);
        Assertions.assertTrue(!door.getOpened());
        door.collisionEnter(plr);
        Assertions.assertTrue(door.getOpened());
    }

}
