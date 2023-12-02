package Model.Towers;

import java.util.ArrayList;
import java.util.List;

import Model.Enemies.AEnemy;

public abstract class ATower{
    private int x;
    private int y;
    private int cost;
    private double range;
    private int cooldown = 0;  // Cooldown starts at 0 so towers can use their abilities directly
    private int maxCooldown;
    private TowerType towerType;

   /**
    * Constructor of abstract class ATower
    * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
    * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
    * @param cost is the amount of money needed to buy one tower
    * @param range is the range of the towers ability 
    * @param maxCooldown is the maximum cooldown of the towers ability, the variable cooldown will reset to this after an ability has been used
    * @param towerType is the type of the tower, for example knife or mallet
    */
   public ATower(int x, int y, int cost, double range, int maxCooldown, TowerType towerType) {
       this.x = x;
       this.y = y;
       this.cost = cost;
       this.range = range;
       this.maxCooldown = maxCooldown;
       this.towerType = towerType;
   }

    /**
    * Finds the first enemy in range of the towers range
    * @param enemies is the list of enemies on the map
    * @return the first enemy in range or null if there are no enemies in range
    */
    public AEnemy findFirstTarget(List<AEnemy> enemies){
        for (AEnemy enemy : enemies) {
            if(inRangeOf(enemy)){
                return enemy;
            }
        }
        return null;
    }

    /**
    * Finds all enemies inside of the towers range 
    * @param enemies is the list of enemies on the map
    * @return all enemies in range or null if there are no enemies in range
    */
    public ArrayList<AEnemy> findAllTargets(ArrayList<AEnemy> enemies){
        ArrayList<AEnemy> targets = new ArrayList<>();   
        for (AEnemy enemy : enemies) {
            if(inRangeOf(enemy)){
                targets.add(enemy);
            }
        }
        return enemies;
    }

    /**
     * Checks if an enemy is in range or not
     * Without "distance += ...;" it will look like the enemy was attacked out of range, even though it wasn't 
     * @param enemy to check 
     * @return whether or not the enemy is in range of the towers attack
     */
    public boolean inRangeOf(AEnemy enemy){
        double distance = Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2));
        distance += 0.3;
        return distance <= range;
    }

    /**
     * Decrements cooldown unless it's already at 0
     */
    public void decrementCooldown() {
        if(cooldown > 0){
            cooldown--;
        } else{
            cooldown = 0;
        }
    }

    /**
     * Checks if tower ability is on cooldown
     * @return true if cooldown larger than 0
     */
    public boolean isOnCooldown() {
        return cooldown > 0;
    }

    /**
     * Resets cooldown to the maximum
     */
    public void resetCooldown() {
        this.cooldown = maxCooldown;
    }

    public TowerType getTowerType(){
        return towerType;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getCost(){
        return cost;
    }

    public double getRange() {
        return range;
    }
}
