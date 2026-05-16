package com.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {

    private ArrayList<Displayable> renderList;
    private GameEngine gameEngine;

    public RenderEngine(GameEngine gameEngine) {
        this.renderList = new ArrayList<Displayable>();
        this.gameEngine = gameEngine;
    }

    public void setRenderList(ArrayList<Displayable> renderList){
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable){
        renderList.add(displayable);
    }
    public void clearRenderList(){
        renderList.clear();
    }

    private void drawCenteredString(Graphics g, String text, int y){
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int x = (getWidth() - textWidth) / 2;
        g.drawString(text, x, y);
    }

    private void drawMenu(Graphics g) {
        g.setColor(Color.BLACK);
        drawCenteredString(g, "Welcome! Press SPACE to Start", 300);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        drawCenteredString(g, "Game Over! Press SPACE to Try Again", 300);
    }

    private void drawTransition(Graphics g) {
        g.setColor(Color.BLACK);
        drawCenteredString(g, "Room Cleared! Press SPACE to Continue", 300);
    }

    private void drawVictory(Graphics g) {
        g.setColor(Color.BLACK);
        drawCenteredString(g, "Congrats, you WON! Restart to play again", 300);
    }

    private void drawHealth(Graphics g, int health){
        g.setColor(Color.BLACK);
        String str = "HEALTH: %,d";
        String fstr = String.format(str, health);
        g.drawString(fstr, 50, 605);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        if (gameEngine.getGameState() == GameState.MENU) {
            drawMenu(g);
        }

        if (gameEngine.getGameState() == GameState.GAMEOVER){
            drawGameOver(g);
        }

        if(gameEngine.getGameState() == GameState.TRANSITION){
            drawTransition(g);
        }

        if(gameEngine.getGameState() == GameState.VICTORY){
            drawVictory(g);
        }

        if (gameEngine.getGameState() == GameState.PLAYING) {
            for (int i=0; i<renderList.size(); i++) {
                renderList.get(i).draw(g);
            }
            drawHealth(g, gameEngine.getReference().getHealth());
        }


    }

    @Override
    public void update() {
        repaint();
    }
}
