package src.Model;
import java.awt.Rectangle;
import java.awt.Image;

public abstract class AEnemy implements IMovable {
    private int health; //The health points of an enemy
    private double x, y; //The enemys position, maybe have this as a point?
    private double speed; //The speed in an enemy
    private Rectangle hitBox; //Enemy hitbox so that when it gets in to the towers targeting range, it can get shot
    private Image model; //The sprite
    private String type;
    private EnemyDirection direction = EnemyDirection.RIGHT;

    public AEnemy(int health, double x, double y, double speed, int width, int height, Image model, String type) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.hitBox = new Rectangle((int) x + width/2, (int) y + height/2, width, height); //FIX MAGIC NUMBERS WITH CONSTANTS?
        this.model = model;
        this.type = type;
    } //Constructor

    public boolean isInTileCenter(double x, double y) {
        if (x == this.x && y == this.y) {
            return true;
        }
        return false;
    } //See if enemy is in center given the arguments


    public void move() {
        System.out.println("Moving in direction: " + this.direction);
        switch (this.direction) {
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

    public void setDirection(EnemyDirection newDir) {
        this.direction = newDir;
    }

    public int getHealth(){
        return this.health;
    }
    
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getWidth() {
        return this.hitBox.width;
    }

    public int getHeight() {
        return this.hitBox.height;
    }
}