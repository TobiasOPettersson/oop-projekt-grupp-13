package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Represents a dialog window providing a tutorial for the game "Kitchen Defence".
 * It displays instructions for gameplay mechanics and controls to the user.
 * This dialog contains information about enemy waves, placing utensils, upgrading placed utensils, and starting the game.
 * The dialog includes a "Start Game" button to close the tutorial and begin gameplay.
 */
public class ShowTutorialDialog extends JDialog {

    /**
     * Constructor for ShowTutorialDialog
     * @param gameView The main JFrame of the game
     */
    public ShowTutorialDialog(JFrame gameView) {
        super(gameView, "", true);
        initializeDialog();
        setupUI();
    }

    /**
     * Initializes the dialog settings
     */
    private void initializeDialog() {
        setSize(500, 350);
        setLocationRelativeTo(getParent());
        setUndecorated(true);
        setBackground(new Color(255, 255, 255, 150));
    }

    /**
     * Creates the inner panel containing tutorial information
     * @return The inner JPanel containing tutorial content
     */
    private JPanel createInnerPanel() {
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        JLabel tutorialHeader = new JLabel("Welcome to Kitchen Defence!");
        tutorialHeader.setFont(new Font("Arial", Font.BOLD, 25));
        innerPanel.add(tutorialHeader);

        String tutorialText = "<html>When you click the button <b>Start Wave</b> enemies will appear on the"
                + "<br>left side of the playing field. They will follow the path to the right side"
                + "<br>and if they get there you will lose health points."
                + "<br>"
                + "<br>A good way to stop the enemies from getting to the right side is to place"
                + "<br>kitchen utensils along the path."
                + "<br>To <b>place a utensil</b>, first left click one of them at the bottom of the screen."
                + "<br>Make sure you have enough money to pay for them!"
                + "<br>Right-click to unselect the utensil."
                + "<br>"
                + "<br>You can left-click on a placed utensil to <b>upgrade it</b> to a faster,"
                + "<br>stronger version."
                + "<br>"
                + "<br>"
                + "<br>"
                + "<br>";
        JLabel tutorialLabel = new JLabel(tutorialText);
        tutorialLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        innerPanel.add(tutorialLabel);

        return innerPanel;
    }

    /**
     * Creates the "Start Game" button
     * @return The "Start Game" JButton
     */
    private JButton createStartGameButton() {
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> dispose());
        return startGameButton;
    }

    /**
     * Creates the outer panel containing the inner panel and the "Start Game" button
     * @param innerPanel      The JPanel containing tutorial content
     * @param startGameButton The JButton to start the game
     * @return                The outer JPanel containing all components
     */
    private JPanel createOuterPanel(JPanel innerPanel, JButton startGameButton) {
        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outerPanel.setBackground(getBackground());
        outerPanel.add(innerPanel);
        outerPanel.add(startGameButton);
        return outerPanel;
    }

    /**
     * Sets up the user interface of the dialog
     */
    private void setupUI() {
        JPanel innerPanel = createInnerPanel();
        JButton startGameButton = createStartGameButton();
        JPanel outerPanel = createOuterPanel(innerPanel, startGameButton);

        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 5, 5));
        setContentPane(outerPanel);
    }
}
