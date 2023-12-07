package Controller;

import Model.Enums.TowerType;
import Model.Enums.Upgrade;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpgradeButton extends WidgetButtonTower{
    protected Upgrade upgrade;

    public UpgradeButton(int cost, UpgradeTowerController towerController, Upgrade upgrade, TowerType type) {
        super(cost, type, towerController);
        this.upgrade = upgrade;
        nameLabel.setText(upgrade.name());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                towerController.notifyObservers(upgrade);
            }
        });
    }
}
