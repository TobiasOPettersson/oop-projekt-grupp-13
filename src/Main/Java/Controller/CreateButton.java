package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Enums.TowerType;

public class CreateButton extends WidgetButtonTower{

    public CreateButton(int cost, TowerType type, CreateTowerController towerController) {
        super(cost, type, towerController);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                towerController.notifyObservers(type);
            }
        });
    }
}
