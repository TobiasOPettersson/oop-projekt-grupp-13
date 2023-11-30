package Model.Towers;

import java.util.ArrayList;
import java.util.List;

import Model.Enemies.AEnemy;
import Model.Interfaces.IAttackable;

public abstract class AttackTower extends ATower implements IAttackable{
    protected int damage;

   public AttackTower(int x, int y, int cost, int range, int maxCooldown, TowerType towerType, int damage) {
       super(x, y, cost, range, maxCooldown, towerType);
       this.damage = damage;
   }

   @Override
    public AEnemy findFirstTarget(List<AEnemy> enemies){
        for (AEnemy enemy : enemies) {
            if(inRangeOf(enemy)){
                return enemy;
            }
        }
        return null;
    }

    @Override
    public void attack(AEnemy target) {
        if(target != null){
            target.doDamage(damage);
            resetCooldown();
            System.out.println("Has attacked!!!!");
        }
    }

   public int getDamage() {
       return this.damage;
   }
   public void setDamage(int damage) {
       this.damage = damage;
   }
}
