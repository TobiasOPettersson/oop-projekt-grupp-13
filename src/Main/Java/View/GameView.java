package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import Controller.CreateTowerController;
import Controller.UpgradeTowerController;
import Controller.Interfaces.ITowerObserver;
import Controller.Interfaces.ITowerSubject;
import Model.MainModel;
import Model.Map.AMap;
import Model.Map.TowerTile;
import Model.Towers.ATower;
import Model.Towers.TowerType;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

public class GameView extends JFrame {
    MainModel model;
    DrawPanel drawPanel;
    private BufferedImage image;
    private BufferedImage image2;
    CreateTowerController createWidget;
    List<UpgradeTowerController> upgradeWidgets;

    // Constructor

    public GameView(MainModel model) { // Moved initComponents down so setVisible is done last
        importImg();
        this.model = model;
        this.drawPanel = new DrawPanel(this, model, image, image2);
        add(drawPanel, BorderLayout.CENTER);
        createWidget = new CreateTowerController(this.model);
        add(createWidget, BorderLayout.SOUTH);
        initComponents();
    }

    // import sprite sheet
    private void importImg() {
        InputStream is = this.getClass().getResourceAsStream("res1/spriteatlas.png");
        InputStream is2 = this.getClass().getResourceAsStream("res1/knife2.png");

        try {
            image = ImageIO.read(is);
            image2 = ImageIO.read(is2);
        } catch (IOException e) {
            e.printStackTrace();
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
                TowerType type = ((TowerTile) model.getMap().getTile(x, y)).getTower().getTowerType();
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
}