package src.Model;

import java.util.List;

public class EnemyOne extends AEnemy{
    
    public EnemyOne(double y, double speed, List<Direction> directions) {
        super(1, y, speed, "normal", directions, 1, 1);
    }
}
