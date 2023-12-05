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
    }

    @Override
    public void attack(AEnemy target) {
        if(target != null){
            if(hasUpgrade(Upgrade.Frostbite)){
                if(target.isChilled()){
                    target.takeDamage(getDamage()+1);                
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

    @Override
    public void upgrade(Upgrade upgrade) {
        switch (upgrade) {
            case IncreasedRange1:
                setRange(getRange() + 0.5);
                addUpgrade(upgrade);
                break;
            case IncreasedConditionDuration:
                setDamage(chillDuration += 10);
                addUpgrade(upgrade);
                break;
            case Frostbite:
            case SuperChill:
                addUpgrade(upgrade);
                break;
            case IncreasedSpeed1:
                setMaxCooldown(getMaxCooldown() - 10);
                addUpgrade(upgrade);
            default:
                System.out.println("Tower  doesn't have that upgrade");
                break;
        }
    }   
}