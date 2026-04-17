package com.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
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
        DynamicSprite goblin = new DynamicSprite(ImageIO.read(new File("The assets-20260206/img/purpleGoblinSpriteSheet.png")),200,300, 48,50);

        playground = new Playground("The assets-20260206/level/randLevel.txt");
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, goblin, physicEngine, playground);
        renderEngine = new RenderEngine(gameEngine);
        gameEngine.setRenderEngine(renderEngine);

        renderEngine.addToRenderList(hero);
        renderEngine.addToRenderList(goblin);

        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        renderTimer.start();
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        gameTimer.start();

        displayZoneFrame.addKeyListener(gameEngine);
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        for(int i=0; i<playground.getSpriteList().size(); i++){
            renderEngine.addToRenderList(playground.getSpriteList().get(i));
        }
        renderEngine.addToRenderList(hero);
        renderEngine.addToRenderList(goblin);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.addToMovingSpriteList(goblin);
        physicEngine.setEnvironment(playground.getSolidSpriteList());
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }
}

