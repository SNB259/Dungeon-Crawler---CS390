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
        displayZoneFrame.setSize(576, 650); //actual game height = 600
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("The assets-20260206/img/heroTileSheetLowRes.png")),200,300, 48,50);

        DungeonGenerator dungeonGenerator = new DungeonGenerator(5, 5, 8 );
        Room [][] dungeonGrid = dungeonGenerator.generateDungeon();

        Room entryRoom = null;
        for(int row=0; row<dungeonGrid.length; row++){
            for(int col=0; col<dungeonGrid[row].length; col++){
                if(dungeonGrid[row][col]!=null && dungeonGrid[row][col].isEntry()){
                    entryRoom = dungeonGrid[row][col];
                }
            }
        }

        playground = new Playground(entryRoom, 3, hero);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, physicEngine, playground, dungeonGrid, entryRoom);
        renderEngine = new RenderEngine(gameEngine);
        gameEngine.setRenderEngine(renderEngine);

        renderEngine.addToRenderList(hero);

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
        //Why?? redundant?
        renderEngine.addToRenderList(hero);
//        renderEngine.addToRenderList(goblin);
        physicEngine.addToMovingSpriteList(hero);
//        physicEngine.addToMovingSpriteList(goblin);
        physicEngine.setEnvironment(playground.getSolidSpriteList());
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }
}

