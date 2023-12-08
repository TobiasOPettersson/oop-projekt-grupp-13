package Controller.Interfaces;

import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public interface IUpgradeTowerSubject {
    /**
     * Notifies the observers when a tower is to be created or upgraded
     * @param towerType is the type of the tower
     * @throws Exception
     */
    void notifyObservers(Upgrade upgrade) throws Exception;
}
