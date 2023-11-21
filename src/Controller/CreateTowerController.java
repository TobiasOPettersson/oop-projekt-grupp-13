package src.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import src.Model.AMap;

public class CreateTowerController extends TowerController {

    public CreateTowerController(AMap map, int x, int y, List<JButton> buttons, JLabel label, JPanel panel) {
        super(map, x, y, buttons, label, panel);
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

    public void createTower(int x, int y, String type) {
        map.createTower(x, y, type);
    }

    public void createTower(String type) {
        // map.createTower(savedMousePosX, savedMousePosY, type);
        // gameView.placeTower(savedMousePosX, savedMousePosY, type);
    }

    @Override
    public void handleButtonClick(String type) {
        createTower(type);
    }

}
