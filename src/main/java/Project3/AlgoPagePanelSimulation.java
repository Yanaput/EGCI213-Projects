package Project3;

import javax.swing.*;
import java.awt.*;

public class AlgoPagePanelSimulation extends JPanel {
    private static final Color backgroundColour = new Color(0x20293b);

    public AlgoPagePanelSimulation(Dimension dimension){
        this.setPreferredSize(dimension);
        this.setBackground(backgroundColour.darker());
//        this.setBorder(BorderFactory.createLineBorder(new Color(UIConstants.LightRed)));
    }
}
