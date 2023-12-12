package Model.Towers;

import Model.Enemies.AEnemy;
import Model.Enums.EnemyType;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Interfaces.IAttackable;

public abstract class AttackTower extends ATower{
    protected int damage;

    /**
     * Constructor of towers that can attack, i.e. their ability damages enemies 
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     * @param cost is the amount of money needed to buy one tower
     * @param range is the range of the towers ability 
     * @param maxCooldown is the maximum cooldown of the towers ability, the variable cooldown will reset to this after an ability has been used
     * @param towerType is the type of the tower, for example knife or mallet     
     * @param damage is the amount of damage an attack does
     */
    public AttackTower(int x, int y, int cost, double range, double aoeRange, int maxCooldown, TowerType towerType, int damage, TargetType nTargets, TargetType targetType) {
        super(x, y, cost, range, aoeRange, maxCooldown, towerType, nTargets, targetType);
        this.damage = damage;
    }

   /**
    * The default attack for attack towers, dealing damage to the first enemy in range. Method is from the interface IAttackable
    * @param target The enemy the tower is attacking 
    */
    public void attack(AEnemy target) {
        if(target != null){
            target.takeDamage(getDamage(target.getType()));
            target.setStaggered(true);
            resetCooldown();
        }
    }

    /**
     * Upgrade for damage is here since ATower doesn't share the damage variable
     * The rest of upgrades are handled by ATower
     * @param upgrade The upgrade to add
     */
    @Override
    public void upgrade(Upgrade upgrade){
        if(upgrade == Upgrade.Damage || upgrade == Upgrade.Damage2){
            upgradeDamage(upgrade);
        } else{
            super.upgrade(upgrade);
        }
    }

    /**
     * Upgrades damage if the tower can upgrade damage
     * @param upgrade
     */
    protected void upgradeDamage(Upgrade upgrade){
        damage += upgradeIntMap.get(upgrade);
        upgrades.add(upgrade);
    }

    /**
     * Default damage, overridden in sub-classes
     * @param type The type of enemy that the tower does increased or decreased damage to
     * @return The damage after modifications
     */
    protected abstract int getDamage(EnemyType type);

    public int getDamage(){
        return damage;
    }
}
