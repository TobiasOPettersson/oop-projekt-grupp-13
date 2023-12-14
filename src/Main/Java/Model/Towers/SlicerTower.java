package Model.Towers;

import Model.Enums.EnemyType;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

/**
 * Class representing a cheese slicer tower
 */
public class SlicerTower extends ATower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index
     * @param y the y-position of the tower as a grid-index
     */
    public SlicerTower(int x, int y) {
        super(x, y, 2, 1.0, 0, 60, TowerType.slicer, 4, TargetType.first, TargetType.enemies);
        upgradeDoubleMap.put(Upgrade.AoeRange, 0.5);
        upgradeIntMap.put(Upgrade.Damage, 4);
        upgradeIntMap.put(Upgrade.Damage2, 4);
        upgradeIntMap.put(Upgrade.Damage3, 4);
    }

    /**
     * Slicer does more damage to cheese and the only one who doesn't deal reduced damage to it
     * @param type  The type of enemy that the tower does increased or decreased damage to
     * @return      The damage after modifications
     */
    @Override
    protected int getDamage(EnemyType type) {
        switch (type) {
            case cheese:
                return damage*2;   
            default:
                return damage;
        }
    }
}