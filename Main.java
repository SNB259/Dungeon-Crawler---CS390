import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    Playground playground;

    public Main() throws Exception {
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("The assets-20260206/img/heroTileSheetLowRes.png")),200,300, 48,50);

        renderEngine = new RenderEngine();
        gameEngine = new GameEngine(hero);
        physicEngine = new PhysicEngine();
        playground = new Playground("The assets-20260206/level/level1.txt");

        renderEngine.addToRenderList(hero);

        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        renderTimer.start();
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        gameTimer.start();
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());
        physicTimer.start();

        displayZoneFrame.addKeyListener(gameEngine);
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
        
        for(int i=0; i<playground.getSpriteList().size(); i++){
            renderEngine.addToRenderList(playground.getSpriteList().get(i));
        }
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(playground.getSolidSpriteList());
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }
}

