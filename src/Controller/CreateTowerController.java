package src.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Model.ATower;

public class CreateTowerController extends TowerController {

    public CreateTowerController(int x, int y, List<JButton> buttons, JLabel label, JPanel panel) {
        super(x, y, buttons, label, panel);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void initTowerController() {
        JLabel label = getLabel();
        label.setForeground(Color.BLACK);

        JPanel buttonPanel = getPanel();

        buttonPanel.setBackground(Color.BLUE);
        buttonPanel.setLayout(new GridLayout(0, 4, 10, 10));
        buttonPanel.setPreferredSize(new Dimension(300, 200));

        List<JButton> listwithButtons = getButtons();
        buttonPanel.add(label, BorderLayout.CENTER);
        for (JButton button : listwithButtons) {
            button.setBackground(Color.WHITE);
            System.out.println("inne i knappenloopne");
            button.setPreferredSize(new Dimension(10, 10));
            buttonPanel.add(button);

        }

    }

    // method TODO Metbod createTower
    // TODO: Implement tower creation logic here based on the provided coordinates

    // public void createTower(int x, int y, ATower tower) {
    // Example: Creating a tower object and adding it to the game world.
    // ATower newTower = new ATower(x, y, tower);
    // Tell mainModel to create the tower..
    // MainModel.addTower(newTower);
    // }

}
