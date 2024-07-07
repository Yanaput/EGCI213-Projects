package Project3;

import javax.swing.*;
import java.awt.*;

public class AlgoPagePanelSimulation extends JPanel {
    private static final Color backgroundColour = new Color(0x20293b);
    private static final int padding = 10;

    private PanelNode [][] panelNodes;

    public AlgoPagePanelSimulation(Dimension dimension, int row, int column){
        this.setPreferredSize(dimension);
        this.setBackground(backgroundColour.darker());
        this.setLayout(null);

        int horizontalSize = (dimension.width - 2 * padding) / column;
        int verticalSize = (dimension.height - 2 * padding) / row;

        int size =  Math.min(horizontalSize, verticalSize);

        int horizontalOffset = (dimension.width - size * column) / 2 + padding;
        int verticalOffset = (dimension.height - size * row) / 2 + padding;

        panelNodes = new PanelNode[row][column];
        for(int i=0; i<row; i++) {
            for(int j=0; j<column; j++) {
                panelNodes[i][j] = new PanelNode(horizontalOffset + j * size, verticalOffset + i * size, size - 1);
                this.add(panelNodes[i][j]);
            }
        }
    }

    public Graph getGraph() {
        return null;
    }
}
