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
import src.View.GraphicsDependencies;

public class towerView extends JFrame {

    private TowerController controller;
    // call from this from viewn...
    private GraphicsDependencies borderGraphic;

    public towerView(TowerController controller) {

        this.controller = controller;
        initTowerView();

    }

    // initalize the panel for upgradeTower,createTower etc..
    public void initTowerView() {

        JLabel label = new JLabel("CREATE TOWERS :");
        label.setForeground(Color.BLACK);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setBounds(40, 80, 200, 200);

        // Creating and adding a sample sprite button
        // spriteButtonController spriteButton = new
        // spriteButtonController("path/to/spriteatlas.png");

        buttonPanel.add(label, BorderLayout.SOUTH);

        JButton b1 = new JButton("Archer");
        b1.setBounds(50, 100, 80, 30);
        b1.setBackground(Color.yellow);

        buttonPanel.add(b1, BorderLayout.SOUTH);
        add(buttonPanel);

        setLocationRelativeTo(null);
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
