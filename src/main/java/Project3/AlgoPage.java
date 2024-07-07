package Project3;

import javax.swing.*;
import java.awt.*;

public class AlgoPage extends JFrame {

   private Graph graph;
   private IAlgorithm algorithm;

   public AlgoPage(int row, int columns) {
       super("PathFindSim");

       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       Insets inset = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
       Dimension pageSize = new Dimension(screenSize.width - inset.left - inset.right, screenSize.height - inset.top - inset.bottom);

       System.out.println(pageSize);

       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setExtendedState();
       this.setLocation(0, 0);
       this.setSize(pageSize);
       this.setResizable(false);
       this.add(new AlgoPagePanel(pageSize, row, columns));
       this.setVisible(true);

       this.graph = new Graph(row, columns);
   }

   public void setAlgorithm(IAlgorithm algorithm) {
       this.algorithm = algorithm;
   }

   public void step() {
       algorithm.step();
   }

   public void reset() {
       algorithm.reset();
   }
}
