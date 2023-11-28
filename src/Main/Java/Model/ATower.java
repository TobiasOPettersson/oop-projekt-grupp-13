package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ATower implements IPlacable{
   protected int x;
   protected int y;
   protected int cost;
   protected int range;
   protected int cooldown = 0;
   protected int maxCooldown;
   protected TowerType towerType;

   public ATower(int x, int y, int cost, int range, int maxCooldown, TowerType towerType) {
       this.x = x;
       this.y = y;
       this.cost = cost;
       this.range = range;
       this.maxCooldown = maxCooldown;
       this.towerType = towerType;
   }

    public AEnemy findFirstTarget(List<AEnemy> enemies){
        for (AEnemy enemy : enemies) {
            if(inRangeOf(enemy)){
                return enemy;
            }
        }
        return null;
    }

   public ArrayList<AEnemy> findAllTarget(ArrayList<AEnemy> enemies){
        ArrayList<AEnemy> targets = new ArrayList<>();   
        for (AEnemy enemy : enemies) {
            if(inRangeOf(enemy)){
                targets.add(enemy);
            }
        }
        return enemies;
    }

    public boolean inRangeOf(AEnemy enemy){
        double distance = Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2));
        return distance <= range;
    }

    public boolean triggerCooldown() {
        if(cooldown > 0){
            cooldown--;
            return false;
        } else{
            cooldown = 0;
            return true;
        }
    }

    public void resetCooldown() {
        this.cooldown = maxCooldown;
    }

    public TowerType getTowerType(){
        return towerType;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getXInt(){
        return x;
    }

    public int getYInt(){
        return y;
    }
}
