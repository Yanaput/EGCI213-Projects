package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelNode extends JPanel{

    public static final int BLANK       = 0;
    public static final int WALL        = 1;
    public static final int START       = 2;
    public static final int GOAL        = 3;
    public static final int SEARCH      = 4;
    public static final int PATH        = 5;

    public static final int NORTHWEST   = 0;
    public static final int NORTH       = 1;
    public static final int NORTHEAST   = 2;
    public static final int WEST        = 3;
    public static final int CENTRE      = 4;
    public static final int EAST        = 5;
    public static final int SOUTHWEST   = 6;
    public static final int SOUTH       = 7;
    public static final int SOUTHEAST   = 8;

    private final Color[] componentColours;
    private static final Color BLANK_COLOUR = UIConstants.GutterGrey;

    private int nodeType = PanelNode.BLANK;
    private int row, column;

    private JLabel cost, heuristic;

    public PanelNode(int x, int y, int column, int row , int size, Color[] componentColours) {
        this.column = column;
        this.row = row;
        this.componentColours = componentColours;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(true);
        this.setBackground(BLANK_COLOUR);
        this.setBounds(x, y, size, size);
        this.setState(PanelNode.BLANK);

        this.cost = new JLabel("0");
        this.heuristic = new JLabel("(0)");
        this.cost.setForeground(UIConstants.White);
        this.heuristic.setForeground(UIConstants.White);
        this.cost.setFont(new Font(UIConstants.fontFamily, Font.BOLD, size / 3));
        this.heuristic.setFont(new Font(UIConstants.fontFamily, Font.BOLD, size / 7));

        this.add(cost);
        this.add(heuristic);
    }

    public void setState(int state) {
        this.nodeType = state;
        this.setBackground(state == 0 ? PanelNode.BLANK_COLOUR : this.componentColours[state - 1]);
    }

    public void toggleState() {
        switch(this.nodeType) {
            case PanelNode.BLANK, PanelNode.GOAL -> this.setState(PanelNode.START);
            case PanelNode.START -> this.setState(PanelNode.GOAL);
        }
    }

    public void setNeighbour(PanelNode neighbour, int direction, boolean connected, int weight) {
        if (direction == PanelNode.CENTRE) return;
    }

    public int getRow() { return this.row; }

    public int getColumn() { return this.column; }

    public int getNodeType() { return this.nodeType; }

}
