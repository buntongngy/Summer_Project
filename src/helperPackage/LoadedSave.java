package helperPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadedSave {

    public static BufferedImage getSpriteAtlas() {

        BufferedImage img =null;
        InputStream is = LoadedSave.class.getClassLoader().getResourceAsStream("TD_sheet.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
