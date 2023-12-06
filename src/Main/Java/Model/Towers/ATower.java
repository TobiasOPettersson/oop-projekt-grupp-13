package Model.Towers;

import java.util.ArrayList;
import java.util.List;

import Model.Enemies.AEnemy;
import Model.Interfaces.ITargetable;

public abstract class ATower implements ITargetable {
    private double x;
    private double y;
    private int cost;
    private double range;
    private double aoeRange;
    private int cooldown = 0; // Cooldown starts at 0 so towers can use their abilities directly
    private int maxCooldown;
    private TowerType towerType;
    private TargetType[] targetType;
    private int animationIndex;

    /**
     * Constructor of abstract class ATower
     * 
     * @param x           the x-position of the tower as a grid-index, i.e. not the
     *                    x-position of the sprite in view
     * @param y           the y-position of the tower as a grid-index, i.e. not the
     *                    y-position of the sprite in view
     * @param cost        is the amount of money needed to buy one tower
     * @param range       is the range of the towers ability
     * @param aoeRange    is the aoerange of the towers ability, 0 if the ability
     *                    isn't an aoe
     * @param maxCooldown is the maximum cooldown of the towers ability, the
     *                    variable cooldown will reset to this after an ability has
     *                    been used
     * @param towerType   is the type of the tower, for example knife or mallet
     */
    public ATower(double x, double y, int cost, double range, double aoeRange, int maxCooldown, TowerType towerType) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.range = range;
        this.aoeRange = aoeRange;
        this.maxCooldown = maxCooldown;
        this.towerType = towerType;
        this.animationIndex = 0;
    }

    /**
     * Finds enemies in range
     * 
     * @param enemies All enemies on the map
     * @return Enemies in range, either the first or all
     */
    public List<AEnemy> findEnemiesInRange(List<AEnemy> enemies) {
        List<AEnemy> targets = new ArrayList<>();
        for (AEnemy enemy : enemies) {
            if (inRangeOf(this, enemy, range)) {
                targets.add(enemy);
                if (targetType[0] == TargetType.first) {
                    targets.addAll(findAoeTargets(enemy, enemies));
                    return targets;
                }
            }
        }
        if (targets.size() > 0) {
            return targets;
        }
        return null;
    }

    /**
     * Finds each targetable in range
     * 
     * @param source  The enemy that is the center of the aoe
     * @param enemies All enemies on the map
     * @return The targets in aoe range of the tower ability
     */
    public List<AEnemy> findAoeTargets(AEnemy source, List<AEnemy> enemies) {
        List<AEnemy> aoeTargets = new ArrayList<>();
        if (aoeRange != 0) {
            List<AEnemy> aoeTargetables = enemies;
            aoeTargetables.remove(source);
            for (AEnemy enemy : aoeTargetables) {
                if (inRangeOf(source, enemy, aoeRange)) {
                    aoeTargets.add(enemy);
                }
            }
        }
        return aoeTargets;
    }

    /**
     * Checks if a targetable is in range or not
     * Without "distance += ...;" it will look like the enemy was attacked out of
     * range, even though it wasn't
     * 
     * @param enemy to check
     * @return whether or not the enemy is in range of the towers attack
     */
    public boolean inRangeOf(ITargetable source, ITargetable targetable, double range) {
        double distance = Math
                .sqrt(Math.pow(source.getX() - targetable.getX(), 2) + Math.pow(source.getY() - targetable.getY(), 2));
        distance -= 0.6;
        System.out.println(distance);
        return distance <= range;
    }

    /**
     * Decrements cooldown unless it's already at 0
     */
    public void decrementCooldown() {
        if (cooldown > 0) {
            cooldown--;
        } else {
            cooldown = 0;
        }
    }

    /*
     * Updates animationIndex when the tower is not on cooldown
     */
    public void updateAnimationIndex() {
        //if (!isOnCooldown()) {
            animationIndex++;
            if (animationIndex >= 4) {
                animationIndex = 0;
            /*}
        } else {
            if (animationIndex != 0) {
                animationIndex++;
                if (animationIndex >= 4) {
                    animationIndex = 0;
                }
            }*/
            //animationIndex = 0;
        }
    }
    public void resetAnimation(){
        animationIndex = 0;
    }

    /**
     * Checks if tower ability is on cooldown
     * 
     * @return true if cooldown larger than 0
     */
    public boolean isOnCooldown() {
        return cooldown > 0;
    }

    /**
     * Resets cooldown to the maximum
     */
    public void resetCooldown() {
        this.cooldown = maxCooldown;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public int getAnimationIndex(){
        return animationIndex;
    }
        

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public int getCost() {
        return cost;
    }

    public double getRange() {
        return range;
    }

    public void setTargetTypes(TargetType targetable, TargetType target) {
        targetType = new TargetType[] { target, targetable };
    }

    /**
     * ------------------- Targeting methods that use ITargetable
     * ------------------------
     */

    /**
     * Finds each targetable in range
     * 
     * @param targetables are either the enemies on the map or the towers, depending
     *                    of if the tower ability targets enemies or towers
     * @return if the tower only targets the first only return the first targetable
     *         in range, otherwise all. Return null if there are no targets
     */
    public List<ITargetable> findTargets(List<ITargetable> targetables) {
        List<ITargetable> targets = new ArrayList<>();
        for (ITargetable targetable : targetables) {
            if (inRangeOf((ITargetable) this, targetable, range)) {
                targets.add(targetable);
                if (targetType[0] == TargetType.first) {
                    targets.addAll(findAoeTargets(targetable, targetables));
                    return targets;
                }
            }
        }
        if (targets.size() > 0) {
            return targets;
        }
        return null;
    }

    /**
     * Finds each targetable in range
     * 
     * @param source      is the enemy or tower that is the center of the aoe
     * @param targetables are either the enemies on the map or the towers, depending
     *                    of if the tower ability targets enemies or towers
     * @return if the tower only targets the first only return the first targetable
     *         in range, otherwise all. Return null if there are no targets
     */
    public List<ITargetable> findAoeTargets(ITargetable source, List<ITargetable> targetables) {
        List<ITargetable> aoeTargets = new ArrayList<>();
        if (aoeRange != 0) {
            List<ITargetable> aoeTargetables = targetables;
            aoeTargetables.remove(source);
            for (ITargetable aoeTargetable : aoeTargetables) {
                if (inRangeOf(source, aoeTargetable, aoeRange)) {
                    aoeTargets.add(aoeTargetable);
                }
            }
        }
        return aoeTargets;
    }
}
