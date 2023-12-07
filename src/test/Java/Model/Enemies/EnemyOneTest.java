package Model.Enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import Model.Enums.Direction;

public class EnemyOneTest {

    @Test
    public void enemy_should_be_able_to_walk_in_a_straight_line() {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        /*
         * 0  0  0  0  0
         * 0  0  0  0  0
         * 1  2  3  4  5
         * 0  0  0  0  0
         * 0  0  0  0  0
         */
        for (int i = 0; i < 5; i++) {
            directions.add(Direction.RIGHT);
        }
        AEnemy enemy = new EnemyOne(3, 0.3, directions);
        while (enemy.getX() < 4.5) {
            enemy.move();
        }
        assertEquals(4.5, enemy.getX());
        assertEquals(3.5, enemy.getY());
    }
}
