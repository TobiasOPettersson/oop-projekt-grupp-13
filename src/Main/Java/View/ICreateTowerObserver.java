package View;

import Model.Enums.TowerType;

public interface ICreateTowerObserver {

    /**
     * Creates a tower
     * @param x is the x-position of the tile where the tower will be placed
     * @param y is the y-position of the tile where the tower will be placed
     * @param type is the type of the tower
     * @throws Exception
     */

    void selectTowerToCreate(TowerType type);
}
