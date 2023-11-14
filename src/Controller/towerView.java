package src.Controller;

import java.awt.Color;
import java.awt.Panel;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import src.Controller.TowerController; // Import the TowerController abstract class

public class towerView extends JFrame {

    private TowerController controller;

    public towerView(TowerController controller) {

        this.controller = controller;
        initTowerView();

    }

    // initalize the panel for upgradeTower,createTower etc..
    public void initTowerView() {
        JPanel panel = controller.getPanel();
        // jsut a dummy size, have to adapt..
        panel.setSize(100, 100);
        panel.setBackground(Color.black);
        panel.setVisible(true);

        add(panel);

        pack(); // Adjusts the frame size based on the components added
        setLocationRelativeTo(null); // Centers the frame on the screen
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // Make the frame visible

    }

    // methods for a label/buttons..

}
