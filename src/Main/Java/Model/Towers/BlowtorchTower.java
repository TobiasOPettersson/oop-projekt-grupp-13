package Model.Towers;

import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class BlowtorchTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public BlowtorchTower(int x, int y) {
        super(x, y, 4, 3, 0.5, 10, TowerType.blowtorch, 1);
        setTargetTypes(TargetType.first, TargetType.enemies);
    }

    @Override
    public void upgrade(Upgrade upgrade){
        switch (upgrade) {
            case IncreaseAoeRange1:
            case IncreaseAoeRange2:
                setAoeRange(getAoeRange() + 0.2);
            break;
            case IncreasedRange1:
                setRange(getRange() + 0.5);
                break;
            case IncreasedDamage1:
            case IncreasedDamage2:
                setDamage(getDamage() + 1);
                break;
            default:
                System.out.println("Tower  doesn't have that upgrade");
                break;
        }
    }
}