package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import Controller.CreateTowerController;
import Controller.UpgradeTowerController;
import Controller.Interfaces.IMoneyObserver;
import Controller.Interfaces.ITowerUpgradeObserver;
import Controller.Interfaces.IUpgradeTowerSubject;
import Model.MainModel;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Map.AMap;
import Model.Map.TowerTile;
import Model.Towers.ATower;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class GameView extends JFrame {
    MainModel model;
    DrawPanel drawPanel;
    private BufferedImage image;
    private Map<TowerType, BufferedImage> towerImageMap = new HashMap<TowerType, BufferedImage>();
    CreateTowerController createWidgit;
    List<UpgradeTowerController> upgradeWidgits;

    // Constructor

    public GameView(MainModel model) { // Moved initComponents down so setVisible is done last
        importImg();
        this.model = model;
        this.drawPanel = new DrawPanel(this, model, image, towerImageMap);
        setLayout(null);
        drawPanel.setBounds(0, 0, 960, 480);
        add(drawPanel);
        initWidgits();
        initComponents();
    }

    private void initWidgits(){
        upgradeWidgits = List.of(
            new UpgradeTowerController(model, TowerType.knife),
            new UpgradeTowerController(model, TowerType.mallet),
            new UpgradeTowerController(model, TowerType.blowtorch),
            new UpgradeTowerController(model, TowerType.slicer),
            new UpgradeTowerController(model, TowerType.freezer)
        );

        for(UpgradeTowerController upgradeWidgit : upgradeWidgits){
            upgradeWidgit.setBounds(0, 480, 960, 300);
            add(upgradeWidgit);
            upgradeWidgit.setVisible(false);
        }

        createWidgit = new CreateTowerController(drawPanel);
        createWidgit.setBounds(0, 480, 960, 300);
        add(createWidgit);
        createWidgit.setVisible(true);

        model.getPlayer().setMoneyObservers(getMoneyObservers());
    }

    // import sprite sheet
    private void importImg() {
        InputStream is = this.getClass().getResourceAsStream("res1/spriteatlas.png");
        InputStream is2 = this.getClass().getResourceAsStream("res1/knife2.png");
        InputStream isMallet = this.getClass().getResourceAsStream("res1/mallet.png");
        InputStream isBlowtorch = this.getClass().getResourceAsStream("res1/blowtorch.png");
        InputStream isSlicer = this.getClass().getResourceAsStream("res1/slicer.png");
        InputStream isFridge = this.getClass().getResourceAsStream("res1/fridge.png");

        try {
            image = ImageIO.read(is);
            towerImageMap.put(TowerType.knife, ImageIO.read(is2));
            towerImageMap.put(TowerType.mallet, ImageIO.read(isMallet));
            towerImageMap.put(TowerType.blowtorch, ImageIO.read(isBlowtorch));
            towerImageMap.put(TowerType.slicer, ImageIO.read(isSlicer));
            towerImageMap.put(TowerType.freezer, ImageIO.read(isFridge));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        drawPanel.update();
    }

    public void openUpgradeWidgit(int x, int y, TowerType type, List<Upgrade> currentUpgrades) {
        createWidgit.setVisible(true);
        for (UpgradeTowerController upgradeWidget : upgradeWidgits) {
            if (type.equals(upgradeWidget.getTowerType())) {
                upgradeWidget.setVisible(true);
                upgradeWidget.setSavedMousePos(x, y);
                upgradeWidget.updateAvailableUpgrades(currentUpgrades);
            }
        }
    }

    public void openCreateWidgit(){
        createWidgit.setVisible(true);
        for (UpgradeTowerController upgradeWidget : upgradeWidgits) {
            upgradeWidget.setVisible(false);
        }
    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    // initialize swing window
    private void initComponents() {
        setSize(GraphicsDependencies.getWidth(), GraphicsDependencies.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public List<IMoneyObserver> getMoneyObservers() {
        List<IMoneyObserver> observers = new ArrayList<>();
        if (upgradeWidgits != null) {
            for (UpgradeTowerController upWidget : upgradeWidgits) {
                observers.add(upWidget);
            }
        }
        observers.add(createWidgit);
        return observers;
    }
}