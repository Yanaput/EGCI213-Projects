package Project3;

import javax.swing.*;
import java.awt.*;

class MyImageIcon extends ImageIcon {
    public MyImageIcon(String filename) {
        super(filename);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(int width, int height) {
        Image image = this.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new MyImageIcon(resizedImage);
    }
}

