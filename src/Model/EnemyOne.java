package src.Model;
import java.awt.Image;
public class EnemyOne extends AEnemy{
    
    public EnemyOne(int x, int y, double speedX, double speedY, int width, int height, Image model) {
        super(1, x, y, speedX, speedY, width, height, model, "normal");
    }

    @Override
    public String toString() {
        return "EnemyOne with health: " + getHealth() + ", " + "position: (" + getX() + "," + getY() + ")" + " speed: (" + getSpeedX() + "," + getSpeedY() + ")";
    }
}
