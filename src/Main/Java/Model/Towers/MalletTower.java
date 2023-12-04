package Model.Towers;

import Model.Enemies.AEnemy;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class MalletTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public MalletTower(int x, int y) {
        super(x, y, 3, 1, 1, 200, TowerType.mallet, 3, TargetType.first, TargetType.enemies);
    }
   
    @Override
    public void upgrade(Upgrade upgrade){
        switch (upgrade) {
            case IncreaseAoeRange1:
                setAoeRange(getAoeRange() + 0.5);
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
