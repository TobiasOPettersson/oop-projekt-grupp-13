package Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.Interfaces.ITowerObserver;
import Model.Enums.TowerType;
import Model.Interfaces.IMoneySubject;

public class CreateTowerController extends TowerController{
    JLabel coinsLabel;

    /**
     * Constructor for the Shop Widget
     * @param observer is the Map which is notified when the player wants to create a tower
     */
    public CreateTowerController(ITowerObserver observer) {
        super(observer);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(0, 6, 10, 20));
        setPreferredSize(new Dimension(300, 100));
        initTitle();
        initButtons();
    }

    /**
     * Initializes the title label
     */
    private void initTitle(){
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setLayout(new GridLayout(2, 1, 0, 0));
        JLabel titleLabel = new JLabel("CREATE TOWERS");
        coinsLabel = new JLabel("Coins: ");
        titleLabel.setForeground(Color.BLACK);
        coinsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        labelPanel.add(titleLabel);
        labelPanel.add(coinsLabel);
        add(labelPanel, BorderLayout.EAST);
    }


    /**
     * When a button is clicked this method calls notifyObservers
     * @throws Exception
     */
    @Override
     public void handleButtonClick(TowerType type) {
        try {
            notifyObservers(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the buttons that the player click on when they want to create a tower
     */
    private void initButtons(){
        add(new WidgetButtonTower(1, TowerType.knife, this));
        add(new WidgetButtonTower(3, TowerType.mallet, this));
        add(new WidgetButtonTower(4, TowerType.blowtorch, this));
        add(new WidgetButtonTower(2, TowerType.slicer, this));
        add(new WidgetButtonTower(3, TowerType.freezer, this));
    }

    /**
     * Notifies the observer (Map) that the player wants to create a tower of type towerType
     * @throws Exception if the player doesn't have enough money to buy the tower
     */
    @Override
    public void notifyObservers(TowerType towerType) {
        try {
            getObserver().createTower(getSavedMousePosX(), getSavedMousePosY(), towerType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMoney(int curMoney) {
        coinsLabel.setText("Coins: " + curMoney);
    }
}