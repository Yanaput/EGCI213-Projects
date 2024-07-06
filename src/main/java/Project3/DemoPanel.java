package Project3;

import javax.swing.*;
import java.awt.*;

public class DemoPanel extends JPanel {
    final int maxCol = 15;
    final int maxRow = 15;
    final int screenWidth = 1366;
    final int screenHeight = 768;
    final int nodeSize = (1366*768)/(15*15);


    Node [][] node = new Node[maxCol][maxRow];

    public DemoPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxRow, maxCol));

        for(int col = 0; col < maxCol; col++){
            for(int row = 0; row < maxRow; row++){
                node[col][row] = new Node(col, row);
                this.add(node[col][row]);
            }
        }

    }
}
