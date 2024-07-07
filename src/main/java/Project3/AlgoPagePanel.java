package Project3;

import javax.swing.*;
import java.awt.*;

public class AlgoPagePanel extends JPanel {

    public AlgoPagePanel(Dimension dimension, int row, int column){
        this.setPreferredSize(dimension);
        this.setBackground(UIConstants.DarkBlueBackground);
        this.add(new AlgoPagePanelSettings(new Dimension(400 - 15, dimension.height - 50)));
        this.add(new AlgoPagePanelSimulation(new Dimension(dimension.width - 400 - 15, dimension.height - 50), row, column));
    }
}
