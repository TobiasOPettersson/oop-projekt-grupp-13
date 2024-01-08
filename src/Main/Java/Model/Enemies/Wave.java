package Model.Enemies;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A class representing waves with enemies.
 */
public class Wave {
    private int currentWave;
    private int maxNumberOfWaves;
    private Queue<Queue<EnemyType>> waves = new LinkedList<Queue<EnemyType>>();
    private int spawnRate;
    private final int MAX_SPAWN_RATE = 30;
    private EnemyFactory waveFactory;
    
    /**
     * Constructor of the Wave class.
     * @param startPosition is the start y position in the tile grid for the waves.
     * @param pathDirections is the list of directions which the waves should follow.
     */
    public Wave(int startPosition, List<Direction> pathDirections){
        currentWave = 0;
        createWaves();
        spawnRate = MAX_SPAWN_RATE;
        waveFactory = new EnemyFactory(startPosition, pathDirections);
    }
    
    /**
     * Adds all the waves to the wave queue.
     */
    private void createWaves(){
        Queue<EnemyType> createCurrentWave = new LinkedList<EnemyType>();

        //Waves 1
        createCurrentWave.addAll(createPartWave(1, EnemyType.banana));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 2
        createCurrentWave.addAll(createPartWave(1, EnemyType.tomato));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 3
        createCurrentWave.addAll(createPartWave(1, EnemyType.cheese));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 4
        createCurrentWave.addAll(createPartWave(1, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        /* -------------------------- Template for each wave --------------------------
        //Wave #
        createCurrentWave.addAll(createPartWave(1, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(1, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(1, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(1, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();
         */
        maxNumberOfWaves = waves.size();
    }

    /**
     * Creates a part of a wave with a certain number of enemies of a certain type.
     * @param numberOfEnemies the number of enemies in this part of the wave
     * @param enemyType the type of enemy which the part should contain
     * @return a queue of enemy types
     */
    private Queue<EnemyType> createPartWave(int numberOfEnemies, EnemyType enemyType){
        Queue<EnemyType> currentEnemyType = new LinkedList<EnemyType>();
        for(int i = 0 ; i < numberOfEnemies ; i++){
            currentEnemyType.add(enemyType);
        }
        return currentEnemyType;
    }

    /**
     * @return boolean for if the waves queue is empty or not
     */
    public boolean wavesIsEmpty(){
        return waves.isEmpty();
    }

    /**
     * decrements spawn rate if it's above 0
     */
    public void updateSpawnRate(){
        if (spawnRate > 0) spawnRate--;
    }

    /**
     * @return boolean for if a new enemy can be spawned
     */
    public boolean checkIfSpawnable(){
        if (spawnRate > 0) return false;
        spawnRate = MAX_SPAWN_RATE;
        return true;
    }

    //---------------- GETTERS AND SETTERS -----------------//

    public Queue<AEnemy> getNextWave() throws Exception {
        if (!waves.isEmpty()){
            Queue<AEnemy> nextWave = waveFactory.createCurrentWave(waves.poll());
            currentWave++;
            return nextWave;
        }
        else {
            throw new Exception(" You called waves.poll() on an empty queue. Returns null.");
        }
    }

    public int getCurrentWaveNumber(){
        return currentWave;
    }

    public int getMaxNumberOfWaves() {
        return maxNumberOfWaves;
    }

    public int getSpawnRate(){
        return spawnRate;
    }

    public int getMaxSpawnRate(){
        return MAX_SPAWN_RATE;
    }
}