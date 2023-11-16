package src.Model;

public abstract class AEnemy implements IMovable {
    private int health; //health points
    private double x, y; //position
    private double speed; //movement speed of an enemy
    private String type; //The type of enemy
    private EnemyDirection lastDirection = EnemyDirection.RIGHT; //last direction, starting with walking right
    private EnemyDirection nextDirection = EnemyDirection.RIGHT; //next direction, starting with walking right
    private double tileCenterPointX; //Need to have information about the next tile centerpoint so that we can move the enemy accordingly
    private double tileCenterPointY;

    public AEnemy(int health, double x, double y, double speed, String type) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
    } //Constructor

    /*
     * If an enemy moves past a tile centerpoint return true. Else return false.
     */
    private boolean movesPastTileCenterPoint(double nextX, double nextY) {
        boolean passesTileCenterPoint = false;
        if ((nextX >= tileCenterPointX && this.lastDirection == EnemyDirection.RIGHT) || (nextX <= tileCenterPointX && this.lastDirection == EnemyDirection.LEFT)) {
            passesTileCenterPoint = true;
        }
        if ((nextY <= tileCenterPointY && this.lastDirection == EnemyDirection.UP) || (nextY >= tileCenterPointY && this.lastDirection == EnemyDirection.DOWN)) {
            passesTileCenterPoint = true;
        }
        return passesTileCenterPoint;
    }

    /*
     * Move an enemy depending on its last direction and next direction
     */
    public void move() {
        int h = ((this.lastDirection == EnemyDirection.RIGHT) ? 1 : 0) - ((this.lastDirection == EnemyDirection.LEFT) ? 1 : 0);
        int v = ((this.lastDirection == EnemyDirection.DOWN) ? 1 : 0) - ((this.lastDirection == EnemyDirection.UP) ? 1 : 0);
        int hNext = ((this.nextDirection == EnemyDirection.RIGHT) ? 1 : 0) - ((this.nextDirection == EnemyDirection.LEFT) ? 1 : 0);
        int vNext = ((this.nextDirection == EnemyDirection.DOWN) ? 1 : 0) - ((this.nextDirection == EnemyDirection.UP) ? 1 : 0);
        double nextX = this.x + h * speed;
        double nextY = this.y + v * speed;

        if (!movesPastTileCenterPoint(nextX, nextY) || this.lastDirection == this.nextDirection) {
                this.x += h * speed;
                this.y += v * speed;
            }
        else {
            this.y = this.tileCenterPointY + Math.abs((nextX) - this.tileCenterPointX) * vNext;
            this.x = this.tileCenterPointX + Math.abs((nextY) - this.tileCenterPointY) * hNext;
            //Remove first element from direction list
        }
    }

    // -------- Getters and setters ---------
    public void setTileCenterPointY(double y) {
        this.tileCenterPointY = y;
    }

    public void setTileCenterPointX(double x) {
        this.tileCenterPointX = x;
    }

    public double getTileCenterPointX() {
        return this.tileCenterPointX;
    }

    public double getTileCenterPointY() {
        return this.tileCenterPointY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public EnemyDirection getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(EnemyDirection lastDirection) {
        this.lastDirection = lastDirection;
    }

    public EnemyDirection getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(EnemyDirection nextDirection) {
        this.nextDirection = nextDirection;
    }
}