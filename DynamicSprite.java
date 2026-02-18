import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

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

    //breaks?
    private void move(){
        switch(direction){
            case NORTH -> {
                setPosY(getPosY() - speed);
            }
            case SOUTH -> {
                setPosY(getPosY() + speed);
            }
            case WEST -> {
                setPosX(getPosX() - speed);
            }
            case EAST -> {
                setPosX(getPosX() + speed);
            }
        }
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment){

        double offSetX = getPosX();
        double offSetY = getPosY();

        switch(direction) {
            case NORTH -> offSetY -= speed;
            case SOUTH -> offSetY += speed;
            case WEST  -> offSetX -= speed;
            case EAST  -> offSetX += speed;
        }

        Rectangle2D.Double offSetHitbox = new Rectangle2D.Double(offSetX, offSetY, getSizeX(), getSizeY());

        for(int i=0; i<environment.size(); i++){
            if(environment.get(i) instanceof SolidSprite && environment.get(i) != this){
                Rectangle2D.Double hitbox = new Rectangle2D.Double(environment.get(i).getPosX(), environment.get(i).getPosY(), environment.get(i).getSizeX(), environment.get(i).getSizeY());
                if(offSetHitbox.intersects(hitbox)){
                    return false;
                }
            }
        }

        return true;
    }

    public void moveIfPossible(ArrayList<Sprite> environment){
        if(isMovingPossible(environment)){
            move();
            isWalking = true;
        }
        else {
            isWalking = false;
        }

    }

    


}
