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
import src.View.GraphicsDependencies;

public class tempMainController {
    public static void main(String[] args) {
        GraphicsDependencies g;
        List<JButton> buttons = new ArrayList<>();
        JButton button = new JButton("Tower Button");
        JLabel label = new JLabel("Tower Label");
        JPanel panel = new JPanel();

        TowerController towerController = new CreateTowerController(0, 0, buttons, label, panel);
        towerView towerView = new towerView(towerController);

        JFrame frame = new JFrame("Create Tower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(towerView);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
