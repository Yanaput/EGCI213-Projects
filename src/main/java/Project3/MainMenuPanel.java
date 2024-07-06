package Project3;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    private static final Color backgroundColour = new Color(0x20293b);

    public MainMenuPanel(int menuWidth, int menuHeight){
        this.setPreferredSize(new Dimension(menuWidth, menuHeight));
        this.setBackground(backgroundColour);

    }
}
