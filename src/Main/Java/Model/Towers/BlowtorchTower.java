package Model.Towers;

import Model.Enemies.AEnemy;
import Model.Enums.Condition;
import Model.Enums.EnemyType;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

/**
 * Class representing a blowtorch tower
 */
public class BlowtorchTower extends ATower{
    int burnDuration = 2;

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public BlowtorchTower(int x, int y) {
        super(x, y, 10, 3, 1.0, 30, TowerType.blowtorch, 1, TargetType.first, TargetType.enemies);
        upgradeDoubleMap.put(Upgrade.AoeRange, 1.0);
        upgradeDoubleMap.put(Upgrade.Range, 1.0);
        upgradeIntMap.put(Upgrade.Damage, 1);
        upgradeIntMap.put(Upgrade.SetOnFire, 0);
    }

    /**
     * Blowtorch overrides the default ability method in order to apply burning condition if it has the right upgrade
     * @param target The target of the attack
     */
    @Override
    public void useAbility(AEnemy target) {
        if(target != null){
            System.out.println("Blowtorch attack");
            if(hasUpgrade(Upgrade.SetOnFire)){
                target.setCondition(Condition.onFire, burnDuration);
            }
            target.takeDamage(getDamage(target.getEnemyType()));
            target.setStaggered(true);
            resetCooldown();
            System.out.println(target.getHealth());
        }
    }  

    /**
     * Blowtorch does more damage to chickens and less to cheese
     * @param type The type of enemy that the tower does increased or decreased damage to
     * @return The damage after modifications
     */
    @Override
    protected int getDamage(EnemyType type) {
        switch (type) {
            case chicken:
                return damage*2;
            case cheese:
                return damage-1;
            default:
                return damage;
        }
    }
}