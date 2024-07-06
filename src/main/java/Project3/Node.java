package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Node extends JButton implements MouseListener {
    Node Parent;
    int col, row;
    boolean start, end, clicked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;

        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        addMouseListener(this);
        clicked = false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        clicked = !clicked;
        setBackground(clicked ? new Color(0x58E4BC) : Color.white);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
