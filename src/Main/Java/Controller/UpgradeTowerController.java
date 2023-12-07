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

import Controller.Interfaces.ITowerUpgradeObserver;
import Controller.Interfaces.IUpgradeTowerSubject;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

public class UpgradeTowerController extends TowerController implements IUpgradeTowerSubject{
    JLabel coinsLabel;
    List<WidgetButtonTower> buttons;
    PlayButtonController playbutton;
    JPanel buttonPanel = new JPanel();
    JPanel headpanel = new JPanel();
    TowerType towerType;
    private ITowerUpgradeObserver observer;
    private int savedMousePosX;
    private int savedMousePosY;

    public UpgradeTowerController(ITowerUpgradeObserver observer, TowerType towerType) {
        this.observer = observer;
        this.towerType = towerType;
        setBackground(Color.WHITE);

        buttonPanel.setLayout(new GridLayout(0, 6, 10, 20));

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(300, 300));

        initHeader();
        intiCostAndLife();
        initButtons();
    }

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
     * Initializes the title label
     */
    private void intiCostAndLife() {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setLayout(new GridLayout(3, 1, 0, 0));
        JLabel titleLabel = new JLabel("UPGRADE TOWERS");
        coinsLabel = new JLabel("Coins: ");
        titleLabel.setForeground(Color.BLACK);
        coinsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // add(playbutton);
        labelPanel.add(titleLabel);
        labelPanel.add(coinsLabel);
        buttonPanel.add(labelPanel, BorderLayout.EAST);
    }

    private void initButtons() {
        switch (towerType) {
            case knife:
                buttons = List.of(
                    new UpgradeButton(1, this, Upgrade.Damage, towerType),
                    new UpgradeButton(3, this, Upgrade.Speed, towerType),
                    new UpgradeButton(4, this, Upgrade.Targets, towerType),
                    new UpgradeButton(2, this, Upgrade.Range, towerType));                
                break;
            case mallet:
                buttons = List.of(
                    new UpgradeButton(1, this, Upgrade.Damage, towerType),
                    new UpgradeButton(3, this, Upgrade.Damage2, towerType),
                    new UpgradeButton(4, this, Upgrade.AoeRange, towerType),
                    new UpgradeButton(2, this, Upgrade.Range, towerType));                
                break;
            case blowtorch:
                buttons = List.of(
                    new UpgradeButton(1, this, Upgrade.Damage, towerType),
                    new UpgradeButton(3, this, Upgrade.Range, towerType),
                    new UpgradeButton(4, this, Upgrade.AoeRange, towerType),
                    new UpgradeButton(2, this, Upgrade.SetOnFire, towerType));    
                break;
            case slicer:
                buttons = List.of(
                    new UpgradeButton(1, this, Upgrade.Damage, towerType),
                    new UpgradeButton(3, this, Upgrade.Damage2, towerType),
                    new UpgradeButton(4, this, Upgrade.AoeRange, towerType));
                break;
            case freezer:
                buttons = List.of(
                    new UpgradeButton(1, this, Upgrade.Frostbite, towerType),
                    new UpgradeButton(3, this, Upgrade.SuperChill, towerType),
                    new UpgradeButton(4, this, Upgrade.ConditionDuration, towerType),
                    new UpgradeButton(2, this, Upgrade.Range, towerType));
                break;
            default:
                break;
        }
        
        for (WidgetButtonTower button : buttons) {
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    public TowerType getTowerType() {
        return towerType;
    }

    @Override
    public void notifyObservers(Upgrade upgrade) {
        for (WidgetButtonTower button : buttons) {
            if(upgrade == ((UpgradeButton)button).upgrade){
                button.setOpacity(Color.blue, true);
            }
        }
        observer.upgradeTower(getSavedMousePosX(), getSavedMousePosY(), upgrade);
    }

    /**
     * Called whenever the players bank changes
     * Calls setOpacity(), turns buttons grey if the player cant afford the upgrade
     */
    @Override
    public void updateMoney(int curMoney) {
        coinsLabel.setText("Coins: " + curMoney);
        for (WidgetButtonTower button : buttons) {
            button.setOpacity(Color.gray, button.getCost() > curMoney);
        }
    }

    /**
     * Called whenever the players bank changes
     * Calls setOpacity(), turns buttons grey if the player cant afford the upgrade
     */
    public void updateAvailableUpgrades(List<Upgrade> currentUpgrades) {
        for (WidgetButtonTower button : buttons) {
            if(currentUpgrades.contains(((UpgradeButton)button).upgrade)){
                button.setOpacity(Color.blue, true);
            }
        }
    }

    /**
     * Saves the position of the mouse as grid-indicies
     * @param x is the x-position of the mouse as grid-indicies
     * @param y is the y-position of the mouse as grid-indicies
     */
    public void setSavedMousePos(int x, int y){
        savedMousePosX = x;
        savedMousePosY = y;
    }

    protected int getSavedMousePosY() {
        return savedMousePosY;
    }

    protected int getSavedMousePosX() {
        return savedMousePosX;
    }
}