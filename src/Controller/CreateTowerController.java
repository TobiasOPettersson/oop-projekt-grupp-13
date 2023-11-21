package src.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Model.ATower;

public class CreateTowerController extends TowerController {

    public CreateTowerController(int x, int y, JLabel label) {
        super(x, y, label);
        initTowerController();
        // TODO Auto-generated constructor stub
        // setBackground(Color.BLACK);
    }

    public void initTowerController() {
        System.out.println("inisde inti");
        JLabel label = getLabel();
        label.setForeground(Color.BLACK);

        // JPanel buttonPanel = new JPanel();
        add(label);

        setBackground(Color.GREEN);
        setLayout(new GridLayout(0, 4, 10, 10));
        setPreferredSize(new Dimension(300, 300));

        List<WidgetButtonTower> listwithButtons = new ArrayList<>();

        listwithButtons.add(new WidgetButtonTower(null, 0, "Archer", this));
        listwithButtons.add(new WidgetButtonTower(null, 0, "Wizard", this));
        listwithButtons.add(new WidgetButtonTower(null, 0, "Buffer", this));

        for (WidgetButtonTower widgetButtonTower : listwithButtons) {
            System.out.println("inside for loop");
            add(widgetButtonTower);

        }

        // for (JButton button : listwithButtons) {
        // button.setBackground(Color.WHITE);
        // System.out.println("inne i knappenloopne");
        // button.setPreferredSize(new Dimension(10, 10));
        // buttonPanel.add(button);
        // }

    }

    // method TODO Metbod createTower
    // TODO: Implement tower creation logic here based on the provided coordinates

    // public void createTower(int x, int y, ATower tower) {
    // Example: Creating a tower object and adding it to the game world.
    // ATower newTower = new ATower(x, y, tower);
    // Tell mainModel to create the tower..
    // MainModel.addTower(newTower);
    // }

    public void createTower(String type) {
        // map.createTower(savedMousePosX, savedMousePosY, type);
        // gameView.placeTower(savedMousePosX, savedMousePosY, type);
    }

    @Override
    public void handleButtonClick(String type) {
        createTower(type);
    }

}
