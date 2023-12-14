package Controller;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.Interfaces.IMoneyObserver;
import Model.MainModel;
import View.GraphicsDependencies;

/**
 * AShopWidgetController is an abstract class representing a widget controller
 * responsible for managing the user interface elements within the shop,
 * including
 * the creation and display of buttons, header initialization, and overall
 * layout
 * configuration.
 */

public abstract class AShopWidgetController extends JPanel implements IMoneyObserver {
    private final int MAP_HEIGHT = 480;
    private final int FRAME_HEIGHT = GraphicsDependencies.getHeight();
    protected MainModel model;

    protected PlayButtonController playbutton;
    protected JPanel buttonPanel = new JPanel();
    protected List<AWidgetButton> buttons;
    protected JPanel headpanel = new JPanel();

    /**
     * Constructor of the abstract Tower Controller class
     * 
     * @param observer is the Map which is notified when the player wants to create
     *                 a tower
     */
    public AShopWidgetController(MainModel model) {
        setSize(new Dimension(600, FRAME_HEIGHT - MAP_HEIGHT));
        setVisible(true);
        this.model = model;

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 300));
    }

    /**
     * Initializes the Playbutton label
     */
    protected void initPlaybutton() {
        playbutton = new PlayButtonController(model);
        buttonPanel.add(playbutton);
    }

    /**
     * Initializes the Header of the CreateTowerController
     * 
     * @param titleString - to initilize the titel of the header.
     */
    protected void initHeader(String titleString) {
        headpanel.setBackground(Color.gray);
        headpanel.setPreferredSize(new Dimension(300, 25));

        JLabel titleLabel = new JLabel(titleString);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headpanel.add(titleLabel);
        add(headpanel, BorderLayout.PAGE_START);
    }
}