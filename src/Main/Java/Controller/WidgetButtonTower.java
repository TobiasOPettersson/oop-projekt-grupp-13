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
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class WidgetButtonTower extends JPanel {
    JPanel topPanel;
    JPanel bottomPanel;
    private TowerType type;
    private int cost;
    private Map<TowerType, String> buttonImgPaths = new HashMap<>();

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
        initButtonImagePaths();
        initTopPanel();
        initBottomPanel();
        setOpacity(true);
       

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
     * Initializes the map containing the pats of button images
     */
    private void initButtonImagePaths(){
        String resPath = "src\\Main\\Java\\Controller\\res\\";
        buttonImgPaths.put(TowerType.knife, resPath + "knife.png");
        buttonImgPaths.put(TowerType.mallet, resPath + "mallet.png");
        buttonImgPaths.put(TowerType.blowtorch, resPath + "blowtorch.png");
        buttonImgPaths.put(TowerType.slicer, resPath + "slicer.png");
        buttonImgPaths.put(TowerType.freezer, resPath + "fridge.png");
    }


    /**
     * Initializes all components of the bottom panel, it contains:
     * An image of the tower
     */
    private void initTopPanel(){
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
    private void initBottomPanel(){
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(100, 30));
        bottomPanel.setBackground(Color.PINK);
        bottomPanel.setLayout(new GridLayout(2, 1, 0, 0));
        
        String typeName = Character.toUpperCase(type.name().charAt(0)) + type.name().substring(1);
        JLabel nameLabel = new JLabel(typeName);
        JLabel costLabel = new JLabel(Integer.toString(cost));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        costLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(nameLabel);
        bottomPanel.add(costLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setOpacity(boolean bool){
        if(bool){
            setOpaque(false);
            setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            topPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            bottomPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        } else{
            setOpaque(true);
            setBackground(Color.gray);
            topPanel.setBackground(Color.orange);
            bottomPanel.setBackground(Color.pink);
        }
    }

    protected int getCost(){
        return cost;
    }
}
