import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class spriteButtonController extends JButton {

    private BufferedImage spriteImage;

    public spriteButtonController(String imagePath) {
        setOpaque(false); // Make the button transparent
        setContentAreaFilled(false); // Don't paint the content area
        setBorderPainted(false); // Remove the border

        try {
            spriteImage = ImageIO.read(new File(imagePath)); // Load sprite image from file
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (spriteImage != null) {
            int x = (getWidth() - spriteImage.getWidth()) / 2;
            int y = (getHeight() - spriteImage.getHeight()) / 2;
            g.drawImage(spriteImage, x, y, this);
        }
    }
}

// top at center..
//