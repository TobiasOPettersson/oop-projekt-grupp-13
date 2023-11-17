package src.Controller;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

class ConcreteTowerController extends TowerController {

    public ConcreteTowerController(int x, int y, List<JButton> buttons, JLabel label, JPanel panel) {
        super(x, y, buttons, label, panel);
    }

    @Override
    public void initTowerController() {
        JLabel label = getLabel();
        label.setForeground(Color.BLACK);

        JPanel buttonPanel = getPanel();
        buttonPanel.setBounds(40, 80, 400, 600);
        buttonPanel.setBackground(Color.BLUE);
        buttonPanel.setLayout(new GridLayout(0, 4, 10, 10));
        buttonPanel.setPreferredSize(new Dimension(300, 200));

        List<JButton> listwithButtons = getButtons();
        buttonPanel.add(label, BorderLayout.SOUTH);
        for (JButton button : listwithButtons) {
            button.setBackground(Color.GREEN);
            System.out.println("inne i knappenloopne");

            button.setBounds(50, 100, 500, 50);

            buttonPanel.add(button);
        }

    }
}
