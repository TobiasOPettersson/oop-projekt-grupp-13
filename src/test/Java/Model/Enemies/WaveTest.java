package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Model.Enums.Direction;
import Model.Enums.EnemyType;
import Model.Map.AMap;
import Model.Map.MapOne;
import Model.Enemies.EnemyOne;
import Model.Enemies.Wave;
import Model.Enemies.AEnemy;;

public class WaveTest {

    @Test
    public void check_if_queue_has_right_values() {
        AMap map = new MapOne();
        Wave wave = new Wave();
        Queue<EnemyType> enemies = wave.startWave();
        assertEquals(EnemyType.banana, enemies.peek());
        enemies.clear();
        enemies = wave.startWave();
        for(int i = 0 ; i < 10 ; i++) assertEquals(EnemyType.banana, enemies.poll());
        assertEquals(EnemyType.tomato, enemies.poll());
        assertEquals(EnemyType.tomato, enemies.poll());
    }

    @Test
    public void check_if_queue_becomes_empty() {
        AMap map = new MapOne();
        Wave wave = new Wave();
        Queue<EnemyType> enemies = wave.startWave();
        for(int i = 0 ; i < 5 ; i++) assertEquals(EnemyType.banana, enemies.poll());
        assertEquals(true, enemies.isEmpty());
        enemies = wave.startWave();
        for(int i = 0 ; i < 10 ; i++) assertEquals(EnemyType.banana, enemies.poll());
        assertEquals(EnemyType.tomato, enemies.poll());
        assertEquals(EnemyType.tomato, enemies.poll());
        assertEquals(true, wave.wavesIsEmpty());
        enemies = wave.startWave();
        enemies = wave.startWave();
        enemies = wave.startWave();
        enemies = wave.startWave();
        enemies = wave.startWave();
        enemies = wave.startWave();
        enemies = wave.startWave();
    }
}
