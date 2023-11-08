package Model;

public abstract class AEnemy {
    private int health; //The health points of an enemy
    private double posX, posY; //The enemys position
    private double speedX, speedY; //The speed in x- and y-direction for an enemy

    public AEnemy(int health, double posX, double posY, double speedX, double speedY) {
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void move(double x, double y) {
        this.posX += x;
        this.posY += y;
    }
    public int getHealth(){
        return this.health;
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public double getSpeedX() {
        return this.speedX;
    }

    public double getSpeedY() {
        return this.speedY;
    }
}