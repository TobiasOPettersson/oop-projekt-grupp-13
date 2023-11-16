package src.Controller;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import src.Model.MainModel;
import src.View.GameView;

public class tempMainController {
    public static void main(String[] args) {

        List<JButton> buttons = new ArrayList<>();
        JButton button = new JButton("Tower Button");
        JLabel label = new JLabel("Tower Label");
        JPanel panel = new JPanel();

        // Create CreateTowerController instance with valid parameters
        TowerController towerController = new CreateTowerController(0, 0,
                List.of(button), label, panel);

        // Create towerView instance and use the controller
        towerView towerView = new towerView(towerController);

        JFrame frame = new JFrame("create tower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(20, 20);

        frame.add(towerView);

        frame.setVisible(true);

    }
}
