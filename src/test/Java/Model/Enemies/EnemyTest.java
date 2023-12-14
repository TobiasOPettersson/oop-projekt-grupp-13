package Model.Enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

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
        assertEquals(2.5, enemy.getY());
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

    @Test
    void banana_subtype_is_enum_type_banana() {
        List<Direction> directions = Arrays.asList(Direction.DOWN, Direction.UP, Direction.RIGHT);

        AEnemy enemy = new BananaEnemy(3, directions);

        assertEquals(EnemyType.banana, enemy.getEnemyType());
    }

    @Test
    void tomato_subtype_is_enum_type_tomato() {
        List<Direction> directions = Arrays.asList(Direction.DOWN, Direction.UP, Direction.RIGHT);

        AEnemy enemy = new TomatoEnemy(3, directions);

        assertEquals(EnemyType.tomato, enemy.getEnemyType());
    }

    @Test
    void chicken_subtype_is_enum_type_chicken() {
        List<Direction> directions = Arrays.asList(Direction.DOWN, Direction.UP, Direction.RIGHT);

        AEnemy enemy = new ChickenEnemy(3, directions);

        assertEquals(EnemyType.chicken, enemy.getEnemyType());
    }

    @Test
    void cheese_subtype_is_enum_type_chicken() {
        List<Direction> directions = Arrays.asList(Direction.DOWN, Direction.UP, Direction.RIGHT);

        AEnemy enemy = new CheeseEnemy(3, directions);

        assertEquals(EnemyType.cheese, enemy.getEnemyType());
    }

    @Test
    void empty_direction_list_argument_in_constructor_should_make_the_enemy_have_an_empty_direction_list() {
        List<Direction> directions = Arrays.asList();

        AEnemy enemy = new BananaEnemy(3, directions);

        assertEquals(0, enemy.getDirectionsSize());
    }

    @Test
    void doing_more_damage_than_an_enemys_hp_will_result_in_that_the_enemys_hp_goes_to_zero() {
        AEnemy enemy = new BananaEnemy(3, Arrays.asList(Direction.DOWN, Direction.UP, Direction.RIGHT));

       int damage = 1000;

        enemy.takeDamage(damage);

        assertEquals(0, enemy.getHealth());
    }

    @Test
    void doing_negative_damage_to_an_enemy_should_result_to_an_error() {
        AEnemy enemy = new BananaEnemy(3, Arrays.asList(Direction.DOWN, Direction.UP, Direction.RIGHT));

        int damage = -1;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> enemy.takeDamage(damage));
        assertEquals("Damage can't be negative!", exception.getMessage());
    }
}
