package Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;

import Controller.Interfaces.ITowerObserver;
import Model.Towers.TowerType;

public class CreateTowerController extends TowerController{
    
    /**
     * Constructor for the Shop Widget
     * @param observer is the Map which is notified when the player wants to create a tower
     */
    public CreateTowerController(ITowerObserver observer) {
        super(observer);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(0, 5, 10, 20));
        setPreferredSize(new Dimension(300, 100));
        initTitle();
        initButtons();
    }

    /**
     * Initializes the title label
     */
    private void initTitle(){
        JLabel label = new JLabel("CREATE TOWERS");
        label.setForeground(Color.BLACK);
        add(label, BorderLayout.EAST);
    }


        /**
     * When a button is clicked this method calls notifyObservers
     */
    @Override
     public void handleButtonClick(TowerType type) {
        notifyObservers(type);
    }

    /**
     * Initializes the buttons that the player click on when they want to create a tower
     */
    private void initButtons(){
        add(new WidgetButtonTower(1, TowerType.knife, this));
        add(new WidgetButtonTower(3, TowerType.mallet, this));
        add(new WidgetButtonTower(4, TowerType.blowtorch, this));
        add(new WidgetButtonTower(2, TowerType.slicer, this));
    }

    /**
     * Notifies the observer (Map) that the player wants to create a tower of type towerType
     */
    @Override
    public void notifyObservers(TowerType towerType) {
        getObserver().createTower(getSavedMousePosX(), getSavedMousePosY(), towerType);
    }
}