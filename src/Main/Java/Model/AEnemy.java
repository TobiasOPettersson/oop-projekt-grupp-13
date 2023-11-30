package Model;

import java.util.List;

public abstract class AEnemy implements IMovable {
    private int health; //health points
    private double x, y; //position
    private double speed; //movement speed of an enemy
    private EnemyType type; //The type of enemy
    private List<Direction> directions; //List of directions for the enemy to follow when moving
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
    } 
    
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
     * If an enemy will move past a tile centerpoint - return true. Else return false.
     */
    private boolean nextPosPastCenterPoint(double nextX, double nextY, Direction currentDir, double h, double v) {
        boolean passesTileCenterPoint = false;
        if ((nextX >= tileCenterPointX(h) && currentDir == Direction.RIGHT) || (nextX <= tileCenterPointX(h) && currentDir == Direction.LEFT)) {
            passesTileCenterPoint = true;
        }
        if ((nextY <= tileCenterPointY(v) && currentDir == Direction.UP) || (nextY >= tileCenterPointY(v) && currentDir == Direction.DOWN)) {
            passesTileCenterPoint = true;
        }
        return passesTileCenterPoint;
    }

    private boolean currentPosPastCenterPoint(double centerPointX, double centerPointY, double h, double v) {
        return ((Math.signum(this.y - centerPointY) == v) || (this.y == centerPointY)) && (Math.signum(this.x - centerPointX) == h || (this.x == centerPointX));
    }

    private int horizontalVectorMultiplier(Direction dir) {
        return ((dir == Direction.RIGHT) ? 1 : 0) - ((dir == Direction.LEFT) ? 1 : 0);
    }

    private int verticalVectorMultiplier(Direction dir) {
        return ((dir == Direction.DOWN) ? 1 : 0) - ((dir == Direction.UP) ? 1 : 0);
    }

    /*
     * Move an enemy depending on its last direction and next direction
     */
    public void move() {
        if (directions.size() > 0) {
            moveEnemy();
        }
        else {
            System.out.println("Direction array is empty");
        }
    }

    private void moveEnemy() {
        Direction currentDir = directions.get(0);
        double h = horizontalVectorMultiplier(currentDir);
        double v = verticalVectorMultiplier(currentDir);
        
        finalDirectionMove(h, v);
        
        if (directions.size() > 1) {
            Direction nextDir = directions.get(1);

            double hNext = horizontalVectorMultiplier(nextDir);
            double vNext = verticalVectorMultiplier(nextDir);
            
            double nextX = getNextX(h);
            double nextY = getNextY(v);

            if (nextPosPastCenterPoint(nextX, nextY, currentDir, h, v)) {
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

    private void turningMove(double h, double v, double hNext, double vNext, double nextX, double nextY) {
        double nextXPos = tileCenterPointX(h) + Math.abs((nextY) - tileCenterPointY(v)) * hNext;
        double nextYPos = tileCenterPointY(v) + Math.abs((nextX) - tileCenterPointX(h)) * vNext;
        this.x = nextXPos;
        this.y = nextYPos;
    }

    private double getNextY(double v) {
        return this.y + v * speed;
    }

    private double getNextX(double h) {
        return this.x + h * speed;
    }

    private void oneAxisMove(double h, double v) {
        this.x += h * speed;
        this.y += v * speed;
    }

    private void finalDirectionMove(double h, double v) {
        if (directions.size() == 1) {
            double centerPointX = tileCenterPointX(h);
            double centerPointY = tileCenterPointY(v);
            oneAxisMove(h, v);
            if (currentPosPastCenterPoint(centerPointX, centerPointY, h, v)) {
                forceEnemyToCenterPoint(centerPointX, centerPointY);
                directions.remove(0);
            }
        }
    }

    private void forceEnemyToCenterPoint(double centerPointX, double centerPointY) {
        this.x = centerPointX;
        this.y = centerPointY;
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

    public void doDamage(int damage){
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