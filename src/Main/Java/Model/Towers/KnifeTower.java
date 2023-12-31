package Model.Towers;

import Model.Enums.EnemyType;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

/**
 * Class representing a knife tower
 */
public class KnifeTower extends ATower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * Can upgrade damage, speed, number of targets and range
     * @param x the x-position of the tower as a grid-index
     * @param y the y-position of the tower as a grid-index
     */
    public KnifeTower(int x, int y) {
        super(x, y, 2, 1.0, 0, 50, TowerType.knife, 5, TargetType.first, TargetType.enemies);
        upgradeIntMap.put(Upgrade.Damage, 4);
        upgradeIntMap.put(Upgrade.Speed, 20);
        upgradeIntMap.put(Upgrade.Targets, 1);
        upgradeDoubleMap.put(Upgrade.Range, 1.0);
    }

    /**
     * Knife does less damage to cheese
     * @param type The type of enemy that the tower does increased or decreased damage to
     * @return The damage after modifications
     */
    @Override
    protected int getDamage(EnemyType type) {
        switch (type) {
            case cheese:
                return damage-1;
            default:
                return damage;
        }
    }
}
