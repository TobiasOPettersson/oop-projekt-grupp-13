package src.Model;
import java.awt.Image;
public class EnemyOne extends AEnemy{
    
    public EnemyOne(double x, double y, double speed, int width, int height, Image model) {
        super(1, x, y, speed, width, height, model, "normal");
    }

    @Override
    public String toString() {
        return "EnemyOne with health: " + getHealth() + ", " + "position: (" + getX() + "," + getY() + ")" + " speed: " + getSpeed();
    }
}
