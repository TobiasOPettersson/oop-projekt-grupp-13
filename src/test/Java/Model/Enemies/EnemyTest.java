package Model.Enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import Model.Enums.Direction;

public class EnemyTest {

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
        AEnemy enemy = new TomatoEnemy(2, directions);
        while (enemy.getX() < 4.5) {
            enemy.move();
        }
        assertEquals(4.5, enemy.getX());
        assertEquals(2.5, enemy.getY()); //2.5
    }

    @Test
    public void enemy_should_be_able_to_walk_right_and_then_turn_up() {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        /*
         * 0  0  5  0  0
         * 0  0  4  0  0
         * 1  2  3  0  0
         * 0  0  0  0  0
         * 0  0  0  0  0
         */
        for (int i = 0; i < 3; i++) {
            directions.add(Direction.RIGHT);
        }
        for (int i = 0; i < 2; i++) {
            directions.add(Direction.UP);
        }
        AEnemy enemy = new TomatoEnemy(2, directions);
        while (enemy.getY() > 0.5) {
            enemy.move();
        }
        assertEquals(2.5, enemy.getX());
        assertEquals(0.5, enemy.getY());
    }

    @Test
    public void enemy_should_be_able_to_walk_right_and_then_turn_down() {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        /*
         * 0  0  0  0  0
         * 0  0  0  0  0
         * 1  2  3  0  0
         * 0  0  4  0  0
         * 0  0  5  0  0
         */
        for (int i = 0; i < 3; i++) {
            directions.add(Direction.RIGHT);
        }
        for (int i = 0; i < 2; i++) {
            directions.add(Direction.DOWN);
        }
        AEnemy enemy = new TomatoEnemy(2, directions);
        while (enemy.getY() < 4.5) {
            enemy.move();
        }
        assertEquals(2.5, enemy.getX());
        assertEquals(4.5, enemy.getY());
    }
}
