package com.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends DynamicSprite{

    private int health;
    private int damage;
    private DynamicSprite hero;
    EnemyState state;
    int patrolCounter;
    int patrolDuration;
    double detectionRadius;
    double detectionBuffer;
    private Random random;
    private final int FRAME_WIDTH = 64;
    private final int FRAME_HEIGHT = 64;
    private boolean isStunned;
    private int stunCounter;
    private int stunDuration;

    public Enemy(DynamicSprite hero, Image image, double posX, double posY, double sizeX, double sizeY){
        super(image, posX, posY, sizeX, sizeY);
        this.health = 3;
        this.damage = 2;
        this.hero = hero;
        this.patrolCounter = 0;
        this.patrolDuration = 40;
        this.detectionRadius = 200;
        this.detectionBuffer = 250;
        this.state = EnemyState.PATROL;
        this.random = new Random();
        this.speed = 1;
        this.isStunned = false;
        this.stunCounter = 0;
        this.stunDuration = 30;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void moveIfPossible(ArrayList<Sprite> environment){

        if(isStunned){
            stunCounter++;
            if(stunCounter >= stunDuration){
                isStunned = false;
                stunCounter =0;
            }
            return;
        }

        Direction[] directions = Direction.values();

        double distanceToHero = Math.sqrt(Math.pow(this.hero.getPosX()-this.getPosX(), 2) + Math.pow(this.hero.getPosY()-this.getPosY(), 2));

        //patrolling: enemy moves in random directions in intervals of 2 seconds (standby)
        if(this.state == EnemyState.PATROL){
            if(distanceToHero < this.detectionRadius){
                this.state = EnemyState.CHASE;
                this.speed = 3;
            }
            else {
                patrolCounter++;

                if (patrolCounter > patrolDuration) {
                    patrolCounter = 0;
                    setDirection(directions[random.nextInt(directions.length)]);
                }
            }
        }
        //chasing: when hero enters detection radius, it calculates fastest way to reach hero and chases it
        else{
            if(distanceToHero > detectionBuffer){
                this.state = EnemyState.PATROL;
                patrolCounter = patrolDuration;
                this.speed = 1;
            }
            else {
                double deltaX = this.hero.getPosX() - this.getPosX();
                double deltaY = this.hero.getPosY() - this.getPosY();

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX >= 0) {
                        setDirection(Direction.EAST);
                    } else {
                        setDirection(Direction.WEST);
                    }
                }
                if (Math.abs(deltaY) > Math.abs(deltaX)) {
                    if (deltaY >= 0) {
                        setDirection(Direction.SOUTH);
                    } else {
                        setDirection(Direction.NORTH);
                    }
                }

                attack();
            }
        }

        super.moveIfPossible(environment);
    }

    public void takeHit(){
        health -= 1;
        isStunned = true;
        stunCounter = 0;
    }

    public void attack(){
        double deltaX = this.hero.getPosX() - this.getPosX();
        double deltaY = this.hero.getPosY() - this.getPosY();
        double distance = Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
        if(distance < 70){
            this.hero.takeDamage(damage);
        }
    }

    //draw method overridden to compensate for spritesheet incompatibility
    @Override
    public void draw(Graphics g){
        //
        int index = (int)((System.currentTimeMillis()/timeBetweenFrame) % 5);

        int attitude;
        //instead of making a new enum, a switch case is used
        switch(direction){
            case SOUTH: attitude = 0; break;
            case NORTH: attitude = 1; break;
            case WEST:  attitude = 2; break;
            case EAST:  attitude = 3; break;
            default:    attitude = 0;
        }

        int sourceXStart = index * FRAME_WIDTH;
        int sourceYStart = attitude * FRAME_HEIGHT;
        int sourceXEnd = (index + 1) * FRAME_WIDTH;
        int sourceYEnd = (attitude + 1) * FRAME_HEIGHT;

        int destinationXStart = (int)getPosX();
        int destinationYStart = (int)getPosY();
        int destinationXEnd = (int)getPosX() + (int)getSizeX();
        int destinationYEnd = (int)getPosY() + (int)getSizeY();

        g.drawImage(getImage(), destinationXStart, destinationYStart, destinationXEnd, destinationYEnd, sourceXStart, sourceYStart, sourceXEnd, sourceYEnd, null);
    }

}
