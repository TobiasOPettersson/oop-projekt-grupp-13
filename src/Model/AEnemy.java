package src.Model;

public abstract class AEnemy implements IMovable {
    private int health; //health points
    private double x, y; //position
    private double speed; //movement speed of an enemy
    //private Rectangle hitBox; //Enemy hitbox
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
        //this.hitBox = new Rectangle((int) x + width/2, (int) y + height/2, width, height);
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
     * The four methods below updates the enemys coordinates depending on the last direction,
     * its next coordinate and where the next tile centerpoint is
     */
    private void switchingToMovingUp() {
            switch (this.lastDirection) {
                case RIGHT:
                    this.y -= (nextXCoordinate() - this.tileCenterPointX);
                    this.x = this.tileCenterPointX;
                    break;
                case LEFT:
                    this.y -= (this.tileCenterPointX - nextXCoordinate());
                    this.x = this.tileCenterPointX;
                    break;
                default:
                    this.x = this.tileCenterPointX;
                    this.y = this.tileCenterPointY;
                    break;
            }
        }

    private void switchingToMovingDown()  {
        switch (this.lastDirection) {
            case RIGHT:
                this.y += (nextXCoordinate() - this.tileCenterPointX);
                this.x = this.tileCenterPointX;
                break;
            case LEFT:
                this.y += (this.tileCenterPointX - nextXCoordinate());
                this.x = this.tileCenterPointX;
                break;
            default:
                this.x = this.tileCenterPointX;
                this.y = this.tileCenterPointY;
                break;
        }
    }

    private void switchingToMovingRight() {
        switch (this.lastDirection) {
            case DOWN:
                this.x += (nextYCoordinate() - this.tileCenterPointY);
                this.y = this.tileCenterPointY;
                break;
            case UP:
                this.x += (this.tileCenterPointY - nextYCoordinate());
                this.y = this.tileCenterPointY;
                break;
            default:
                this.x = this.tileCenterPointX;
                this.y = this.tileCenterPointY;
                break;
        }
    }

    private void switchingToMovingLeft() {
        switch (this.lastDirection) {
            case DOWN:
                this.x -= (nextYCoordinate() - this.tileCenterPointY);
                this.y = this.tileCenterPointY;
                break;
            case UP:
                this.x -= (this.tileCenterPointY - nextYCoordinate());
                this.y = this.tileCenterPointY;
                break;
            default:
                this.x = this.tileCenterPointX;
                this.y = this.tileCenterPointY;
                break;
        }
    }

    /*
     * Move an enemy depending on its last direction and next direction
     */
    public void move() {
        if (movesPastTileCenterPoint()) {
            if (this.lastDirection == this.nextDirection) {
               /*  int horizontal = ((this.lastDirection == EnemyDirection.RIGHT) ? 1 : 0) - ((this.lastDirection == EnemyDirection.LEFT) ? 1 : 0);
                int vertical = ((this.lastDirection == EnemyDirection.DOWN) ? 1 : 0) - ((this.lastDirection == EnemyDirection.UP) ? 1 : 0);
                this.x += horizontal * speed;
                this.y += vertical * speed; */

                switch (this.lastDirection) {
                    case RIGHT:
                        this.x += this.speed;
                        break;
                    case LEFT:
                        this.x -= this.speed;
                        break;
                    case UP:
                        this.y -= this.speed;
                        break;
                    case DOWN:
                        this.y += this.speed;
                        break; 
                    }
            }

            else if (this.lastDirection != this.nextDirection) {
                switch (this.nextDirection){
                    case RIGHT:
                        switchingToMovingRight();
                        break;
                    case LEFT:
                        switchingToMovingLeft();
                        break;
                    case UP:
                        switchingToMovingUp();
                        break;
                    case DOWN:
                        switchingToMovingDown();
                        break;
                }
            }
        }
        
        else {
            switch (this.lastDirection) {
                case RIGHT:
                    this.x += this.speed;
                    break;
                case LEFT:
                    this.x -= this.speed;
                    break;
                case UP:
                    this.y -= this.speed;
                    break;
                case DOWN:
                    this.y += this.speed;
                    break; 
                }
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