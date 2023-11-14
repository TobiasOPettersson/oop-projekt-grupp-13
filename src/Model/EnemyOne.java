package src.Model;
public class EnemyOne extends AEnemy{
    
    public EnemyOne(double x, double y, double speed) {
        super(1, x, y, speed, "normal");
    }

    @Override
    public String toString() {
        return "EnemyOne with health: " + getHealth() + ", " + "position: (" + getX() + "," + getY() + ")" + " speed: " + getSpeed();
    }
}
