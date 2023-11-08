package src.Model;
import java.awt.Rectangle;

public abstract class AEnemy implements IMovable{
    private int health; //The health points of an enemy
    private int x, y; //The enemys position
    private double speedX, speedY; //The speed in x- and y-direction for an enemy
    private Rectangle enemyRect; //Enemy boundaries so that when it gets in to the towers targeting range, it gets shot
                                 //Maybe use width, and height instead?

    public AEnemy(int health, int x, int y, double speedX, double speedY, int width, int height) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.enemyRect = new Rectangle(x, y, width, height);
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
        return enemyRect.width;
    }

    public int getHeight() {
        return enemyRect.height;
    }
}