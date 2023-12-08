package View;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class SpriteHelper {
    // TODO Javadoc comment
    /**
     * 
     * @param originalSprite
     * @param angle
     * @return
     */
    public static BufferedImage rotateSprite(BufferedImage originalSprite, int angle) {
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

    // TODO Javadoc comment
    /**
     * 
     * @param originalSprite
     * @param scale
     * @return
     */
    public static BufferedImage scaleSprite(BufferedImage originalSprite, double scale) {
        int w = (int)(originalSprite.getWidth() * scale);
        int h = (int)(originalSprite.getHeight() * scale);
        BufferedImage scaledSprite = new BufferedImage(w, h, originalSprite.getType());
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp = 
            new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        scaledSprite = scaleOp.filter(originalSprite, scaledSprite);
        return scaledSprite;
    }
}
