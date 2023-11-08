package src.Model;
import java.awt.Rectangle;

public abstract class AEnemy {
    private int health; //The health points of an enemy
    private int posX, posY; //The enemys position
    private double speedX, speedY; //The speed in x- and y-direction for an enemy
    private Rectangle enemyRect; //Enemy boundaries so that when it gets in to the towers targeting range, it gets shot
                                 //Maybe use width, and height instead?

    public AEnemy(int health, int posX, int posY, double speedX, double speedY, int width, int height) {
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.enemyRect = new Rectangle(posX, posY, width, height);
    }

    public void move(double speedX, double speedY) {
        this.posX += speedX;
        this.posY += speedY;
    }
    
    public int getHealth(){
        return this.health;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
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