package Model.Towers;

import Model.Enemies.AEnemy;
import Model.Enums.Condition;
import Model.Enums.EnemyType;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class BlowtorchTower extends AttackTower{
    int burnDuration = 2;

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public BlowtorchTower(int x, int y) {
        super(x, y, 4, 3, 0.5, 10, TowerType.blowtorch, 1, TargetType.first, TargetType.enemies);
        upgradeMap.put(Upgrade.AoeRange, 0.2);
        upgradeMap.put(Upgrade.Range, 0.5);
        upgradeMap.put(Upgrade.Damage, 1);
        upgradeMap.put(Upgrade.SetOnFire, 0);
    }

    /**
     * Blowtorch overrides the default attack method in order to apply burning condition if it has the right upgrade
     * @param target The target of the attack
     */
    @Override
    public void attack(AEnemy target) {
        if(target != null){
            System.out.println("Blowtorch attack");
            if(hasUpgrade(Upgrade.SetOnFire)){
                target.setCondition(Condition.onFire, burnDuration);
            }
            target.takeDamage(getDamage(target.getType()));
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