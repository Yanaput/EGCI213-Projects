package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AlgoPagePanel extends JPanel implements MouseListener {

    private JFrame parentFrame;
    private JPanel settingPanel;

    public AlgoPagePanel(JFrame parentFrame, Dimension dimension, int row, int column, Color[] componentColours){
        this.parentFrame = parentFrame;
        this.setPreferredSize(dimension);
        this.setBackground(UIConstants.DarkBlueBackground);
        this.settingPanel = new AlgoPagePanelSettings(new Dimension(400 - 15, dimension.height - 50));
        this.add(this.settingPanel);
        this.add(new AlgoPagePanelSimulation(new Dimension(dimension.width - 400 - 15, dimension.height - 50), row, column, componentColours));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        parentFrame.repaint();
        this.repaint();
        this.settingPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
