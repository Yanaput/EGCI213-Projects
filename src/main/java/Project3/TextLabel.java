package Project3;

import javax.swing.*;
import java.awt.*;

public class TextLabel extends JLabel {
    public TextLabel(String string) {
        super(string);
        this.setForeground(UIConstants.White);
        this.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 20));
    }
}
