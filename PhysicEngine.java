import java.util.ArrayList;
public class PhysicEngine implements Engine{

    ArrayList<DynamicSprite> movingSpriteList;
    ArrayList<Sprite> environment;

    PhysicEngine(){
        this.movingSpriteList = new ArrayList<>();
        this.environment = new ArrayList<>();
    }

    public void addToMovingSpriteList(DynamicSprite movingSprite){
        this.movingSpriteList.add(movingSprite);
    }

    public void clearMovingSpriteList(){
        this.movingSpriteList.clear();
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment = environment;
    }

    @Override
    public void update() {
        for(int i=0; i<movingSpriteList.size(); i++){
            movingSpriteList.get(i).moveIfPossible(environment);
        }
    }
}
