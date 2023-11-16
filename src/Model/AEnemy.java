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
     * Returning the next x-coordinate depending on the direction and speed
     */
    private double nextXCoordinate() {
        switch (this.lastDirection) {
            case RIGHT:
                return this.x + this.speed;
            case LEFT:
                return this.x - this.speed;
            default:
                return this.x;
        }
    }

    /*
     * Returning the next y-coordinate depending on the direction and speed
     */
    private double nextYCoordinate() {
        switch (this.lastDirection) {
            case UP:
                return this.y - this.speed;
            case DOWN:
                return this.y + this.speed;
            default:
                return this.y;
        }
    }

    /*
     * If an enemy moves past a tile centerpoint return true. Else return false.
     */
    private boolean movesPastTileCenterPoint() {
        boolean passesTileCenterPoint = false;
        if ((nextXCoordinate() >= tileCenterPointX && this.lastDirection == EnemyDirection.RIGHT) || (nextXCoordinate() <= tileCenterPointX && this.lastDirection == EnemyDirection.LEFT)) {
            passesTileCenterPoint = true;
        }
        if ((nextYCoordinate() <= tileCenterPointY && this.lastDirection == EnemyDirection.UP) || (nextYCoordinate() >= tileCenterPointY && this.lastDirection == EnemyDirection.DOWN)) {
            passesTileCenterPoint = true;
        }
        return passesTileCenterPoint;
    }

    /*
     * Move an enemy depending on its last direction and next direction
     */
    public void move() {
        int horizontal = ((this.lastDirection == EnemyDirection.RIGHT) ? 1 : 0) - ((this.lastDirection == EnemyDirection.LEFT) ? 1 : 0);
        int vertical = ((this.lastDirection == EnemyDirection.DOWN) ? 1 : 0) - ((this.lastDirection == EnemyDirection.UP) ? 1 : 0);
        int horizontalNext = ((this.nextDirection == EnemyDirection.RIGHT) ? 1 : 0) - ((this.nextDirection == EnemyDirection.LEFT) ? 1 : 0);
        int verticalNext = ((this.nextDirection == EnemyDirection.DOWN) ? 1 : 0) - ((this.nextDirection == EnemyDirection.UP) ? 1 : 0);

        if (!movesPastTileCenterPoint() || this.lastDirection == this.nextDirection) {
                this.x += horizontal * speed;
                this.y += vertical * speed;
            }
        else {
            this.y = this.tileCenterPointY + Math.abs(this.x + horizontal * speed - this.tileCenterPointX) * verticalNext;
            this.x = this.tileCenterPointX + Math.abs(this.y + vertical * speed - this.tileCenterPointY) * horizontalNext;
            //Remove first element from direction list
        }
    }

    // -------- Getters and setters ---------
    public void setTileCenterPointY(double y) {
        this.tileCenterPointY = y;
    }

    public double getTileCenterPointY() {
        return this.tileCenterPointY;
    }

    public void setTileCenterPointX(double x) {
        this.tileCenterPointX = x;
    }

    public double getTileCenterPointX() {
        return this.tileCenterPointX;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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