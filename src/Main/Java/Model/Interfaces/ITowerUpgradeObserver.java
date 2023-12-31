package Model.Interfaces;

import Model.Enums.Upgrade;

public interface ITowerUpgradeObserver {
    /**
     * Upgrades a tower
     * @param x is the x-position of the tile where the tower is currently placed
     * @param y is the y-position of the tile where the tower is currently placed
     * @param upgradeLvl is an int representing a specific upgrade
     */
    void upgradeTower(int x, int y, Upgrade upgrade, int cost) throws Exception;
}