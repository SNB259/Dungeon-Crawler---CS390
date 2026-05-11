package com.game;

public enum DungeonDirection {

    NORTH(0, -1), SOUTH(0, 1), EAST(1, 0), WEST(1, 0);
    private final int dx;
    private final int dy;


    DungeonDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public DungeonDirection getOpposite(){
        switch(this) {
            case NORTH: return SOUTH;
            case SOUTH: return NORTH;
            case EAST:  return WEST;
            case WEST:  return EAST;
            default: throw new IllegalStateException();
        }
    }
}


