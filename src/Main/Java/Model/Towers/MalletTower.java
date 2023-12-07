package Model.Towers;

import Model.Enemies.AEnemy;
import Model.Enums.EnemyType;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class MalletTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * Can upgrade damage twice, aoe range, and range
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public MalletTower(int x, int y) {
        super(x, y, 3, 1, 1, 200, TowerType.mallet, 3, TargetType.first, TargetType.enemies);
        upgradeMap.put(Upgrade.AoeRange, 0.5);
        upgradeMap.put(Upgrade.Range, 0.5);
        upgradeMap.put(Upgrade.Damage, 1);
        upgradeMap.put(Upgrade.Damage2, 1);
    }

    @Override
    protected int getDamage(EnemyType type) {
        switch (type) {
            case tomato:
                return damage*2;   
            case cheese:
                return damage-3;
            default:
                return damage;
        }
    }

}
