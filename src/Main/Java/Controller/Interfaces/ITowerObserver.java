package Controller.Interfaces;
import Model.Enums.TowerType;

public interface ITowerObserver {
    /**
     * Creates a tower
     * @param x is the x-position of the tile where the tower will be placed
     * @param y is the y-position of the tile where the tower will be placed
     * @param type is the type of the tower
     * @throws Exception
     */
    void createTower(int x, int y, TowerType type) throws Exception;

    /**
     * Upgrades a tower
     * @param x is the x-position of the tile where the tower is currently placed
     * @param y is the y-position of the tile where the tower is currently placed
     * @param upgradeLvl is an int representing a specific upgrade
     */
    void upgradeTower(int x, int y, int upgradeLvl);
}