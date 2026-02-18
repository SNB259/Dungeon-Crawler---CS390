import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameEngine implements Engine, KeyListener{

    public Random random;
    private GameState gameState;
    private final DynamicSprite reference;
    PhysicEngine physicEngine;
    StringBuilder levelString;

    GameEngine(DynamicSprite reference, PhysicEngine physicEngine){
        this.physicEngine = physicEngine;
        this.reference = reference;
        this.gameState = GameState.MENU;
        this.random = new Random();
        System.out.println(gameState);
    }

    @Override
    public void update() {
        if (gameState == GameState.PLAYING) {
            physicEngine.update();
        }
        if (!reference.isWalking){
            gameState = GameState.GAMEOVER;
        }

        if(reference.getPosX() > 400 && gameState == GameState.PLAYING){
            gameState = GameState.TRANSITION;
        }

        reference.resetPos(gameState);
    }

    public void setGameState(GameState newState) {

        if (this.gameState != newState) {   // only if actually changing
            this.gameState = newState;

            resetLevel();  // runs once per change
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

    public void resetLevel(int amount){
        char[][] container = new char[9][6];
        levelString = new StringBuilder();
        int placed = 0;

        while (placed < amount) {

            int rowRandIdx = random.nextInt(7) + 1;
            int columnRandIdx = random.nextInt(4) + 1;

            if (container[rowRandIdx][columnRandIdx] != 'R' && !(rowRandIdx==4 && columnRandIdx==3) && !(rowRandIdx==5 && columnRandIdx==3) && !(rowRandIdx==6 && columnRandIdx==3)) {
                container[rowRandIdx][columnRandIdx] = 'R';
                placed++;
            }
        }

        for(int i=0; i< container.length; i++){
            for(int j=0; j<container[i].length; j++){
                if(i==0 || i==8){
                    container[i][j] = 'T';
                }
                else {

                    container[i][0] = 'T';
                    container[i][5] = 'T';
                    if(container[i][j] != 'T' && container[i][j] != 'R'){
                        container[i][j] = ' ';
                    }
                }
            }
        }
        container[5][5] = 'E';

        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[i].length; j++) {
                levelString.append(container[i][j]);
            }
            levelString.append("\n"); // very important
        }
        System.out.println(levelString);

        try{
            Files.writeString(Path.of("The assets-20260206/level/randLevel.txt"), levelString);
        } catch (Exception e){
            System.out.println("Error in file retrieval when resetting level");
        }
    }
}

//TTTTTT
//T    T
//T R  T
//T    E
//T   RT
//T    T
//T    T
//T  R T
//TTTTTT
