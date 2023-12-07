package Model.Towers;

import Model.Enemies.AEnemy;
import Model.Enums.Condition;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class FreezerTower extends AttackTower {
    private int chillDuration = 30;

    public FreezerTower(int x, int y) {
        super(x, y, 4, 1, 1, 30, TowerType.freezer, 0, TargetType.all, TargetType.enemies);
        upgradeMap.put(Upgrade.Frostbite, 0);
        upgradeMap.put(Upgrade.SuperChill, 0);
        upgradeMap.put(Upgrade.ConditionDuration, 10);
        upgradeMap.put(Upgrade.Range, 0.5);
    }

    @Override
    public void attack(AEnemy target) {
        if(target != null){
            if(hasUpgrade(Upgrade.Frostbite)){
                if(target.isChilled()){
                    target.takeDamage(damage+1);                
                }
            }
            if(hasUpgrade(Upgrade.SuperChill)){
                target.setCondition(Condition.superChilled, chillDuration);
            } else{
                target.setCondition(Condition.chilled, chillDuration);
            }
            resetCooldown();
        }
    }  
}