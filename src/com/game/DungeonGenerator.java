package com.game;

import java.util.ArrayList;
import java.util.Collections;
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
        DungeonDirection[] directions = DungeonDirection.values();
        DungeonDirection entryWall = directions[random.nextInt(4)];

        int entryX;
        int entryY;

        //wall logic
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

        ArrayList<DungeonDirection> freeDirections = new ArrayList<>();

        //loop to decide available expansion directions and adding rooms to queue
        while(!this.queue.isEmpty() && roomCount<roomMin){

            Room current = queue.pollFirst();

            for(DungeonDirection direction : directions){
                int neighborRow = current.getGridX() + direction.getDy();
                int neighborColumn = current.getGridY() + direction.getDx();

                if(neighborRow >= 0 && neighborRow <= 4 && neighborColumn >= 0 && neighborColumn <= 4 && grid[neighborRow][neighborColumn] == null){
                    freeDirections.add(direction);
                }
            }
            if(freeDirections.isEmpty()) continue;

            int branches = random.nextInt(freeDirections.size()) + 1;
            Collections.shuffle(freeDirections);

            for(int i=0; i<branches; i++){
                Room newRoom = new Room(current.getGridX() + freeDirections.get(i).getDy(), current.getGridY() + freeDirections.get(i).getDx());
                newRoom.setDistanceFromEntry(current.getDistanceFromEntry() + 1);
                current.addNeighbor(freeDirections.get(i), newRoom);
                newRoom.addNeighbor(freeDirections.get(i).getOpposite(), current);
                queue.addLast(newRoom);
                grid[newRoom.getGridX()][newRoom.getGridY()] = newRoom;
                roomCount += 1;
            }

            freeDirections.clear();
        }

        Room exitRoom = null;
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(grid[row][col] != null){
                    if(exitRoom == null || grid[row][col].getDistanceFromEntry() > exitRoom.getDistanceFromEntry()){
                        exitRoom = grid[row][col];
                    }
                }
            }
        }
        exitRoom.setExit(true);

        printGrid();
        return this.grid;
    }

    public void printGrid(){
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(grid[row][col] == null)            System.out.print(" .  ");
                else if(grid[row][col].isEntry())     System.out.print("[S] ");
                else if(grid[row][col].isExit())      System.out.print("[E] ");
                else                                   System.out.print("[R] ");
            }
            System.out.println();
        }
        System.out.println("Total rooms: " + roomCount);
    }

}
