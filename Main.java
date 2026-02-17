import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;

    public Main() throws Exception {
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        renderEngine = new RenderEngine();

        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("The assets-20260206/img/heroTileSheetLowRes.png")),200,300, 48,50);
        renderEngine.addToRenderList(hero);

        gameEngine = new GameEngine(hero);

        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        renderTimer.start();
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        gameTimer.start();

        displayZoneFrame.addKeyListener(gameEngine);
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }
}

