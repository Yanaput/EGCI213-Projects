package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelNode extends JPanel{

    public static final int BLANK    = 0;
    public static final int WALL     = 1;
    public static final int START    = 2;
    public static final int GOAL     = 3;
    public static final int SEARCH   = 4;
    public static final int PATH     = 5;
    private final Color[] componentColours;
    private static final Color BLANK_COLOUR = UIConstants.GutterGrey;
    private JPanel parent;

    private int nodeType = PanelNode.BLANK;

    private JLabel cost, heuristic;

    public PanelNode(JPanel parent, int x, int y, int size, Color[] componentColours) {
        this.parent = parent;
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
        this.setState(this.nodeType % 3 + 1);
    }


//    @Override
//    public void mouseClicked(MouseEvent e) {
////        if (!isPointInNode(e.getX(), e.getY())) return;
//
//        switch(e.getButton()) {
//            case MouseEvent.BUTTON3:
//                this.setState(PanelNode.BLANK);
//                break;
//            case MouseEvent.BUTTON1:
//                this.setState((this.nodeType + 1) % 4);
//                break;
//        }
//
//
//
//        System.out.printf("[%3d] [%3d] : [%d]\n", this.getX(), this.getY(), e.getButton());
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {}
//
//    @Override
//    public void mouseReleased(MouseEvent e) {}
//
//    @Override
//    public void mouseEntered(MouseEvent e) {}
//
//    @Override
//    public void mouseExited(MouseEvent e) {}
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        parent.dispatchEvent(e);
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//
//    }
}
