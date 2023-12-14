package Model.Towers;

import Model.Enums.EnemyType;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class MalletTower extends ATower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * Can upgrade damage twice, aoe range, and range
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public MalletTower(int x, int y) {
        super(x, y, 3, 1.0, 1.0, 200, TowerType.mallet, 20, TargetType.first, TargetType.enemies);
        upgradeDoubleMap.put(Upgrade.AoeRange, 1.0);
        upgradeDoubleMap.put(Upgrade.Range, 1.0);
        upgradeIntMap.put(Upgrade.Damage, 1);
        upgradeIntMap.put(Upgrade.Damage2, 1);
    }

    /**
     * Mallet does more damage to tomatoes and less damage to cheese
     * @param type The type of enemy that the tower does increased or decreased damage to
     * @return The damage after modifications
     */
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
