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

import Controller.Interfaces.ICreateTowerSubject;
import Model.MainModel;
import Model.Enums.TowerType;
import View.ICreateTowerObserver;

public class CreateTowerController extends TowerController implements ICreateTowerSubject {
    ICreateTowerObserver observer;
    // JLabel coinsLabel;
    List<WidgetButton> buttons;
    PlayButtonController playbutton;
    JPanel buttonPanel = new JPanel();
    JPanel headpanel = new JPanel();

    /**
     * Constructor for the Shop Widget
     * 
     * @param observer The DrawPanel that is notified when the player wants to
     *                 create a tower
     */
    public CreateTowerController(ICreateTowerObserver observer, MainModel model) {
        super(model);

        this.observer = observer;
        this.model = model;

        setBackground(Color.WHITE);
        buttonPanel.setLayout(new GridLayout(0, 6, 5, 10));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 300));

        initHeader();
        initButtons();
        intiPlaybutton();
    }

    /**
     * Initializes the Header of the CreateTowerController
     */
    private void initHeader() {
        headpanel.setBackground(Color.gray);
        headpanel.setPreferredSize(new Dimension(300, 25));

        JLabel titleLabel = new JLabel("CREATE TOWERS");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headpanel.add(titleLabel);
        add(headpanel, BorderLayout.PAGE_START);
    }

    /**
     * Initializes the Playbutton label
     */
    private void intiPlaybutton() {
        playbutton = new PlayButtonController(model);
        buttonPanel.add(playbutton);
    }

    /**
     * Initializes the buttons that the player click on when they want to create a
     * tower
     */
    private void initButtons() {
        buttons = List.of(
                new CreateButton(1, TowerType.knife, this),
                new CreateButton(3, TowerType.mallet, this),
                new CreateButton(4, TowerType.blowtorch, this),
                new CreateButton(2, TowerType.slicer, this),
                new CreateButton(3, TowerType.freezer, this));
        for (WidgetButton button : buttons) {
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Notifies the observer (DrawPanel) that the player wants to create a tower of
     * type towerType
     * 
     * @throws Exception if the player doesn't have enough money to buy the tower
     */
    @Override
    public void notifyObservers(TowerType towerType) {
        try {
            observer.selectTowerToCreate(towerType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called whenever the players bank changes
     * Calls setOpacity(), turns buttons grey if the player cant afford the tower
     */
    @Override
    public void updateMoney(int curMoney) {
        // coinsLabel.setText("Coins: " + curMoney);
        for (WidgetButton button : buttons) {
            button.setOpacity(button.getCost() > curMoney);
        }
    }
}