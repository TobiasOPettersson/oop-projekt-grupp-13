package Controller.Interfaces;

import Model.Towers.TowerType;

public interface ITowerSubject {
    void notifyObservers(TowerType towerType);
}
