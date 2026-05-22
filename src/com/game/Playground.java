package com.game;

import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Playground {
    private ArrayList<Sprite> environment;
    private Room room;
    private ArrayList<Enemy> enemies;
    private Random random;
    private DynamicSprite hero;

    public Playground (Room room, int enemyCount, DynamicSprite hero) {
        this.environment = new ArrayList<Sprite>();
        this.room = room;
        this.enemies = new ArrayList<Enemy>();
        this.random = new Random();
        this.hero = hero;
        loadPlayground(room);
    }

    public void loadPlayground(Room room) {
        try {
            final Image imageTree = ImageIO.read(new File("The assets-20260206/img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("The assets-20260206/img/grass.png"));
            final Image imageRock = ImageIO.read(new File("The assets-20260206/img/rock.png"));
            final Image imageEnemy = ImageIO.read(new File("The assets-20260206/img/purpleGoblinSpriteSheet.png"));

            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            //Room construction

            int[][] roomGrid = new int[9][9];

            //Extracting doors (middle point of walls that connect to another room)
            boolean doorNorth = room.getNeighbors().containsKey(DungeonDirection.NORTH);
            boolean doorSouth = room.getNeighbors().containsKey(DungeonDirection.SOUTH);
            boolean doorWest  = room.getNeighbors().containsKey(DungeonDirection.WEST);
            boolean doorEast  = room.getNeighbors().containsKey(DungeonDirection.EAST);

            for(int row=0; row<roomGrid.length; row++) {
                for (int col = 0; col < roomGrid[row].length; col++) {
                    if (row == 0 || row == 8 || col == 0 || col == 8) {

                        boolean isDoor = (row == 0 && col == 4 && doorNorth) || (row == 8 && col == 4 && doorSouth) || (col == 0 && row == 4 && doorWest) || (col == 8 && row == 4 && doorEast);

                        if (isDoor) {
                            if(room.isEntry() || room.isCleared() || !room.isDoorLocked()){
                                environment.add(new Sprite(imageGrass, col * imageGrassWidth, row * imageGrassHeight, imageGrassWidth, imageGrassHeight));
                            } else {
                                environment.add(new SolidSprite(imageTree, col * imageTreeWidth, row * imageTreeHeight, imageTreeWidth, imageTreeHeight));
                            }
                        } else {
                            environment.add(new SolidSprite(imageTree, col * imageTreeWidth, row * imageTreeHeight, imageTreeWidth, imageTreeHeight));
                        }
                    } else {
                        environment.add(new Sprite(imageGrass, col * imageGrassWidth, row * imageGrassHeight, imageGrassWidth, imageGrassHeight));
                    }
                }
            }
            if (!room.isEntry() && !room.isCleared()) {
                if (!room.isExit()) {
                    int enemyCount = random.nextInt(4) + 2;
                    for (int i = 0; i < enemyCount; i++) {
                        int spawnX = random.nextInt(5) + 2;
                        int spawnY = random.nextInt(5) + 2;
                        //adapting to enemy's 64x64 size in spritesheet
                        int posX = spawnX * 64;
                        int posY = spawnY * 64;
                        Enemy enemy = new Enemy(this.hero, imageEnemy, posX, posY, 96, 96);
                        this.enemies.add(enemy);
                    }
                } else {
                    int posX = 3 * 64;
                    int posY = 4 * 64;
                    Enemy enemy = new Enemy(this.hero, imageEnemy, posX, posY, 180, 180);
                    enemy.setHealth(10);
                    enemy.setDamage(3);
                    this.enemies.add(enemy);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadTilesOnly(Room room){
        environment.clear();
        try {
            final Image imageTree = ImageIO.read(new File("The assets-20260206/img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("The assets-20260206/img/grass.png"));
            final Image imageRock = ImageIO.read(new File("The assets-20260206/img/rock.png"));

            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            int[][] roomGrid = new int[9][9];

            //Extracting doors (middle point of walls that connect to another room)
            boolean doorNorth = room.getNeighbors().containsKey(DungeonDirection.NORTH);
            boolean doorSouth = room.getNeighbors().containsKey(DungeonDirection.SOUTH);
            boolean doorWest = room.getNeighbors().containsKey(DungeonDirection.WEST);
            boolean doorEast = room.getNeighbors().containsKey(DungeonDirection.EAST);

            for (int row = 0; row < roomGrid.length; row++) {
                for (int col = 0; col < roomGrid[row].length; col++) {
                    if (row == 0 || row == 8 || col == 0 || col == 8) {

                        boolean isDoor = (row == 0 && col == 4 && doorNorth) || (row == 8 && col == 4 && doorSouth) || (col == 0 && row == 4 && doorWest) || (col == 8 && row == 4 && doorEast);

                        if (isDoor) {
                            if (room.isEntry() || room.isCleared() || !room.isDoorLocked()) {
                                environment.add(new Sprite(imageGrass, col * imageGrassWidth, row * imageGrassHeight, imageGrassWidth, imageGrassHeight));
                            } else {
                                environment.add(new SolidSprite(imageTree, col * imageTreeWidth, row * imageTreeHeight, imageTreeWidth, imageTreeHeight));
                            }
                        } else {
                            environment.add(new SolidSprite(imageTree, col * imageTreeWidth, row * imageTreeHeight, imageTreeWidth, imageTreeHeight));
                        }
                    } else {
                        environment.add(new Sprite(imageGrass, col * imageGrassWidth, row * imageGrassHeight, imageGrassWidth, imageGrassHeight));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeDeadEnemies(){
        enemies.removeIf(e -> e.getHealth() <= 0);
    }

    public void reload() {
        environment.clear();
        enemies.clear();
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }
}

