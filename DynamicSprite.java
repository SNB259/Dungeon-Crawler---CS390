import java.awt.*;

public class DynamicSprite extends SolidSprite{

    boolean isWalking;
    double speed;
    final int spriteSheetNumberOfColumn = 10;
    int timeBetweenFrame;
    Direction direction;

    DynamicSprite(Image image, double posX, double posY, double sizeX, double sizeY){
        super(image, posX, posY, sizeX, sizeY);
        this.isWalking = true;
        this.speed = 5;
        this.timeBetweenFrame = 200;
        this.direction = Direction.SOUTH;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g){
        int index = (int)((System.currentTimeMillis()/timeBetweenFrame)%spriteSheetNumberOfColumn);

        int attitude = direction.getFrameLineNumber();

        int sourceXStart = (index*(int)super.getSizeX());
        int sourceYStart = attitude*(int)super.getSizeY();
        int sourceXEnd = (index+1)*(int)super.getSizeX();
        int sourceYEnd = (attitude+1)*(int)super.getSizeY();

        int destinationXStart = (int)super.getPosX();
        int destinationYStart = (int)super.getPosY();
        int destinationXEnd = (int)super.getPosX() + (int)super.getSizeX();
        int destinationYEnd = (int)super.getPosY() + (int)super.getSizeY();

        g.drawImage(super.getImage(), destinationXStart, destinationYStart, destinationXEnd, destinationYEnd, sourceXStart, sourceYStart, sourceXEnd, sourceYEnd, null);
    }

}
