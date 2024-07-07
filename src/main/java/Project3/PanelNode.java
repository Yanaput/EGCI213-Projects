package Project3;

import javax.swing.*;
import java.awt.*;

public class PanelNode extends JPanel {

    private JLabel cost, heuristic;

    public PanelNode(int x, int y, int size) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(true);
        this.setBackground(UIConstants.GutterGrey);
        this.setBounds(x, y, size, size);

        this.cost = new JLabel("test1");
        this.heuristic = new JLabel("(test2)");
        this.cost.setForeground(UIConstants.White);
        this.heuristic.setForeground(UIConstants.White);
        this.cost.setFont(new Font(UIConstants.fontFamily, Font.BOLD, size / 3));
        this.heuristic.setFont(new Font(UIConstants.fontFamily, Font.BOLD, size / 7));

        this.add(cost);
        this.add(heuristic);
    }
}
