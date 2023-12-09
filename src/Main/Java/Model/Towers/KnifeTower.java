package Model.Towers;

import java.util.List;

import Model.Enums.EnemyType;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class KnifeTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * Can upgrade damage, speed, number of targets and range
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public KnifeTower(int x, int y) {
        super(x, y, 1, 1, 0, 50, TowerType.knife, 2, TargetType.first, TargetType.enemies);
        upgradeMap.put(Upgrade.Damage, 3);
        upgradeMap.put(Upgrade.Speed, 1);
        upgradeMap.put(Upgrade.Targets, 1);
        upgradeMap.put(Upgrade.Range, 1);
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
