package src.Model;

import java.util.List;

public abstract class AEnemy implements IMovable {
    private int health; //health points
    private double x, y; //position
    private double speed; //movement speed of an enemy
    private String type; //The type of enemy
    private List<Direction> directions;
    
    public AEnemy(int health, double x, double y, double speed, String type, List<Direction> directions) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
        this.directions = directions;
    } //Constructor
    
    private double tileCenterPointX(double h) {
        if (h!=0) {
            double number;
            double offset = (double) h/2;
            if (this.x == Math.floor(this.x) + 0.5) {
                number = (double) Math.round(this.x + offset);
            }
            else {
                number = (double) Math.round(this.x);
            }
            return number + offset;
        }
        double number = Math.floor(this.x);
        return number + 0.5;
    }

    private double tileCenterPointY(double v) {
        if (v!=0) {
            double number;
            double offset = (double) v/2;
            if (this.y == Math.floor(this.y) + 0.5) {
                System.out.println("test");
                number = (double) Math.round(this.y + offset);
            }
            else {
                number = (double) Math.round(this.y);
            }
            System.out.println("Number: " + number + "Offset: " + offset);
            return number + offset;
        }
        double number = Math.floor(this.y);
        return number + 0.5;
    }

    /*
     * If an enemy moves past a tile centerpoint return true. Else return false.
     */
    private boolean movesPastTileCenterPoint(double nextX, double nextY, Direction currentDir, double h, double v) {
        boolean passesTileCenterPoint = false;
        if ((nextX >= tileCenterPointX(h) && currentDir == Direction.RIGHT) || (nextX <= tileCenterPointX(h) && currentDir == Direction.LEFT)) {
            passesTileCenterPoint = true;
        }
        if ((nextY <= tileCenterPointY(v) && currentDir == Direction.UP) || (nextY >= tileCenterPointY(v) && currentDir == Direction.DOWN)) {
            passesTileCenterPoint = true;
        }
        return passesTileCenterPoint;
    }

    /*
     * Move an enemy depending on its last direction and next direction
     */
    public void move() {
        if (directions.size() > 0) {
            Direction currentDir = directions.get(0);
            double h = ((currentDir == Direction.RIGHT) ? 1 : 0) - ((currentDir == Direction.LEFT) ? 1 : 0);
            double v = ((currentDir == Direction.DOWN) ? 1 : 0) - ((currentDir == Direction.UP) ? 1 : 0);
            
            if (directions.size() == 1) {
                double centerPointX = tileCenterPointX(h);
                double centerPointY = tileCenterPointY(v);
                this.x += h * speed;
                this.y += v * speed;
                if ((Math.signum(this.y - centerPointY) == v || (this.y == centerPointY)) && (Math.signum((this.x - centerPointX)) == h || (this.x == centerPointX))  ) {
                    directions.remove(0);
                    this.x = centerPointX;
                    this.y = centerPointY;
                }
            }
            
            if (directions.size() > 1) {
                Direction nextDir = directions.get(1);

                double hNext = ((nextDir == Direction.RIGHT) ? 1 : 0) - ((nextDir == Direction.LEFT) ? 1 : 0);
                double vNext = ((nextDir == Direction.DOWN) ? 1 : 0) - ((nextDir == Direction.UP) ? 1 : 0);

                double nextX = this.x + h * speed;
                double nextY = this.y + v * speed;

                if (movesPastTileCenterPoint(nextX, nextY, currentDir, h, v)) {
                    if (currentDir == nextDir) {
                        this.x += h * speed;
                        this.y += v * speed;
                    }
                    else {
                        double nextYPos = tileCenterPointY(v) + Math.abs((nextX) - tileCenterPointX(h)) * vNext;
                        double nextXPos = tileCenterPointX(h) + Math.abs((nextY) - tileCenterPointY(v)) * hNext;
                        this.y = nextYPos;
                        this.x = nextXPos;
                    }
                    directions.remove(0);
                    System.out.println(directions.size());
                }
                else {
                    this.x += h * speed;
                    this.y += v * speed;
                }
            }
        }
        else {
            System.out.println("Direction array is empty");
        }
        
    }
       
    // -------- Getters and setters ---------

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}