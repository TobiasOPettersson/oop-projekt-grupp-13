package src.Model;

public class EnemyOne extends AEnemy{
    
    public EnemyOne(int health, int posX, int posY, double speedX, double speedY, int width, int height) {
        super(health, posX, posY, speedX, speedY, width, height);
    }

    @Override
    public String toString() {
        return "EnemyOne with health: " + getHealth() + ", " + "position: (" + getPosX() + "," + getPosY() + ")" + " speed: (" + getSpeedX() + "," + getSpeedY() + ")";
    }
}
