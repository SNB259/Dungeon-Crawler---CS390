import java.awt.*;

public class Sprite implements Displayable{

    private Image image;
    private double posX, posY;
    private double sizeX, sizeY;

    public Sprite(Image image, double posX, double posY, double sizeX, double sizeY){
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(image, (int)posX, (int)posY, (int)sizeX, (int)sizeY, null);
    }

    public Image getImage() {
        return image;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }



}
