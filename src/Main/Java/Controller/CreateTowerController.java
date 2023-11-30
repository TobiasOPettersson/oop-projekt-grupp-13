package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import Model.TowerType;

public class CreateTowerController extends TowerController {

    public CreateTowerController(ITowerObserver observer) {
        super(observer);
        initTowerController();
    }

    public void initTowerController() {
        JLabel label = new JLabel("CREATE TOWERS");
        // System.out.println("inisde inti"); //DEL
        label.setForeground(Color.BLACK);

        add(label, BorderLayout.EAST);

        setBackground(Color.WHITE);
        setLayout(new GridLayout(0, 5, 10, 20));
        setPreferredSize(new Dimension(300, 100));

        List<WidgetButtonTower> listwithButtons = new ArrayList<>();

        listwithButtons.add(new WidgetButtonTower(null, 1, "Knife", TowerType.knife, this));
        listwithButtons.add(new WidgetButtonTower(null, 3, "Mallet", TowerType.mallet, this));
        listwithButtons.add(new WidgetButtonTower(null, 4, "Blowtorch", TowerType.blowtorch, this));
        listwithButtons.add(new WidgetButtonTower(null, 2, "Slicer", TowerType.slicer, this));

        for (WidgetButtonTower widgetButtonTower : listwithButtons) {

            add(widgetButtonTower);

        }

        // for (JButton button : listwithButtons) {
        // button.setBackground(Color.WHITE);
        // System.out.println("inne i knappenloopne");
        // button.setPreferredSize(new Dimension(10, 10));
        // buttonPanel.add(button);
        // }

    }

    @Override
    public void handleButtonClick(TowerType type) {
        notifyObservers(type);
    }

    @Override
    public void notifyObservers(TowerType towerType) {
        observer.createTower(savedMousePosX, savedMousePosY, towerType);
    }
}