

import javax.imageio.ImageIO;
import java.awt.Image;


public class LoadImg {
    private String context;

    public LoadImg(String context) {
        this.context = context;
    }

    public Image getImg() {
        Image img = null;
        try {
            String fileName = this.context.replace("(", ":").replace(")", ":");
            fileName = fileName.split(":")[1];
            img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(fileName));
        }catch (Exception ex) {
            System.err.println("Unable to load image");
            System.exit(1);
        }
        if(img == null) {
            System.err.println("Unable to load image");
            System.exit(1);
        }
        return img;
    }
}
