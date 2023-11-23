package src.Controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class WidgetButtonTower extends JPanel {

    private BufferedImage image;
    private JLabel labelTower;
    private JLabel labelCost;
    private TowerController towerControlller;

    public WidgetButtonTower(BufferedImage image, int cost, String towerName,
            CreateTowerController createTowerController) {
        this.image = image;
        this.labelTower = new JLabel(towerName);
        this.labelCost = new JLabel(Integer.toString(cost));
        this.towerControlller = towerControlller;

        setSize(new Dimension(100, 200));
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        System.out.println("inside WidgebuttonController");
        labelTower.setHorizontalAlignment(SwingConstants.CENTER);
        labelCost.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(50, 100));
        bottomPanel.setBackground(Color.PINK);
        bottomPanel.setLayout(new GridLayout(2, 1, 0, 0));
        bottomPanel.add(labelTower);
        bottomPanel.add(labelCost);
        add(bottomPanel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                towerControlller.handleButtonClick(labelTower.getText());
            }
        });
    }
}
