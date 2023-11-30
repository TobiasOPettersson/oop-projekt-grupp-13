package Controller;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Model.Towers.TowerType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class WidgetButtonTower extends JPanel {

    private BufferedImage image;
    private JLabel labelTower;
    private JLabel labelCost;
    private TowerController towerController;
    private TowerType type;

    public WidgetButtonTower(BufferedImage image, int cost, String towerName, TowerType type,
            CreateTowerController towerController) {
        this.image = image;
        this.labelTower = new JLabel(towerName);
        this.labelCost = new JLabel(Integer.toString(cost));
        this.towerController = towerController;

        setSize(new Dimension(100, 200));
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        // System.out.println("inside WidgebuttonController"); // DEL
        labelTower.setHorizontalAlignment(SwingConstants.CENTER);
        labelCost.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(50, 100));
        bottomPanel.setBackground(Color.PINK);
        bottomPanel.setLayout(new GridLayout(2, 1, 0, 0));
        bottomPanel.add(labelTower);
        bottomPanel.add(labelCost);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(50, 100));
        topPanel.setBackground(Color.orange);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(drawImage(), BorderLayout.CENTER);
        add(topPanel, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                towerController.handleButtonClick(type);
            }
        });
    }

    private JLabel drawImage(){
        /*switch (type) {
            case knife:
                return new JLabel(new ImageIcon("src\\Controller\\res\\knife-sprite.png"));
            case mallet:
                return new JLabel(new ImageIcon("src\\Controller\\res\\mallet-sprite.png"));
            case blowtorch:
                return new JLabel(new ImageIcon("src\\Controller\\res\\blowtorch-sprite.png"));
            case slicer:
                return new JLabel(new ImageIcon("src\\Controller\\res\\slicer-sprite.png"));
            default:
                return new JLabel(new ImageIcon("src\\Controller\\res\\knife-sprite.png"));
        }*/
        return new JLabel(new ImageIcon("src\\Controller\\res\\knife-sprite.png"));
    }
}
