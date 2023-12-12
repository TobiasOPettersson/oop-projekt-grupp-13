package Model.Towers;

import Model.Enums.EnemyType;
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
        super(x, y, 2, 1, 0, 30, TowerType.slicer, 2, TargetType.first, TargetType.enemies);
        upgradeMap.put(Upgrade.AoeRange, 0.5);
        upgradeMap.put(Upgrade.Damage, 1);
        upgradeMap.put(Upgrade.Damage2, 1);
    }

    /**
     * Slicer does more damage to cheese and the only one who doesn't deal reduced damage to it
     * @param type The type of enemy that the tower does increased or decreased damage to
     * @return The damage after modifications
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