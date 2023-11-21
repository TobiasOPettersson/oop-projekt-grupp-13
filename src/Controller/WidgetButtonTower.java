package src.Controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;

public class WidgetButtonTower extends JPanel {

    private BufferedImage image;
    private JLabel labelTower;
    private JLabel labelCost;

    public WidgetButtonTower(BufferedImage image, int cost, String towerName) {
        this.image = image;
        this.labelTower = new JLabel(towerName);
        this.labelCost = new JLabel(Integer.toString(cost));
    }

}
