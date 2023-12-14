package Controller;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Enums.TowerType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

public abstract class AWidgetButton extends JPanel {
    protected JPanel topPanel;
    protected JPanel bottomPanel;
    protected JLabel nameLabel;
    private int cost;
    protected TowerType type;
    protected Map<TowerType, String> buttonImgPaths = new HashMap<>();

    /**
     * The constructor of the tower widget buttonClick
     * 
     * @param cost            is the amount of money needed to purchace the tower
     * @param type            is the type of the tower, for example knife or mallet
     * @param towerController is the tower controller widgit that the button is
     *                        added to
     */
    public AWidgetButton(int cost, TowerType type, AShopWidgetController towerController) {
        this.cost = cost;
        this.type = type;
        setSize(new Dimension(100, 200));
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        initButtonImagePaths();
        initTopPanel();
        initBottomPanel();
        setOpacity(true);
    }

    /**
     * Initializes the map containing the pats of button images
     */
    protected abstract void initButtonImagePaths();

    /**
     * Initializes all components of the top panel, it contains:
     * An image of the tower
     */
    private void initTopPanel() {
        topPanel = new JPanel();
        JLabel towerImageLabel = new JLabel(new ImageIcon(buttonImgPaths.get(type)));
        topPanel.setBackground(Color.orange);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(towerImageLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.CENTER);
    }

    /**
     * Initializes all components of the bottom panel, it contains:
     * A label of the tower name
     * A label of the tower cost
     */
    private void initBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(100, 180));
        bottomPanel.setBackground(Color.PINK);
        bottomPanel.setLayout(new GridLayout(3, 1, 0, 0));

        String typeName = Character.toUpperCase(type.name().charAt(0)) + type.name().substring(1);
        nameLabel = new JLabel(typeName);
        JLabel costLabel = new JLabel(Integer.toString(cost));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        costLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(nameLabel);
        bottomPanel.add(costLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets the opacity of the widget components.
     * 
     * @param bool true to set the widget and its components as opaque,
     *             false to set them with transparency!
     */
    public void setOpacity(boolean bool) {
        Color color = new Color(0, 0, 0, 150);

        if (bool) {
            setOpaque(false);
            setBackground(color);
            topPanel.setBackground(color);
            bottomPanel.setBackground(color);
        } else {
            setOpaque(true);
            setBackground(Color.gray);
            topPanel.setBackground(Color.orange);
            bottomPanel.setBackground(Color.pink);
        }
    }

    protected int getCost() {
        return cost;
    }
}
