package Controller;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Towers.TowerType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WidgetButtonTower extends JPanel {

    private TowerType type;
    private int cost;

    /**
     * The constructor of the tower widget buttonClick
     * @param cost is the amount of money needed to purchace the tower
     * @param type is the type of the tower, for example knife or mallet
     * @param towerController is the tower controller widgit that the button is added to
     */
    public WidgetButtonTower(int cost, TowerType type, CreateTowerController towerController) {
        this.type = type;
        this.cost = cost;
        
        setSize(new Dimension(100, 200));
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        initTopPanel();
        initBottomPanel();

        /**
         * When clicked the button calls for its tower controller to handle the mouse click
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                towerController.handleButtonClick(type);
            }
        });
    }

    /**
     * Initializes all components of the bottom panel, it contains:
     * An image of the tower
     */
    private void initTopPanel(){
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.orange);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(drawImage(), BorderLayout.CENTER);
        add(topPanel, BorderLayout.CENTER);        
    }

    /**
     * Initializes all components of the bottom panel, it contains:
     * A label of the tower name
     * A label of the tower cost
     */
    private void initBottomPanel(){
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(100, 30));
        bottomPanel.setBackground(Color.PINK);
        bottomPanel.setLayout(new GridLayout(2, 1, 0, 0));
        
        JLabel nameLabel = new JLabel(type.name());
        JLabel costLabel = new JLabel(Integer.toString(cost));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        costLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(nameLabel);
        bottomPanel.add(costLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a JLabel containing an image of the tower, depending on its type
     * @return the JLabel containing the tower image
     */
    private JLabel drawImage(){
        /*switch (type) {
            case knife:
                return new JLabel(new ImageIcon("src\\Controller\\res\\knife-sprite.png"));
            case mallet:
                return new JLabel(new ImageIcon("src\\Controller\\res\\mallet-sprite.png"));
            case blowtorch:
                return new JLabel(new ImageIcon("src\\Controller\\res\\blowtorch-sprite.png"));
            case slicer:
                return new JLabel(new ImageIcon("src\\Controller\\res\\slicer-sprite.png"));
            default:
                return new JLabel(new ImageIcon("src\\Controller\\res\\knife-sprite.png"));
        }*/
        return new JLabel(new ImageIcon("src\\Main\\Java\\Controller\\res\\knife-sprite.png"));
    }
}
