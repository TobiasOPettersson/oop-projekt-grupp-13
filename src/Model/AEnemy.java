package src.Model;

public abstract class AEnemy implements IMovable {
    private int health; //The health points of an enemy
    private double x, y; //The enemys position, maybe have this as a point?
    private double speed; //The speed in an enemy
    //private double hitBox; //Enemy hitbox so that when it gets in to the towers targeting range, it can get shot
    private String type; //The type of enemy
    private EnemyDirection lastDirection = EnemyDirection.RIGHT; //An enemy will start on the left side
    private EnemyDirection nextDirection = EnemyDirection.RIGHT;
    private double tileCenterPointX; //TEMPORARY
    private double tileCenterPointY; //TEMPORARY

    public AEnemy(int health, double x, double y, double speed, String type) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.speed = speed;
        //this.hitBox = new Rectangle((int) x + width/2, (int) y + height/2, width, height);
        this.type = type;
    } //Constructor

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

    private void switchingToMovingUp() {
            switch (this.lastDirection) {
                case RIGHT:
                    this.x = this.tileCenterPointX;
                    this.y -= (nextXCoordinate() - this.tileCenterPointX);
                    break;
                case LEFT:
                    this.x = this.tileCenterPointX;
                    this.y -= (this.tileCenterPointX - nextXCoordinate());
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
                this.x = this.tileCenterPointX;
                this.y += (nextXCoordinate() - this.tileCenterPointX);
                break;
            case LEFT:
                this.x = this.tileCenterPointX;
                this.y += (this.tileCenterPointX - nextXCoordinate());
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

    public void move() {
        if (movesPastTileCenterPoint()) {
            if (this.lastDirection == this.nextDirection) {
                int horizontal = ((this.lastDirection == EnemyDirection.RIGHT) ? 1 : 0) - ((this.lastDirection == EnemyDirection.LEFT) ? 1 : 0);
                int vertical = ((this.lastDirection == EnemyDirection.DOWN) ? 1 : 0) - ((this.lastDirection == EnemyDirection.UP) ? 1 : 0);
                this.x += horizontal * speed;
                this.y += vertical * speed;

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