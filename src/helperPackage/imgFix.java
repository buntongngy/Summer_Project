package helperPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class imgFix {

    //Rotate

    public static BufferedImage getRotateImg(BufferedImage img, int rotAngle) {

        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage newImg = new BufferedImage(w, h, img.getType());
        Graphics2D g2d = newImg.createGraphics();

        g2d.rotate(Math.toRadians(rotAngle), w/2, h/2);
        g2d.drawImage(img, 0,0,null);
        g2d.dispose();

        return newImg;
    }

    //img layer
    public static BufferedImage buildImg(BufferedImage[] imgs ) {
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (BufferedImage i : imgs) {
            g2d.drawImage(i, 0,0,null);
        }
        g2d.dispose();
        return newImg;

    }

    //Rotate Second img only

    public static BufferedImage getBuildRotImg(BufferedImage[] imgs, int rotAngle, int rotAngleIndex) {

        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (int i=0; i<imgs.length;i++) {
            if (rotAngleIndex == i) {
                g2d.rotate(Math.toRadians(rotAngle), w/2, h/2);
            }
            g2d.drawImage(imgs[i], 0,0,null);

            if (rotAngleIndex == i) {
                g2d.rotate(Math.toRadians(-rotAngle), w / 2, h / 2);
            }
        }
        g2d.dispose();
        return newImg;

    }


}
