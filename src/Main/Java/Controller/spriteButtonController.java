package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class SpriteButtonController extends JButton {
/* 
    private BufferedImage spriteImage;
    private boolean isVisible;

    public SpriteButtonController() {
        super(); // Call the superclass constructor

        setPreferredSize(new Dimension(100, 100)); // Set preferred button size

        try {
            URL imageUrl = new URL("res/resImagesprite_39.png"); // Replace with your image URL
            spriteImage = ImageIO.read(imageUrl);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isVisible) {
                    setIcon(new ImageIcon(spriteImage)); // Set sprite image when clicked
                } else {
                    setIcon(null); // Remove image when made invisible
                }
                isVisible = !isVisible; // Toggle visibility
            }
        });
    }*/
}
