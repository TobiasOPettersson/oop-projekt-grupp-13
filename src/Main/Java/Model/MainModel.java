package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import Controller.Interfaces.ITowerUpgradeObserver;
import Model.Enemies.AEnemy;
import Model.Enemies.EnemyOne;
import Model.Enemies.Wave;
import Model.Enums.Direction;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Interfaces.ITargetable;
import Model.Enums.EnemyType;
import Model.Map.AMap;
import Model.Map.ATile;
import Model.Map.MapOne;
import Model.Player.Player;
import Model.Towers.ATower;
import Model.Towers.AttackTower;

public class MainModel implements ITowerUpgradeObserver{
    private AMap map;
    private List<AEnemy> enemies = new ArrayList<AEnemy>();
    private Queue<AEnemy> currentWaveEnemies = new LinkedList<AEnemy>();
    private Player player;
    private boolean alive;
    private boolean activeWave;
    private Wave allWaves;

    public MainModel(){
        this.player = new Player(5, 200);
        this.map = new MapOne();
        this.allWaves = new Wave();
        this.currentWaveEnemies = convertAllWavesToAEnemy();
        this.alive = true;
        this.activeWave = false;
    }

    public void run(){
        if(activeWave){

        AEnemy enemyToRemove = null;
        for (AEnemy enemy : enemies){
            //enemy.triggerConditions();
            enemy.move();
            enemy.setStagger(false);
            if (enemy.getX() > map.getMapSizeX()) {
                player.takeDamage(enemy.getDamage());
                enemyToRemove = enemy;
            }
        }

        // Removal is outside the for-loop so that the list size doesnt chance while inside the for-loop
        if(enemyToRemove != null){
            enemies.remove(enemyToRemove);
        }

        this.allWaves.updateSpawnRate();
        if(this.allWaves.checkIfSpawnable() && this.currentWaveEnemies.isEmpty() == false){
            System.out.println("Test");
            this.enemies.add(this.currentWaveEnemies.poll());
        }

        // Spawn a new enemy if the last enemy has walked one tile
        // if(enemies.isEmpty() || enemies.get(enemies.size()-1).getDirectionsSize() < map.getPathDirections().size()-2){
        //     enemies.add(new EnemyOne(map.getStartPosition(), 0.02, this.map.getPathDirections()));
        // }


        for (ATower tower : map.getTowers()){
            if(!tower.isOnCooldown()){
                if (tower instanceof AttackTower){
                    List<AEnemy> targets = tower.findEnemiesInRange(enemies);
                    if(targets != null){
                        for(AEnemy target : targets){
                            ((AttackTower)tower).attack(target);
                            target.setStaggered(true);
                            if (target.getHealth() <= 0) {
                                player.addMoney(target.getMoney());
                                enemies.remove(target);
                            }
                        }
                    }
                }
            } else{
                tower.decrementCooldown();
            }
        }

        this.alive = alive();
        //this.activeWave = activeWave(); Commented since it doesnt check if the wave is finished, only if there are no enemies currently on the panel/ in the list
        }
    }

    public void createTower(int x, int y, TowerType type) throws Exception{
        map.createTower(x, y, type);
    }

    @Override
    public void upgradeTower(int x, int y, Upgrade upgrade) {
        map.upgradeTower(x, y, upgrade);
    }

    public void play(){
        activeWave = true;
    }


    public List<ITargetable> convertEnemiesToTargetables(){
        List<ITargetable> targetables = new ArrayList<>();
        for (AEnemy enemy : enemies) {
            targetables.add(enemy);
        }
        return targetables;
    }

    public List<ITargetable> convertTowersToTargetables(){
        List<ITargetable> targetables = new ArrayList<>();
        for (ATower tower : map.getTowers()) {
            targetables.add(tower);
        }
        return targetables;
    }

    private boolean alive(){
        return player.getHealth() > 0;
    }


    //------------------------Waves----------------------//
    private Queue<AEnemy> convertAllWavesToAEnemy (){
        Queue<AEnemy> thisWave = new LinkedList<AEnemy>();
        Queue<EnemyType> thisWaveType = this.allWaves.startWave();
        EnemyType currentEnemyType;
        while (thisWaveType.isEmpty() == false) {
            currentEnemyType = thisWaveType.poll();
            if (currentEnemyType == EnemyType.banana) thisWave.add(new EnemyOne(this.map.getStartPosition(), 0.02, this.map.getPathDirections()));
            //TODO
            //if statements for every enemytype
        }
        return thisWave;
    }

    public boolean activeWave(){
        if (this.enemies.isEmpty() && this.currentWaveEnemies.isEmpty()) return false;
        return true;
    }

    public boolean getAlive(){
        return this.alive;
    }

    public boolean getActiveWave(){
        return this.activeWave;
    }

   public ATile [][] getTileGrid(){
        return map.getTileGrid();
    }

    public List<Direction> getPathDirections(){
        return map.getPathDirections();
    }
    public int getStartPosition(){
        return map.getStartPosition();
    }
    public int[][] getPathGrid(){
        return map.getPathGrid();
    }
    public AMap getMap(){
        return map;
    }

    public List<AEnemy> getEnemies() {
        return enemies;
    }

    public int getMapSizeX() {
        return map.getMapSizeX();
    }
    public int getMapSizeY() {
        return map.getMapSizeY();
    }
    public List<AEnemy> getEnemyArray(){
        return this.enemies;
    }
    public List<ATower> getTowers(){
        return this.map.getTowers();
    }

    public Player getPlayer() {
        map.setPlayer(player);
        return player;
    }
}