package Controller;

import Model.TowerType;

public interface ITowerSubject {
    void notifyObservers(TowerType towerType);
}
