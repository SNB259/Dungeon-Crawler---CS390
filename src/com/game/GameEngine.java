package com.game;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameEngine implements Engine, KeyListener{

    public Random random;
    private GameState gameState;
    private DungeonGenerator dungeonGenerator;
    private Room[][] dungeonGrid;
    private Room currentRoom;
    private final DynamicSprite reference;
//    private final DynamicSprite goblinReference;
    PhysicEngine physicEngine;
    RenderEngine renderEngine;
    Playground playground;

    GameEngine(DynamicSprite reference, PhysicEngine physicEngine, Playground playground, Room[][] dungeonGrid, Room currentRoom){
        this.physicEngine = physicEngine;
        this.playground = playground;
        this.reference = reference;
        this.dungeonGrid = dungeonGrid;
        this.currentRoom = currentRoom;
        this.gameState = GameState.MENU;
        this.random = new Random();
    }

    @Override
    public void update() {
        if(gameState == GameState.MENU){

        }
        if (gameState == GameState.PLAYING) {
            physicEngine.update();
        }
//        if (!reference.isWalking){
//            setGameState(GameState.GAMEOVER);
//        }

        if((reference.getPosX() > 576 || reference.getPosX() < 0 || reference.getPosY() > 600 || reference.getPosY() < 0) && gameState == GameState.PLAYING){
            setGameState(GameState.TRANSITION);
        }
    }

    public void setRenderEngine(RenderEngine renderEngine){
        this.renderEngine = renderEngine;
    }

    public void setGameState(GameState newState) {

        if (this.gameState != newState) {   // only if actually changing
            this.gameState = newState;
            if(gameState == GameState.PLAYING){

            }

            if(gameState == GameState.GAMEOVER){

            }
            else if (gameState == GameState.TRANSITION){
                DungeonDirection heroDirection = toDungeonDirection(reference.getDirection());
                Room nextRoom = this.currentRoom.getNeighbors().get(heroDirection);

                if(nextRoom != null){
                    loadRoom(nextRoom);
                    this.currentRoom = nextRoom;
                }

                switch (heroDirection){
                    case NORTH:
                        reference.setDirection(Direction.NORTH);
                        reference.setPosX(256);
                        reference.setPosY(512);
                        break;
                    case SOUTH:
                        reference.setDirection(Direction.SOUTH);
                        reference.setPosX(256);
                        reference.setPosY(64);
                        break;
                    case EAST:
                        reference.setDirection(Direction.EAST);
                        reference.setPosX(64);
                        reference.setPosY(256);
                        break;
                    case WEST:
                        reference.setDirection(Direction.WEST);
                        reference.setPosX(512);
                        reference.setPosY(256);
                        break;
                }
            }
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

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    private DungeonDirection toDungeonDirection(Direction dir){
        switch(dir){
            case NORTH: return DungeonDirection.NORTH;
            case SOUTH: return DungeonDirection.SOUTH;
            case EAST:  return DungeonDirection.EAST;
            case WEST:  return DungeonDirection.WEST;
            default: throw new IllegalStateException();
        }
    }

    public void loadRoom(Room room){

        playground.reload();
        playground.loadPlayground(room);
        physicEngine.setEnvironment(playground.getSolidSpriteList());
        renderEngine.clearRenderList();
        for(Displayable sprite : playground.getSpriteList()){
            renderEngine.addToRenderList(sprite);
        }
        renderEngine.addToRenderList(reference);
        physicEngine.clearMovingSpriteList();
        physicEngine.addToMovingSpriteList(reference);
    }

}
