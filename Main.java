import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    JFrame displayZoneFrame;

    RenderEngine renderEngine;

    public Main() throws Exception {
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        renderEngine = new RenderEngine();

        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        renderTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);

        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("The assets-20260206/img/heroTileSheetLowRes.png")),200,300, 48,50);
        renderEngine.addToRenderList(hero);

        displayZoneFrame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }
}

