package src.Controller;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
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
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setBounds(40, 80, 200, 200);

        for (JButton button : buttons) {
            button.setBounds(50, 100, 80, 30);
            button.setBackground(Color.YELLOW);
            buttonPanel.add(button);
        }
        buttonPanel.add(label);
    }
}
