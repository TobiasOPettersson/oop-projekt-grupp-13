package src.Model;
import java.awt.Rectangle;
import java.awt.Image;

public abstract class AEnemy implements IMovable {
    private int health; //The health points of an enemy
    private int x, y; //The enemys position, maybe have this as a point?
    private double speedX, speedY; //The speed in x- and y-direction for an enemy
    private Rectangle hitBox; //Enemy hitbox so that when it gets in to the towers targeting range, it can get shot
    private Image model;
    private String type;
    
    public AEnemy(int health, int x, int y, double speedX, double speedY, int width, int height, Image model, String type) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.hitBox = new Rectangle(x, y, width, height);
        this.model = model;
        this.type = type;
    }

    public void move(double speedX, double speedY) {
        this.x += speedX;
        this.y += speedY;
    }
    
    public int getHealth(){
        return this.health;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getSpeedX() {
        return this.speedX;
    }

    public double getSpeedY() {
        return this.speedY;
    }

    public int getWidth() {
        return this.hitBox.width;
    }

    public int getHeight() {
        return this.hitBox.height;
    }
}