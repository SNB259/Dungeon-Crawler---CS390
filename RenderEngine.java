import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {

    private ArrayList<Displayable> renderList = new ArrayList<Displayable>();

    public RenderEngine() {
        this.renderList = renderList;
    }

    public void setRenderList(ArrayList<Displayable> renderList){
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable){
        renderList.add(displayable);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (int i=0; i<renderList.size(); i++) {
            renderList.get(i).draw(g);
        }
    }

    @Override
    public void update() {
//        System.out.println("display");
        repaint();
    }
}
