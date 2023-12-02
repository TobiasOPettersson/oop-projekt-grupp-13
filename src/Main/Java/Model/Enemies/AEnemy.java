package Model.Enemies;


import java.util.List;

import Model.Enums.Direction;
import Model.Interfaces.IMovable;
import Model.Interfaces.ITargetable;

/**
 * An abstract class representing an enemy in the game.
 * This class implements the IMovable interface.
 */
public abstract class AEnemy implements IMovable, ITargetable {
    private int health; //health points
    private double x, y; //position
    private double speed; //movement speed of an enemy
    private EnemyType type; //The type of enemy
    private List<Direction> directions; //List of directions for the enemy to follow when moving
    private int damage;
    private int moneyBag;
    private double tileOffset = 0.5;
    
    public AEnemy(int health, double y, double speed, EnemyType type, List<Direction> directions, int damage, int moneyBag) {
        this.health = health;
        this.x = 0;
        this.y = y + tileOffset;
        this.speed = speed;
        this.type = type;
        this.directions = directions;
        this.damage = damage;
        this.moneyBag = moneyBag;
    } 
    
    /*
     * Computes the horizontal vector.
     * Returns the horizontal vector depending on an enemy's direciton.
     */
    private int horizontalVector(Direction dir) {
        return ((dir == Direction.RIGHT) ? 1 : 0) - ((dir == Direction.LEFT) ? 1 : 0);
    }

    /*
     * Computes the vertical vector.
     * Returns the vertical vector depending on an enemy's direciton.
     */
    private int verticalVector(Direction dir) {
        return ((dir == Direction.DOWN) ? 1 : 0) - ((dir == Direction.UP) ? 1 : 0);
    }

    /*
     * Computes the closest tile centerpoint x- or y-corodinate.
     * h must be -1, 0 or 1. Throws IllegalArgumentException if not.
     * Else return the closest coordinate depending on the enemy's position
     * and current vector.
     */
    private double tileCenterPoint(double coordinate, double vector) {
        if (vector != -1 && vector != 0 && vector != 1) {
            throw new IllegalArgumentException("The vector can only be -1, 0, or 1");
        }

        if (vector != 0) {
            double number;
            double offset = vector / 2;
    
            if (coordinate == Math.floor(coordinate) + this.tileOffset) {
                number = Math.round(coordinate + offset);
            } else {
                number = Math.round(coordinate);
            }
    
            return number + offset;
        }
    
        double number = Math.floor(coordinate);
        return number + 0.5;
    }
    
    /*
     * Return the closest tile centerpoint x-coordinate.
     */
    private double tileCenterPointX(double h) {
        return tileCenterPoint(this.x, h);
    }
    
    /*
     * Return the closest tile centerpoint y-coordinate.
     */
    private double tileCenterPointY(double v) {
        return tileCenterPoint(this.y, v);
    }

    /*
     * Computes if the enemy's next position is passed or on a tile centerpoint.
     * Returns true if the position is passed or on a centerpoint, else false.
     */
    private boolean nextPosIsPassedCenterPoint(double nextX, double nextY, Direction currentDir, double h, double v) {
        if ((nextX >= tileCenterPointX(h) && currentDir == Direction.RIGHT) || (nextX <= tileCenterPointX(h) && currentDir == Direction.LEFT)) {
            return true;
        }
        if ((nextY <= tileCenterPointY(v) && currentDir == Direction.UP) || (nextY >= tileCenterPointY(v) && currentDir == Direction.DOWN)) {
            return true;
        }
        return false;
    }

    /*
     * Computes if the enemy's current position is passed or on a tile centerpoint.
     * Returns true if the position is passed or on a centerpoint, else false.
     */
    private boolean currentPosIsPassedCenterPoint(double centerPointX, double centerPointY, double h, double v) {
        return ((Math.signum(this.y - centerPointY) == v) || (this.y == centerPointY)) && (Math.signum(this.x - centerPointX) == h || (this.x == centerPointX));
    }

    /*
     * Calculate the next enemy position coordinates in one axis and updates them.
     */
    private void oneAxisMove(double h, double v) {
        if (h!= 0 && v!= 0) {
            throw new IllegalArgumentException("The enemy can only walk in one direction");
        }
        this.x += h * speed;
        this.y += v * speed;
    }

    /*
     * Updates the enemy's coordinates to the closest tile centerpoint coordinates.
     */
    private void forceEnemyToCenterPoint(double centerPointX, double centerPointY) {
        this.x = centerPointX;
        this.y = centerPointY;
    }

    /*
     * If there is only one direction for the enemy left to move, move it
     * in that direction until it has reached its closest tile centerpoint.
     */
    private void finalDirectionMove(double h, double v) {
        if (directions.size() == 1) {
            double centerPointX = tileCenterPointX(h);
            double centerPointY = tileCenterPointY(v);
            oneAxisMove(h, v);
            if (currentPosIsPassedCenterPoint(centerPointX, centerPointY, h, v)) {
                forceEnemyToCenterPoint(centerPointX, centerPointY);
                directions.remove(0);
            }
        }
    }

    /*
     * Calculate the next enemy position coordinates after a turn and updates them.
     */
    private void turningMove(double h, double v, double hNext, double vNext, double nextX, double nextY) {
        double nextXPos = tileCenterPointX(h) + Math.abs((nextY) - tileCenterPointY(v)) * hNext;
        double nextYPos = tileCenterPointY(v) + Math.abs((nextX) - tileCenterPointX(h)) * vNext;
        this.x = nextXPos;
        this.y = nextYPos;
    }

    /**
     * Moves the enemy along a predefined path of directions, adjusting its position
     * based on the current and next direction. Handles both continuous movement
     * in the same direction and turning at designated points.
     */
    private void moveEnemy() {
        Direction currentDir = directions.get(0);

        double h = horizontalVector(currentDir);
        double v = verticalVector(currentDir);
        
        finalDirectionMove(h, v);
        
        if (directions.size() > 1) {
            Direction nextDir = directions.get(1);

            double hNext = horizontalVector(nextDir);
            double vNext = verticalVector(nextDir);
            
            double nextX = this.x + h * speed;
            double nextY = this.y + v * speed;

            if (nextPosIsPassedCenterPoint(nextX, nextY, currentDir, h, v)) {
                if (currentDir == nextDir) {
                    oneAxisMove(h, v);
                }
                else {
                    turningMove(h, v, hNext, vNext, nextX, nextY);
                }
                directions.remove(0);
            }
            else {
                oneAxisMove(h, v);
            }
        }
    }

    /*
     * Move an enemy if the direction list isn't empty.
     */
    @Override
    public void move() {
        if (directions.size() > 0) {
            moveEnemy();
        }
    }
       
    // -------- Getters and setters ---------

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public int getDamage() {
        return this.damage;
    }
    
    /*
     * Updates the enemy health depending on the damage it takes
     */
    public void takeDamage(int damage){
        this.health -= damage;
    }

    public int getHealth(){
        return this.health;
    }

    public int getMoney(){
        return this.moneyBag;
    }

    public int getDirectionsSize(){
        return directions.size();
    }
}