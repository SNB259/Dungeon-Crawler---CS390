package com.game;

import java.util.HashMap;

public class Room {

    private int gridX, gridY;
    private HashMap<DungeonDirection, Room> neighbors;
    private boolean[] doors;
    private int distanceFromEntry;
    private boolean isEntry, isExit;

    public Room(int x, int y){
        this.gridX = x;
        this.gridY = y;
        this.neighbors = new HashMap<>();
        this.doors = new boolean[4];
    }

    public void setDistanceFromEntry(int distanceFromEntry) {
        this.distanceFromEntry = distanceFromEntry;
    }

    public void setEntry(boolean entry) {
        isEntry = entry;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public void setNeighbors(HashMap<DungeonDirection, Room> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(DungeonDirection direction, Room neighbor){
        this.neighbors.put(direction, neighbor);
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public HashMap<DungeonDirection, Room> getNeighbors() {
        return neighbors;
    }

    public boolean[] getDoors() {
        return doors;
    }

    public int getDistanceFromEntry() {
        return distanceFromEntry;
    }

    public boolean isEntry() {
        return isEntry;
    }

    public boolean isExit() {
        return isExit;
    }
}
