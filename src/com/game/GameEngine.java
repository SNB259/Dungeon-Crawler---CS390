package com.game;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameEngine implements Engine, KeyListener{

    public Random random;
    private GameState gameState;
    private final DynamicSprite reference;
    private final DynamicSprite goblinReference;
    PhysicEngine physicEngine;
    RenderEngine renderEngine;
    StringBuilder levelString;
    Playground playground;
    private int score;
    int rockAmount;
//    private int chance;

    GameEngine(DynamicSprite reference, DynamicSprite goblinReference, PhysicEngine physicEngine, Playground playground){
        this.physicEngine = physicEngine;
        this.playground = playground;
        this.reference = reference;
        this.goblinReference = goblinReference;
        this.gameState = GameState.MENU;
        this.random = new Random();
        this.score = 0;
        this.rockAmount = 4;
//        this.chance = random.nextInt(2);
        System.out.println(gameState);
    }

    @Override
    public void update() {
        if(gameState == GameState.MENU){
            score = 0;
            rockAmount = 4;
        }
        if (gameState == GameState.PLAYING) {
            physicEngine.update();
        }
        if (!reference.isWalking){
            setGameState(GameState.GAMEOVER);
        }

        if((reference.getPosX() > 400 || reference.getPosX() < 0 || reference.getPosY() > 600 || reference.getPosY() < 0) && gameState == GameState.PLAYING){
            setGameState(GameState.TRANSITION);
        }

    }

    public int getScore(){
        return score;
    }

    public void setRenderEngine(RenderEngine renderEngine){
        this.renderEngine = renderEngine;
    }

    public void setGameState(GameState newState) {

        if (this.gameState != newState) {   // only if actually changing
            this.gameState = newState;

            if(gameState == GameState.GAMEOVER){
                score-=1;
                rockAmount = 5;
            }
            else if (gameState == GameState.TRANSITION){
                score += 1;
                rockAmount += 1;
            }

            resetLevel(rockAmount);  // runs once per change
        }
    }

    public GameState getGameState(){
        return gameState;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            this.gameState = GameState.PLAYING;
        }

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                reference.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_RIGHT:
                reference.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_LEFT:
                reference.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_DOWN:
                reference.setDirection(Direction.SOUTH);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void resetLevel(int amount){
        char[][] container = new char[6][9];
        levelString = new StringBuilder();
        int placed = 0;

        while (placed < amount) {
            int rowRandIdx = random.nextInt(7) + 1;
            int columnRandIdx = random.nextInt(4) + 1;

            if (container[rowRandIdx][columnRandIdx] != 'R' && !(rowRandIdx==4 && columnRandIdx==3) && !(rowRandIdx==5 && columnRandIdx==3) && !(rowRandIdx==6 && columnRandIdx==3) && !(rowRandIdx==5 && columnRandIdx==4)) {
                container[rowRandIdx][columnRandIdx] = 'R';
                placed++;
            }
        }

        for(int i=0; i< container.length; i++){
            for(int j=0; j<container[i].length; j++){
                if(i==0 || i==8){
                    container[i][j] = 'T';
                }
                else {

                    container[i][0] = 'T';
                    container[i][5] = 'T';
                    if(container[i][j] != 'T' && container[i][j] != 'R'){
                        container[i][j] = ' ';
                    }
                }
            }
        }

        int preRowIdx = random.nextInt(7) + 1;
        int preColIdx = random.nextInt(4)+ 1;
        int chance = random.nextInt(2);
        int sndChance = random.nextInt(2);

        if (chance == 0){
            if (sndChance == 0){
                container[0][preColIdx] = 'E';
            }
            else {
                container[8][preColIdx] = 'E';
            }
        }
        else{
            if (sndChance == 0){
                container[preRowIdx][0] = 'E';
            }
            else {
                container[preRowIdx][5] = 'E';
            }
        }

        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[i].length; j++) {
                levelString.append(container[i][j]);
            }
            levelString.append("\n");
        }

        try{
            Files.writeString(Path.of("The assets-20260206/level/randLevel.txt"), levelString);
        } catch (Exception e){
            System.out.println("Error in file retrieval when resetting level");
        }

        playground.reload();

        physicEngine.setEnvironment(playground.getSolidSpriteList());

        physicEngine.clearMovingSpriteList();
        reference.resetPos(gameState);
        physicEngine.addToMovingSpriteList(reference);

        renderEngine.clearRenderList();

        for (Displayable sprite : playground.getSpriteList()) {
            renderEngine.addToRenderList(sprite);
        }

        renderEngine.addToRenderList(reference);
    }
}
