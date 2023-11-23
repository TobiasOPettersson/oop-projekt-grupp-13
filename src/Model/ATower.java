package src.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ATower implements IPlacable{
   private int x;
   private double y;
   private int cost;
   protected int range;
   private Image model;
   protected int cooldown;
   protected int maxCooldown;

   public ATower(int x, int y) {
       this.x = x;
       this.y = y;
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

    public void changeCooldown() {
        if (this.maxCooldown > 0) this.maxCooldown--;
        else this.maxCooldown = this.cooldown--;
    }

    public int getCooldown() {
        return cooldown;
    }
    public void setCooldown(int fireSpeed) {
        this.cooldown = fireSpeed;
    }

   public double getX() {
       return x;
   }

   public void setX(int x) {
       this.x = x;
   }

   public double getY() {
       return y;
   }

   public void setY(int y) {
       this.y = y;
   }

   public int getCost() {
       return cost;
   }

   public void setCost(int cost) {
       this.cost = cost;
   }

   public int getRange() {
       return range;
   }

   public void setRange(int range) {
       this.range = range;
   }

   public Image getModel() {
       return model;
   }

   public void setModel(Image model) {
       this.model = model;
   }
}
