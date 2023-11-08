package src.Model;

import java.awt.*;

public abstract class AttackTower extends ATower implements IShootable{
   private int damage;
   private int fireSpeed;

   public AttackTower(int x, int y, int cost, int range, Image model, int damage, int fireSpeed) {
       super(x, y, cost, range, model);
       this.damage = damage;
       this.fireSpeed = fireSpeed;
   }

   public int getDamage() {
       return damage;
   }
   public void setDamage(int damage) {
       this.damage = damage;
   }

   public int getFireSpeed() {
       return fireSpeed;
   }
   public void setFireSpeed(int fireSpeed) {
       this.fireSpeed = fireSpeed;
   }
}
