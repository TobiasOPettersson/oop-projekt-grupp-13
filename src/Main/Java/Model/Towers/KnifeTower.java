package Model.Towers;

import java.util.List;

import Model.Enemies.EnemyType;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class KnifeTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public KnifeTower(int x, int y) {
        super(x, y, 1, 1, 0, 50, TowerType.knife, 2, TargetType.first, TargetType.enemies);
    }

    @Override
    public void upgrade(Upgrade upgrade){
        switch (upgrade) {
            case IncreasedTargets1:
                setTargetTypes(TargetType.firstTwo, TargetType.enemies);
                addUpgrade(upgrade);
                break;
            case IncreasedTargets2:
                setTargetTypes(TargetType.firstThree, TargetType.enemies);
                addUpgrade(upgrade);
                break;
            case IncreasedRange1:
            case IncreasedRange2:
                setRange(getRange() + 1);
                addUpgrade(upgrade);
                break;
            case IncreasedDamage1:
                setDamage(getDamage() + 1);
                addUpgrade(upgrade);
                break;
            case IncreasedSpeed1:
            case IncreasedSpeed2:
                setMaxCooldown(getMaxCooldown() - 10);
                setMaxCooldown(getMaxCooldown() - 10);
                addUpgrade(upgrade);
                break;
            default:
                System.out.println("Tower  doesn't have that upgrade");
                break;
        }
    }

    @Override
    protected int getDamageWithTypeModifications(EnemyType type) {
        switch (type) {
            case cheese:
                return getDamage()-1;
            default:
                return getDamage();
        }
    }
}
