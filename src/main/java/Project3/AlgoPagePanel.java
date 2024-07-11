package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AlgoPagePanel extends JPanel{

    private JFrame parentFrame;
    public AlgoPagePanel(Dimension dimension, int row, int column, Color[] componentColours, JFrame parentFrame){
        this.parentFrame = parentFrame;
        this.setPreferredSize(dimension);
        this.setBackground(UIConstants.DarkBlueBackground);
        AlgoPagePanelSimulation simulationPanel = new AlgoPagePanelSimulation(new Dimension(dimension.width - 400 - 15, dimension.height - 50), row, column, componentColours);
        AlgoPagePanelSettings settingPanel = new AlgoPagePanelSettings(simulationPanel, new Dimension(400 - 15, dimension.height - 50), this);
        this.add(settingPanel);
        this.add(simulationPanel);
    }

    public JFrame getParentFrame(){
        return this.parentFrame;
    }
}
