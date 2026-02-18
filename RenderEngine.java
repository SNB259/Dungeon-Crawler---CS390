import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {

    private ArrayList<Displayable> renderList = new ArrayList<Displayable>();
    private GameEngine gameEngine;

    public RenderEngine(GameEngine gameEngine) {
        this.renderList = renderList;
        this.gameEngine = gameEngine;
    }

    public void setRenderList(ArrayList<Displayable> renderList){
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable){
        renderList.add(displayable);
    }

    private void drawMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Welcome! Press SPACE to Start", 150, 300);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Game Over! Press SPACE to Try Again", 150, 300);
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

        if (gameEngine.getGameState() == GameState.PLAYING) {
            for (int i=0; i<renderList.size(); i++) {
                renderList.get(i).draw(g);
            }
        }

    }

    @Override
    public void update() {
//        System.out.println("display");
        repaint();
    }
}
