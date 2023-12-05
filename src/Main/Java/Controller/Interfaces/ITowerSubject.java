package Controller.Interfaces;

import Model.Towers.TowerType;

public interface ITowerSubject {
    /**
     * Notifies the observers when a tower is to be created or upgraded
     * @param towerType is the type of the tower
     */
    void notifyObservers(TowerType towerType);
}
