package src.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import src.Controller.TowerController; // Import the TowerController abstract class
import src.Model.MainModel;

public class towerView extends JFrame {

    private TowerController controller;

    public towerView(TowerController controller) {

        this.controller = controller;
        initTowerView();

    }

    // initalize the panel for upgradeTower,createTower etc..
    public void initTowerView() {

        JLabel label = new JLabel("CREATE TOWERS :");
        label.setForeground(Color.BLACK);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(400, 400);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT); // Create a FlowLayout instance
        flowLayout.setHgap(100); // Set the horizontal gap (space between components) to 10 pixels
        buttonPanel.setLayout(flowLayout); // Apply the modified FlowLayout to the panel

        buttonPanel.add(label);
        // buttonPanel.setPreferredSize(new Dimension(190, 190));

        for (int i = 1; i <= 3; i++) {
            JButton button = new JButton("Button " + i);
            buttonPanel.add(button);

        }
        buttonPanel.setPreferredSize(new Dimension(50, 50));

        // mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    // method to updateLabel
    public void updateLabel(String text) {
        JLabel label = controller.getLabel();
        label.setText(text);
    }

    // method to add buttons to the panel.
    public void addButton(JButton button) {
        JPanel panel = controller.getPanel();
        panel.add(button);
    }

}
