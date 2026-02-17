import javax.imageio.ImageIO;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.io.File;

public class GameEngine implements Engine, KeyListener{

    private final DynamicSprite reference;

    GameEngine(DynamicSprite reference){
        this.reference = reference;
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                reference.setDirection(Direction.NORTH);
                System.out.println(reference.direction);
                break;
            case KeyEvent.VK_RIGHT:
                reference.setDirection(Direction.EAST);
                System.out.println(reference.direction);
                break;
            case KeyEvent.VK_LEFT:
                reference.setDirection(Direction.WEST);
                System.out.println(reference.direction);
                break;
            case KeyEvent.VK_DOWN:
                reference.setDirection(Direction.SOUTH);
                System.out.println(reference.direction);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
