package Model.Towers;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Enemies.AEnemy;
import Model.Enums.TargetType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Interfaces.ITargetable;
import Model.Interfaces.IUpgradable;

public abstract class ATower implements ITargetable, IUpgradable {
    private double x;
    private double y;
    private int cost;
    private double range;
    private double aoeRange;
    private int cooldown = 0; // Cooldown starts at 0 so towers can use their abilities directly
    private int maxCooldown;
    private TowerType towerType;
    private TargetType[] targetType;
    private int nTargets;
    protected List<Upgrade> upgrades = new ArrayList<>();
    protected Map<Upgrade, Number> upgradeMap;
    private Point2D.Double targetPosition;
    private int animationIndex;
    private int animationTick;
    private boolean enemiesInRange = false;

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
    public ATower(double x, double y, int cost, double range, double aoeRange, int maxCooldown, TowerType towerType,
            TargetType targetType1, TargetType targetType2) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.range = range;
        this.aoeRange = aoeRange;
        this.maxCooldown = maxCooldown;
        this.towerType = towerType;
        this.animationTick = 0;
        this.animationIndex = 0;
        this.targetPosition = null;
        targetType = new TargetType[] { targetType1, targetType2 };
        nTargets = 1;
        upgradeMap = new HashMap<>();

    }

    // ----------------------------Methods for targeting----------------------//

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
                    targetPosition = new Point2D.Double(enemy.getX(), enemy.getY());
                    if (aoeRange != 0) {
                        targets.addAll(findAoeTargets(enemy, enemies));
                    }
                    enemiesInRange = true;
                    return targets;
                }
            }
        }
        if (targets.size() > 0) {
            return targets;
        }
        enemiesInRange = false;
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
        List<AEnemy> aoeTargetables = new ArrayList<>(enemies);
        for (AEnemy enemy : aoeTargetables) {
            if (!enemy.equals(source)) {
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
        return distance <= range;
    }

    // ----------------------------Upgrade method----------------------//

    /**
     * Upgrades the tower
     * 
     * @param The upgrade to add
     * @return whether or not the enemy is in range of the towers attack
     */
    @Override
    public void upgrade(Upgrade upgrade) {
        Number upgradeValue = upgradeMap.get(upgrade);
        if (upgradeValue == null) {
            System.out.println("Tower  doesn't have that upgrade");
        }

        switch (upgrade) {
            case Targets:
                nTargets += upgradeValue.intValue();
                break;
            case Range:
                range += upgradeValue.intValue();
            case AoeRange:
                aoeRange += upgradeValue.doubleValue();
                break;
            case Speed:
                maxCooldown -= upgradeValue.doubleValue();
            default:
                break;
        }
        upgrades.add(upgrade);
    }

    // ----------------------------Cooldown methods----------------------//
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

    // ----------------------------Animation methods----------------------//

    public void updateAnimationTick() {
        animationTick++;
        if (animationTick >= 10) {
            animationTick = 0;
            
            if (enemiesInRange) {
                incrementAnimationIndex();
            } else {
                resetAnimation();
            }
        }
    }

    /*
     * Updates animationIndex
     */
    public void incrementAnimationIndex() {
        int spritesInAnimation = 4;
        animationIndex++;
        if (animationIndex >= spritesInAnimation) {
            animationIndex = 0;
            enemiesInRange = false;
        }
    }

    public void resetAnimation() {
        int spritesInAnimation = 4;
        if (animationIndex != 0) {
            animationIndex = 0;
            if (animationIndex >= spritesInAnimation) {
                animationIndex = 0;
            }
        }
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

    // ----------------------------Getters and setters----------------------//

    public TowerType getTowerType() {
        return towerType;
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

    public TargetType[] getTargetTypes() {
        return targetType;
    }

    public void setRange(double range) {
        this.range = range;
    }

    protected boolean hasUpgrade(Upgrade targetUpgrade) {
        return upgrades.contains(targetUpgrade);
    }

    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    public int getMaxCooldown() {
        return maxCooldown;
    }

    protected double getAoeRange() {
        return aoeRange;
    }

    protected void setAoeRange(double aoeRange) {
        this.aoeRange = aoeRange;
    }

    public Point2D.Double getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Point2D.Double point) {
        targetPosition = point;
    }

    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    public int getAnimationIndex(){
        return animationIndex;
    }

    // ------------------- Targeting methods that use ITargetable
    // ------------------------//

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
        int targetsFound = 0;
        for (ITargetable targetable : targetables) {
            if (inRangeOf((ITargetable) this, targetable, range)) {
                targets.add(targetable);
                targetsFound++;
                if (targetsFound == 1) {
                    targets.addAll(findAoeTargets(targetable, targetables));
                }
                if (targetsFound == nTargets) {
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
