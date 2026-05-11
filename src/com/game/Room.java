package com.game;

public class Room {

    private int gridX, gridY;
    private Room[] neighbors;
    private boolean[] doors;
    private int distanceFromEntry;
    private boolean isEntry, isExit;

    public Room(int x, int y){
        this.gridX = x;
        this.gridY = y;
        this.neighbors = new Room[4];
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

    public void setNeighbors(Room[] neighbors) {
        this.neighbors = neighbors;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public Room[] getNeighbors() {
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
