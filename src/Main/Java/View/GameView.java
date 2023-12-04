package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import Controller.CreateTowerController;
import Controller.UpgradeTowerController;
import Controller.Interfaces.IMoneyObserver;
import Controller.Interfaces.ITowerObserver;
import Controller.Interfaces.ITowerSubject;
import Model.MainModel;
import Model.Enums.TowerType;
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
    private Map<TowerType, BufferedImage> towerImageMap = new HashMap<TowerType,BufferedImage>();
    CreateTowerController createWidget;
    List<UpgradeTowerController> upgradeWidgets;

    // Constructor

    public GameView(MainModel model) { // Moved initComponents down so setVisible is done last
        importImg();
        this.model = model;
        this.drawPanel = new DrawPanel(this, model, image, towerImageMap);
        add(drawPanel, BorderLayout.CENTER);
        createWidget = new CreateTowerController(this.model);
        add(createWidget, BorderLayout.SOUTH);
        model.getPlayer().setMoneyObservers(getMoneyObservers());
        initComponents();
    }

    // import sprite sheet
    private void importImg() {
        InputStream is = this.getClass().getResourceAsStream("res/spriteatlas.png");
        InputStream is2 = this.getClass().getResourceAsStream("res/knife2.png");
        InputStream isMallet = this.getClass().getResourceAsStream("res/mallet.png");
        InputStream isBlowtorch = this.getClass().getResourceAsStream("res/blowtorch.png");
        InputStream isSlicer = this.getClass().getResourceAsStream("res/slicer.png");
        InputStream isFridge = this.getClass().getResourceAsStream("res/fridge.png");

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

    public void openWidgit(int x, int y) {
        if (model.getMap().getTile(x, y).placeable) {
            createWidget.setVisible(true);
            createWidget.setSavedMousePos(x, y);
        } else {
            for (UpgradeTowerController upgradeWidget : upgradeWidgets) {
                TowerType type = ((TowerTile) model.getMap().getTile(x, y)).getTower().getTowerType(); // too much method chaining?
                if (type.equals(upgradeWidget.getTowerType())) {
                    upgradeWidget.setVisible(true);
                    upgradeWidget.setSavedMousePos(x, y);
                }
            }
        }
    }

    // initialize swing window
    private void initComponents() {
        setSize(GraphicsDependencies.getWidth(), GraphicsDependencies.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public List<IMoneyObserver> getMoneyObservers(){
        List<IMoneyObserver> observers = new ArrayList<>();
        if(upgradeWidgets != null){
            for (UpgradeTowerController upWidget : upgradeWidgets) {
                observers.add(upWidget);
            }
        }
        observers.add(createWidget);
        return observers;
    }
}