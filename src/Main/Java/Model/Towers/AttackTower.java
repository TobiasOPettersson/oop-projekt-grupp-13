package Model.Towers;

import Model.Enemies.AEnemy;
import Model.Interfaces.IAttackable;

public abstract class AttackTower extends ATower implements IAttackable{
    private int damage;

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
    public AttackTower(int x, int y, int cost, double range, double aoeRange, int maxCooldown, TowerType towerType, int damage) {
        super(x, y, cost, range, aoeRange, maxCooldown, towerType);
        this.damage = damage;
    }

   /**
    * The default attack for attack towers, dealing damage to the first enemy in range. Method is from the interface IAttackable
    */
    @Override
    public void attack(AEnemy target) {
        if(target != null){
            target.takeDamage(damage);
            resetCooldown();
        }
    }
}
