package Controller;
import Controller.Interfaces.ITowerObserver;
import Model.Enums.TowerType;

public class UpgradeTowerController extends TowerController{
    TowerType towerType;

    public UpgradeTowerController(ITowerObserver observer, TowerType towerType) {
        super(observer);
        this.towerType = towerType;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    @Override
    public void handleButtonClick(TowerType type) {
        notifyObservers(type);
    }

    @Override
    public void notifyObservers(TowerType towerType) {
        getObserver().upgradeTower(getSavedMousePosX(), getSavedMousePosY(), 0);
    }

    @Override
    public void updateMoney(int curMoney) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMoney'");
    }
}