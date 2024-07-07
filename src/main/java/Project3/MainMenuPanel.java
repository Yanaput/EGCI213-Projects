package Project3;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

   private static final Color backgroundColour = UIConstants.DarkBlueBackground;

   public MainMenuPanel(int menuWidth, int menuHeight){
       this.setPreferredSize(new Dimension(menuWidth, menuHeight));
       this.setBackground(backgroundColour);

   }
}
