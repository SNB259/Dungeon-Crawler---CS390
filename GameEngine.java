import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameEngine implements Engine, KeyListener{


    private GameState gameState;
    private final DynamicSprite reference;
    PhysicEngine physicEngine;

    GameEngine(DynamicSprite reference){
        this.physicEngine = new PhysicEngine();
        this.reference = reference;
        this.gameState = GameState.MENU;
        System.out.println(gameState);
    }

    @Override
    public void update() {
        if (gameState == GameState.PLAYING) {
            physicEngine.update();
//            Timer physicTimer = new Timer(50,(time)-> physicEngine.update());
//            physicTimer.start();
        }
        if (!reference.isWalking){
            gameState = GameState.GAMEOVER;
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
            System.out.println(gameState);
        }

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                reference.setDirection(Direction.NORTH);
//                System.out.println(reference.direction);
                break;
            case KeyEvent.VK_RIGHT:
                reference.setDirection(Direction.EAST);
//                System.out.println(reference.direction);
                break;
            case KeyEvent.VK_LEFT:
                reference.setDirection(Direction.WEST);
//                System.out.println(reference.direction);
                break;
            case KeyEvent.VK_DOWN:
                reference.setDirection(Direction.SOUTH);
//                System.out.println(reference.direction);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
