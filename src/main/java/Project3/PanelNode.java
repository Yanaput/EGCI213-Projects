package Project3;

import javax.swing.*;
import java.awt.*;

public class PanelNode extends JPanel{

    public static final int BLANK       = 0;
    public static final int WALL        = 1;
    public static final int START       = 2;
    public static final int GOAL        = 3;
    public static final int SEARCH      = 4;
    public static final int SEARCHING   = 5;
    public static final int PATH        = 6;

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

    private PanelNode previous;

    private JLabel costLabel, heuristicLabel;
    private int cost, heuristic;

    private PanelNodeConnection [] neighbours;

    public PanelNode(int x, int y, int column, int row , int size, Color[] componentColours) {
        this.column = column;
        this.row = row;
        this.componentColours = componentColours;
        this.neighbours = new PanelNodeConnection[9];
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(true);
        this.setBackground(BLANK_COLOUR);
        this.setBounds(x, y, size, size);
        this.setState(PanelNode.BLANK);

        this.costLabel = new JLabel("0");
        this.heuristicLabel = new JLabel("(0)");
        this.costLabel.setForeground(UIConstants.White);
        this.heuristicLabel.setForeground(UIConstants.White);
        this.costLabel.setFont(new Font(UIConstants.fontFamily, Font.BOLD, size / 3));
        this.heuristicLabel.setFont(new Font(UIConstants.fontFamily, Font.BOLD, size / 7));

        this.add(costLabel);
        this.add(heuristicLabel);
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
        this.neighbours[direction] = new PanelNodeConnection(this, neighbour, connected, weight);
    }

    public PanelNodeConnection [] getNeighbours() {
        return this.neighbours;
    }

    public void setPrevious(PanelNode previous) {
        this.previous = previous;
    }

    public PanelNode getPrevious() {
        return this.previous;
    }

    public int getRow() { return this.row; }

    public int getColumn() { return this.column; }

    public int getNodeType() { return this.nodeType; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; this.costLabel.setText(cost == Integer.MAX_VALUE ? "inf" : ("" + cost));}

    public int getHeuristic() { return heuristic; }
    public void setHeuristic(int heuristic) { this.heuristic = heuristic; this.heuristicLabel.setText("" + heuristic); }

    public int getComputedHeuristicFunction() { return this.cost + this.heuristic; }

    public String toString() {
        return "Panel [" + this.row + "] [" + this.column + "] (" + this.cost + ")";
    }

}
