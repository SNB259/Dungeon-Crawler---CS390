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

        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, physicEngine);
        renderEngine = new RenderEngine(gameEngine);
        playground = new Playground("The assets-20260206/level/level1.txt");

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
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(playground.getSolidSpriteList());
//        gameEngine.resetLevel(5);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }
}

