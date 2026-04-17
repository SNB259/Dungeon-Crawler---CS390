package com.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Playground {
    private ArrayList<Sprite> environment;
    String pathname;
    public Playground (String pathName) {
        this.pathname = pathName;
        this.environment = new ArrayList<Sprite>();
        loadPlayground(pathName);
    }

    public void loadPlayground(String pathname) {
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

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathname));
            String line = bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line != null) {
                for (byte element : line.getBytes(StandardCharsets.UTF_8)) {
                    switch (element) {
                        case 'T':
                            environment.add(new SolidSprite(imageTree, columnNumber * imageTreeWidth,
                                    lineNumber * imageTreeHeight, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ', 'E':
                            environment.add(new Sprite(imageGrass, columnNumber * imageGrassWidth,
                                    lineNumber * imageGrassHeight, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R':
                            environment.add(new SolidSprite(imageRock, columnNumber * imageRockWidth,
                                    lineNumber * imageRockHeight, imageRockWidth, imageRockHeight));
                            break;
                    }
                    columnNumber++;
                }
                columnNumber = 0;
                lineNumber++;
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        environment.clear();
        loadPlayground(this.pathname);
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

