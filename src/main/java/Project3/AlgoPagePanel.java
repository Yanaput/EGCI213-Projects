package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AlgoPagePanel extends JPanel{


    public AlgoPagePanel(Dimension dimension, int row, int column, Color[] componentColours){
        this.setPreferredSize(dimension);
        this.setBackground(UIConstants.DarkBlueBackground);
        JPanel simulationPanel = new AlgoPagePanelSimulation(new Dimension(dimension.width - 400 - 15, dimension.height - 50), row, column, componentColours);
        JPanel settingPanel = new AlgoPagePanelSettings(simulationPanel, new Dimension(400 - 15, dimension.height - 50));
        this.add(settingPanel);
        this.add(simulationPanel);
    }
}
