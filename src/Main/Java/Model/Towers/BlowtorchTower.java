package Model.Towers;

import Model.Enemies.EnemyType;
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
        super(x, y, 4, 3, 0.5, 10, TowerType.blowtorch, 1, TargetType.first, TargetType.enemies);
    }


    @Override
    public void upgrade(Upgrade upgrade){
        switch (upgrade) {
            case IncreaseAoeRange1:
            case IncreaseAoeRange2:
                setAoeRange(getAoeRange() + 0.2);
                addUpgrade(upgrade);
            break;
            case IncreasedRange1:
                setRange(getRange() + 0.5);
                addUpgrade(upgrade);
                break;
            case IncreasedDamage1:
            case IncreasedDamage2:
                setDamage(getDamage() + 1);
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
            case chicken:
                return getDamage()*2;   
            case cheese:
                return getDamage()-1;
            default:
                return getDamage();
        }
    }
}