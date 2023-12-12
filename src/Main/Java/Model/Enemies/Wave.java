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
    private WaveFactory waveFactory;
    
    public Wave(int startPosition, List<Direction> pathDirections){
        this.currentWave = 0;
        createWaves();
        this.spawnRate = this.MAX_SPAWN_RATE;
        this.waveFactory = new WaveFactory(startPosition, pathDirections);
    }

    public Queue<EnemyType> startWave(){
        this.currentWave++;
        if (waves.isEmpty() == true) new Exception("Wave is empty");
        return waves.poll();
        
    }

    private void createWaves(){
        Queue<EnemyType> createCurrentWave = new LinkedList<EnemyType>();
        
        //Waves 1
        createCurrentWave.addAll(createPartWave(5, EnemyType.banana));
        this.waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();
        
        //Wave 2
        createCurrentWave.addAll(createPartWave(10, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(2, EnemyType.tomato));
        this.waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();

 

        /* -------------------------- Mall for each wave
        //Wave #
        createCurrentWave.addAll(createPartWave(1, EnemyType.banana));
        createCurrentWave.addAll(createPartWave(1, EnemyType.tomato));
        createCurrentWave.addAll(createPartWave(1, EnemyType.cheese));
        createCurrentWave.addAll(createPartWave(1, EnemyType.chicken));
        this.waves.add(new LinkedList<EnemyType>(createCurrentWave));
        createCurrentWave.clear();
         */
        this.maxNumberOfWaves = this.waves.size();
    }


    private Queue<EnemyType> createPartWave(int numberOfEnemies, EnemyType enemieType){
        Queue<EnemyType> currentEnemieType = new LinkedList<EnemyType>();
        for(int i = 0 ; i < numberOfEnemies ; i++){
            currentEnemieType.add(enemieType);
        }
        return currentEnemieType;
    }

    public boolean wavesIsEmpty(){
        return this.waves.isEmpty();
    }

    public int getCurrentWaveNumber(){
        return this.currentWave;
    }

    public void updateSpawnRate(){
        this.spawnRate--;

    }

    public boolean checkIfSpawnable(){
        if (this.spawnRate != 0) return false;
        this.spawnRate = this.MAX_SPAWN_RATE;
        return true;
    }

    public Queue<AEnemy> getWave(){
        this.currentWave++;
        return this.waveFactory.createCurrentWave(this.waves.poll());
    }

    public int getMaxNumberOfWaves() {
        return this.maxNumberOfWaves;
    }
}