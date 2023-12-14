package Model.Towers;

import Model.Enemies.AEnemy;
import Model.Enums.Condition;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class FreezerTower extends ATower {
    private int chillDuration = 30;

    public FreezerTower(int x, int y) {
        super(x, y, 6, 1.0, 0, 30, TowerType.freezer, 0, TargetType.all, TargetType.enemies);
        upgradeIntMap.put(Upgrade.Frostbite, 0);
        upgradeIntMap.put(Upgrade.SuperChill, 0);
        upgradeIntMap.put(Upgrade.ConditionDuration, 20);
        upgradeDoubleMap.put(Upgrade.Range, 0.5);
    }

    /**
     * Blowtorch overrides the default attack method in order to apply the chill condition if it has the right upgrade
     * Also since if it has the upgrade Frostbite, it can deal damage to chilled enemies
     * @param target The target of the attack
     */
    @Override
    public void useAbility(AEnemy target) {
        if(target != null){
            if(hasUpgrade(Upgrade.Frostbite)){
                if(target.hasCondition(Condition.chilled) || target.hasCondition(Condition.superChilled)){
                    target.takeDamage(damage+1);
                }
            }
            
            int increasedChillDuration = 0;
            if(hasUpgrade(Upgrade.ConditionDuration)){
                increasedChillDuration += 20;
            }

            if(hasUpgrade(Upgrade.SuperChill)){
                target.setCondition(Condition.superChilled, chillDuration+increasedChillDuration);
            } else{
                target.setCondition(Condition.chilled, chillDuration+increasedChillDuration);
            }
            resetCooldown();
        }
    }

    public Integer getChillDuration() {
        return null;
    }
}