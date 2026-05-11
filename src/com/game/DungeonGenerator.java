package com.game;

import java.util.LinkedList;
import java.util.Random;

public class DungeonGenerator {

    private Room[][] grid;
    Random random;
    private LinkedList<Room> queue;
    private int roomCount;
    private int roomMin;

    public DungeonGenerator(int gridRows, int gridColumns, int roomMin){
        this.grid = new Room[gridRows][gridColumns];
        this.roomMin = roomMin;
        this.random = new Random();
        this.queue = new LinkedList<Room>();
        this.roomCount = 0;
    }

    public Room[][] generateDungeon(){

        //pick random wall to attach entry
        DungeonDirection[] walls = DungeonDirection.values();
        DungeonDirection entryWall = walls[random.nextInt(4)];

        int entryX;
        int entryY;

        switch(entryWall){
            case NORTH:
                entryX = 0;
                entryY = random.nextInt(5);
                break;
            case SOUTH:
                entryX = 4;
                entryY = random.nextInt(5);
                break;
            case WEST:
                entryX = random.nextInt(5);
                entryY = 0;
                break;
            case EAST:
                entryX = random.nextInt(5);
                entryY = 4;
                break;
            default:
                entryX = 0;
                entryY = 0;
        }

        Room entryRoom = new Room(entryX, entryY);
        entryRoom.setEntry(true);
        entryRoom.setDistanceFromEntry(0);
        this.grid[entryX][entryY] = entryRoom;
        this.queue.add(entryRoom);
        roomCount += 1;
        
        return this.grid;
    }

    private boolean wallCheck(Room[][]grid, int roomX, int roomY){
        boolean isWall = false;

        if ((roomX+1) > 4 || (roomX-1) < 0){
            isWall = true;
        }
        if ((roomY+1) > 4 || (roomY-1) < 0){

        }

        return isWall;
    }
}
