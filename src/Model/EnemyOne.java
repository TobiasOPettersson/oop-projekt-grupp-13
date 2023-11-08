package src.Model;

public class EnemyOne extends AEnemy{
    
    public EnemyOne(int health, int x, int y, double speedX, double speedY, int width, int height) {
        super(health, x, y, speedX, speedY, width, height);
    }

    @Override
    public String toString() {
        return "EnemyOne with health: " + getHealth() + ", " + "position: (" + getX() + "," + getY() + ")" + " speed: (" + getSpeedX() + "," + getSpeedY() + ")";
    }
}
