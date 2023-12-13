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
    private final int MAX_SPAWN_RATE = 120;
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
     * Starts a new wave of enemies.
     * @return The next wave in the queue.
     * @throws Exception if the queue is empty.
     */
    public Queue<EnemyType> startWave() throws Exception{
        currentWave++;
        if (waves.isEmpty()) throw new Exception("Wave is empty");
        return waves.poll();
    }
    
    /**
     * Adds all the waves to the wave queue.
     */
    private void createWaves(){
        Queue<EnemyType> createCurrentWave = new LinkedList<EnemyType>();
        
        //Waves 1
        createCurrentWave.addAll(createPartWave(5, EnemyType.banana));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 2
        createCurrentWave.addAll(createPartWave(10, EnemyType.banana));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 3
        createCurrentWave.addAll(createPartWave(15, EnemyType.banana));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 4
        createCurrentWave.addAll(createPartWave(20, EnemyType.banana));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 5
        createCurrentWave.addAll(createPartWave(15, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(5, EnemyType.tomato));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 6
        createCurrentWave.addAll(createPartWave(20, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(7, EnemyType.tomato));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 7
        createCurrentWave.addAll(createPartWave(25, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(10, EnemyType.tomato));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 8
        createCurrentWave.addAll(createPartWave(30, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(15, EnemyType.tomato));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 9
        createCurrentWave.addAll(createPartWave(30, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(20, EnemyType.tomato));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 10
        createCurrentWave.addAll(createPartWave(25, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(20, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(5, EnemyType.cheese));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 11
        createCurrentWave.addAll(createPartWave(20, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(20, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(10, EnemyType.cheese));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 12
        createCurrentWave.addAll(createPartWave(15, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(20, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(15, EnemyType.cheese));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 13
        createCurrentWave.addAll(createPartWave(15, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(15, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(20, EnemyType.cheese));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 14
        createCurrentWave.addAll(createPartWave(10, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(15, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(25, EnemyType.cheese));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 15
        createCurrentWave.addAll(createPartWave(15, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(15, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(15, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(5, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 16
        createCurrentWave.addAll(createPartWave(15, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(15, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(10, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(10, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 17
        createCurrentWave.addAll(createPartWave(15, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(10, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(15, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(10, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 18
        createCurrentWave.addAll(createPartWave(10, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(15, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(15, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(10, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 19
        createCurrentWave.addAll(createPartWave(10, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(15, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(10, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(15, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 20
        createCurrentWave.addAll(createPartWave(10, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(10, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(10, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(20, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave #21
        createCurrentWave.addAll(createPartWave(5, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(10, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(10, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(25, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 22
        createCurrentWave.addAll(createPartWave(10, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(10, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(30, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 23
        createCurrentWave.addAll(createPartWave(5, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(15, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(30, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 24
        createCurrentWave.addAll(createPartWave(15, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(35, EnemyType.chicken));
        waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

        //Wave 25
        createCurrentWave.addAll(createPartWave(10, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(40, EnemyType.chicken));
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

    public Queue<AEnemy> getNextWave() {
        if (!waves.isEmpty()){
            Queue<AEnemy> nextWave = waveFactory.createCurrentWave(waves.poll());
            currentWave++;
            return nextWave;
        }
        else {
            throw new NullPointerException(" You can't call waves.poll() on an empty queue. Returns null.");
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