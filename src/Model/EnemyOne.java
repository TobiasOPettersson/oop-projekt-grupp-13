package src.Model;

import java.util.List;

public class EnemyOne extends AEnemy{
    
    public EnemyOne(double x, double y, double speed, List<Direction> directions) {
        super(1, x, y, speed, "normal", directions);
    }
}
