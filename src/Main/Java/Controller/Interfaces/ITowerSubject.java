package Controller.Interfaces;

import Model.Enums.TowerType;

public interface ITowerSubject {
    /**
     * Notifies the observers when a tower is to be created or upgraded
     * @param towerType is the type of the tower
     * @throws Exception
     */
    void notifyObservers(TowerType towerType) throws Exception;
}
