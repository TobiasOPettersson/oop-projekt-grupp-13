package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class AttackTower extends ATower implements IShootable{
    protected int damage;

   public AttackTower(int x, int y) {
       super(x, y);
   }

   @Override
    public AEnemy findFirstTarget(List<AEnemy> enemies){
        if(cooldown == 0){
            for (AEnemy enemy : enemies) {
                if(inRangeOf(enemy)){
                    return enemy;
                }
            }
        }
        return null;
    }

    @Override
    public void shoot(AEnemy enemy) {
         AEnemy target = findFirstTarget(null);
         target.doDamage(damage);
         cooldown = maxCooldown;
    }

   public int getDamage() {
       return this.damage;
   }
   public void setDamage(int damage) {
       this.damage = damage;
   }
}
