package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Interfaces.IMovable;

public abstract class AEnemy implements IMovable {
    private int health; //health points
    private double x, y; //position
    private double speed; //movement speed of an enemy
    private EnemyType type; //The type of enemy
    private List<Direction> directions;
    private int damage;
    private int moneyBag;
    private double tileOffset = 0.5;
    
    public AEnemy(int health, double y, double speed, EnemyType type, List<Direction> directions, int damage, int moneyBank) {
        this.health = health;
        this.x = 0;
        this.y = y + tileOffset;
        this.speed = speed;
        this.type = type;
        this.directions = directions;
        this.damage = damage;
        this.moneyBag = moneyBank;
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
                number = (double) Math.round(this.y + offset);
            }
            else {
                number = (double) Math.round(this.y);
            }
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

                // If the next point which the enemies are going through is decided when they cross the midddle of a tile 
                // the setting of nextX nd Y should probably be done by movespastTileCenter
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

    public int getDamage() {
        return this.damage;
    }

    public void doDamage(int x){
        this.health -= x;
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

    public EnemyType getType(){
        return type;
    }
}