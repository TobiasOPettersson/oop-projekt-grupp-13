package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.Interfaces.ITowerObserver;
import Model.Enums.TowerType;

public class CreateTowerController extends TowerController {
    JLabel coinsLabel;
    List<WidgetButtonTower> buttons;
    PlayButtonController playbutton;
    JPanel buttonPanel = new JPanel();
    JPanel headpanel = new JPanel();

    /**
     * Constructor for the Shop Widget
     * 
     * @param observer is the Map which is notified when the player wants to create
     *                 a tower
     */
    public CreateTowerController(ITowerObserver observer) {
        super(observer);
        setBackground(Color.WHITE);

        buttonPanel.setLayout(new GridLayout(0, 6, 10, 20));

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(300, 100));

        initHeader();
        intiCostAndLife();
        initButtons();
    }

    private void initHeader() {

        headpanel.setBackground(Color.RED);

        headpanel.setPreferredSize(new Dimension(300, 30));
        JLabel titleLabel = new JLabel("CREATE TOWERS");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headpanel.add(titleLabel);

        add(headpanel, BorderLayout.PAGE_START);
    }

    /**
     * Initializes the title label
     */
    private void intiCostAndLife() {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setLayout(new GridLayout(2, 1, 0, 0));
        JLabel titleLabel = new JLabel("CREATE TOWERS");
        coinsLabel = new JLabel("Coins: ");
        titleLabel.setForeground(Color.BLACK);
        coinsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // add(playbutton);
        labelPanel.add(titleLabel);
        labelPanel.add(coinsLabel);
        buttonPanel.add(labelPanel, BorderLayout.EAST);
    }

    /**
     * When a button is clicked this method calls notifyObservers
     * 
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
     * Initializes the buttons that the player click on when they want to create a
     * tower
     */
    private void initButtons() {
        buttons = List.of(
                new WidgetButtonTower(1, TowerType.knife, this),
                new WidgetButtonTower(3, TowerType.mallet, this),
                new WidgetButtonTower(4, TowerType.blowtorch, this),
                new WidgetButtonTower(2, TowerType.slicer, this),
                new WidgetButtonTower(3, TowerType.freezer, this));
        for (WidgetButtonTower button : buttons) {
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);

    }

    /**
     * Notifies the observer (Map) that the player wants to create a tower of type
     * towerType
     * 
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
        for (WidgetButtonTower button : buttons) {
            button.setOpacity(button.getCost() > curMoney);
        }
    }
}