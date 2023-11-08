package Model;

public class EnemyOne extends AEnemy{
    
    public EnemyOne(int health, double posX, double posY, double speedX, double speedY) {
        super(health, posX, posY, speedX, speedY);
    }

    @Override
    public String toString() {
        return "EnemyOne with health: " + getHealth() + ", " + "position: (" + getPosX() + "," + getPosY() + ")" + " speed: (" + getSpeedX() + "," + getSpeedY() + ")";
    }
}
