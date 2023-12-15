package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import Model.Enemies.AEnemy;
import Model.Enemies.Wave;
import Model.Enums.Direction;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Interfaces.IObservable;
import Model.Interfaces.ITargetable;
import Model.Interfaces.ITowerUpgradeObserver;
import Model.Map.AMap;
import Model.Map.ATile;
import Model.Map.MapOne;
import Model.Map.TowerTile;
import Model.Player.Player;
import Model.Towers.ATower;

public class MainModel implements ITowerUpgradeObserver {
    private AMap map;
    private List<AEnemy> enemies = new ArrayList<AEnemy>();
    private Queue<AEnemy> currentWaveEnemies = new LinkedList<AEnemy>();
    private Player player;
    private boolean alive;
    private boolean activeWave;
    private Wave allWaves;
    List<IObservable> observers = new ArrayList<IObservable>();

    /**
     * The constructor for this class
     */
    public MainModel() {
        player = new Player(5, 3);
        map = new MapOne(player);
        allWaves = new Wave(map.getStartPosition(), map.getPathDirections());
        alive = true;
        activeWave = false;
    }

    /**
     * This is the method to call to update the game by one step
     */
    public void run() {
        if (activeWave) {
            updateEnemies();
            updateWave();
            updateTowers();

            alive = isAlive();
            activeWave = isActiveWave();
        }
        notifyObservers();
    }

    //----------------------Run helper methods-------------------------------//

    /**
     * Updates all enemies. Delete if they die
     */
    private void updateEnemies() {
        AEnemy enemyToRemove = null;
        for (AEnemy enemy : enemies) {
            enemy.updateAnimationTick();
            enemy.triggerConditions();
            enemy.move();
            enemy.setStaggered(false);
            if (enemy.getX() > map.getMapSizeX()) {
                player.takeDamage(enemy.getDamage());
                enemyToRemove = enemy;
            }
        }

        // Removal is outside the for-loop so that the list size doesnt change while
        // inside the for-loop
        if (enemyToRemove != null) {
            enemies.remove(enemyToRemove);
        }
    }

    /**
     * Updates the wave. Spawn enemies while current wave have enemies
     */
    private void updateWave() {
        allWaves.updateSpawnRate();
        if (allWaves.checkIfSpawnable() && currentWaveEnemies.isEmpty() == false) {
            enemies.add(currentWaveEnemies.poll());
        }
    }

    /**
     * Updates all the towers that uses their abilities if its off cooldown and targets are in range
     */
    private void updateTowers() {
        for (ATower tower : map.getTowers()) {
                tower.updateAnimationTick();
                if (!tower.isOnCooldown()) {
                    List<AEnemy> targets = tower.findEnemiesInRange(enemies);
                    if (targets != null) {
                        for (AEnemy target : targets) {
                            tower.useAbility(target);
                            if (target.getHealth() <= 0) {
                                player.addMoney(target.getMoney());
                                enemies.remove(target);
                            }
                        }
                    }
                } else {
                    tower.decrementCooldown();
                }
            }
    }


    //-----------------------Tower methods---------------------// 

    /**
     * Calls createTower() in map that creates a new instance of a tower
     * @param x          Grid x-index of where the tower will be created
     * @param y          Grid y-index of where the tower will be created
     * @param type       Type of the tower
     * @throws Exception if the player doesn't have enough money to buy the tower
     */
    public void createTower(int x, int y, TowerType type) throws Exception {
        map.createTower(x, y, type);
    }

    /**
     * Calls upgradeTower() in map that upgrades the tower at tile (x, y)
     * @param x       The towers x-index on the grid
     * @param y       The towers y-index on the grid
     * @param upgrade The type of upgrade that will be added
     */
    @Override
    public void upgradeTower(int x, int y, Upgrade upgrade, int cost) throws Exception {
        map.upgradeTower(x, y, upgrade, cost);
    }

    //-----------------------Observer---------------------------//

    public void addObserver(IObservable observer) {
        observers.add(observer);
    }

    /**
     * Notifies the DrawPanel to repain all components
     */
    private void notifyObservers(){
        for (IObservable observer : observers) {
            observer.update();
        }
    }

    //-----------------------Other methods---------------------// 

    /**
     * Starts the wave
     * @throws Exception
     */
    public void play() throws Exception {
        if (canStartNewWave()) {
            currentWaveEnemies = allWaves.getNextWave();
            activeWave = true;
        }
    }

    /**
     * Checks whether a new wave can start
     * @return true if u can start a new wave, otherwise false
     */
    private boolean canStartNewWave() {
        if (activeWave == false && alive == true && currentWaveEnemies.isEmpty() == true
                && enemies.isEmpty() == true)
            return true;
        return false;
    }

    /**
     * Checks whether the player is alive or not
     * @return true if alive, false if dead
     */
    private boolean isAlive(){
        return player.getHealth() > 0;
    }
   


    //-----------------------ITargetale convertion methods (Might use)---------------------// 

    public List<ITargetable> convertEnemiesToTargetables() {
        List<ITargetable> targetables = new ArrayList<>();
        for (AEnemy enemy : enemies) {
            targetables.add(enemy);
        }
        return targetables;
    }

    public List<ITargetable> convertTowersToTargetables() {
        List<ITargetable> targetables = new ArrayList<>();
        for (ATower tower : map.getTowers()) {
            targetables.add(tower);
        }
        return targetables;
    }

    //----------------------------Waves--------------------------// 

    /**
     * @return whether or not there is an active wave
     */
    public boolean isActiveWave(){
        if (enemies.isEmpty() && currentWaveEnemies.isEmpty()) return false;
        return true;
    }

    //----------------------------Getters and setters--------------------------// 

    public boolean getAlive() {
        return alive;
    }

    public boolean getActiveWave() {
        return activeWave;
    }

    public ATile[][] getTileGrid() {
        return map.getTileGrid();
    }

    public List<Direction> getPathDirections() {
        return map.getPathDirections();
    }

    public int getStartPosition() {
        return map.getStartPosition();
    }

    public int getEndPosition() {
        return map.getEndPosition();
    }

    public int[][] getPathGrid() {
        return map.getPathGrid();
    }

    public AMap getMap() {
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

    public List<AEnemy> getEnemyArray() {
        return enemies;
    }

    public List<ATower> getTowers() {
        return map.getTowers();
    }

    public ATile getTile(int x, int y){
        return map.getTile(x, y);
    }

    public boolean tileIsTowerTile(int x, int y){
        return map.tileIsTowerTile(x, y);
    }

    public ATower getTowerOnTile(TowerTile tile){
        return tile.getTower();
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerMoney() {
        return player.getMoney();
    }

    public int getPlayerHealth() {
        return player.getHealth();
    }

    public boolean allWavesDead() {
        return allWaves.wavesIsEmpty();
    }

    public int getCurrentWaveNumber() {
        return allWaves.getCurrentWaveNumber();
    }

    public int getMaxNumberofWaves() {
        return allWaves.getMaxNumberOfWaves();
    }
}