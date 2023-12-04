package Model.Towers;

import java.util.List;

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
        super(x, y, 1, 1, 0, 50, TowerType.knife, 2);
        setTargetTypes(TargetType.first, TargetType.enemies);
    }

    @Override
    public void upgrade(Upgrade upgrade){
        switch (upgrade) {
            case IncreasedTargets1:
                setTargetTypes(TargetType.firstTwo, TargetType.enemies);
                break;
            case IncreasedTargets2:
                setTargetTypes(TargetType.firstThree, TargetType.enemies);
                break;
            case IncreasedRange1:
            case IncreasedRange2:
                setRange(getRange() + 1);
                break;
            case IncreasedDamage1:
                setDamage(getDamage() + 1);
                break;
            case IncreasedSpeed1:
            case IncreasedSpeed2:
                setMaxCooldown(getMaxCooldown() - 10);
                setMaxCooldown(getMaxCooldown() - 10);
                break;
            default:
                System.out.println("Tower  doesn't have that upgrade");
                break;
        }
    }

   
}
