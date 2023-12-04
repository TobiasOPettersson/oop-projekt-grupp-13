package Model.Towers;

import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class SlicerTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public SlicerTower(int x, int y) {
        super(x, y, 2, 1, 0, 30, TowerType.slicer, 2);
        setTargetTypes(TargetType.first, TargetType.enemies);
    }

    @Override
    public void upgrade(Upgrade upgrade){
        switch (upgrade) {
            case IncreasedSpeed1:
                    setMaxCooldown(getMaxCooldown() - 5);
                break;
            case IncreasedDamage1:
                setDamage(getDamage() + 1);
                break;
            default:
                System.out.println("Tower  doesn't have that upgrade");
                break;
        }
    }
}