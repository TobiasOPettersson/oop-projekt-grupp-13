package src.View;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class SpriteRotator {
    public static BufferedImage rotateSprite(BufferedImage originalSprite, int angle){
        int width = originalSprite.getWidth();
        int height = originalSprite.getHeight();

        BufferedImage rotatedSprite = new BufferedImage(height, width, originalSprite.getType());
        Graphics2D g2d = rotatedSprite.createGraphics();

        // Rotate the image 90 degrees
        AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(angle),
                width / 2.0, height / 2.0);
        g2d.setTransform(rotation);

        // Draw the rotated image
        g2d.drawImage(originalSprite, 0, 0, null);
        g2d.dispose();

        return rotatedSprite;
    }
}
