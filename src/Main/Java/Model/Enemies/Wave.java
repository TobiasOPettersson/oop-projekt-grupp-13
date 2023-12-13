package Model.Enemies;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

public class Wave {
    private int currentWave;
    private int maxNumberOfWaves;
    private Queue<Queue<EnemyType>> waves = new LinkedList<Queue<EnemyType>>();
    private int spawnRate;
    private final int MAX_SPAWN_RATE = 120;
    private EnemyFactory waveFactory;
    
    public Wave(int startPosition, List<Direction> pathDirections){
        currentWave = 0;
        createWaves();
        spawnRate = MAX_SPAWN_RATE;
        waveFactory = new EnemyFactory(startPosition, pathDirections);
    }

    public Queue<EnemyType> startWave() throws Exception{
        currentWave++;
        if (waves.isEmpty()) throw new Exception("Wave is empty");
        return waves.poll();
    }

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

        /* -------------------------- Mall for each wave
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


    private Queue<EnemyType> createPartWave(int numberOfEnemies, EnemyType enemieType){
        Queue<EnemyType> currentEnemieType = new LinkedList<EnemyType>();
        for(int i = 0 ; i < numberOfEnemies ; i++){
            currentEnemieType.add(enemieType);
        }
        return currentEnemieType;
    }

    public boolean wavesIsEmpty(){
        return waves.isEmpty();
    }

    public int getCurrentWaveNumber(){
        return currentWave;
    }

    public void updateSpawnRate(){
        if (spawnRate > 0) spawnRate--;
    }

    public boolean checkIfSpawnable(){
        if (spawnRate > 0) return false;
        spawnRate = MAX_SPAWN_RATE;
        return true;
    }

    public Queue<AEnemy> getWave(){
        currentWave++;
        return waveFactory.createCurrentWave(waves.poll());
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