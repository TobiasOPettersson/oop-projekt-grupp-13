package test.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import src.Model.EnemyOne;

public class EnemyOneTest {

    @Test
    public void move_enemy_in_x_direction_will_change_its_x_position() {
        EnemyOne enemy = new EnemyOne(1, 0, 0, 1, 1, 0, 0);
        enemy.move(1, 1);
        assertEquals(enemy.getX(), 1);
    }
}
