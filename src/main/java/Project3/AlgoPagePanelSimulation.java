package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class AlgoPagePanelSimulation extends JPanel implements MouseMotionListener, MouseListener {
    private static final Color backgroundColour = UIConstants.DarkBlueBackground;
    private static final int padding = 10;

    private int horizontalOffset;
    private int verticalOffset;
    private int column;
    private int row;
    private int nodeSize;

    private PanelNode [][] panelNodes;


    public AlgoPagePanelSimulation(Dimension dimension, int row, int column, Color[] componentColours){
        this.column = column;
        this.row = row;
        this.setPreferredSize(dimension);
        this.setBackground(backgroundColour.darker());
        this.setLayout(null);

        int horizontalSize = (dimension.width - 2 * padding) / column;
        int verticalSize = (dimension.height - 2 * padding) / row;

        this.nodeSize =  Math.min(horizontalSize, verticalSize);

        this.horizontalOffset = (dimension.width - this.nodeSize * column) / 2 + padding;
        this.verticalOffset = (dimension.height - this.nodeSize * row) / 2 + padding;

        panelNodes = new PanelNode[row][column];
        for(int i=0; i<row; i++) {
            for(int j=0; j<column; j++) {
                panelNodes[i][j] = new PanelNode(horizontalOffset + j * this.nodeSize, verticalOffset + i * this.nodeSize, j, i, this.nodeSize - 1, componentColours);
                this.add(panelNodes[i][j]);
            }
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private boolean isInBound(int row, int column) {
        return 0 <= row && row < this.row && 0 <= column && column < this.column;
    }

    public Graph getGraph() {
        Graph graph = new Graph(panelNodes);
        int startCount = 0;
        int goalCount = 0;
        for(int i=0; i<this.row; i++) {
            for(int j=0; j<this.column; j++) {
                PanelNode currentNode = this.panelNodes[i][j];
                int currentNodeType = currentNode.getNodeType();
                switch(currentNodeType) {
                    case PanelNode.SEARCH:
                    case PanelNode.SEARCHING:
                    case PanelNode.PATH:
                        currentNode.setState(PanelNode.BLANK); break;
                    default:
                        currentNode.setState(currentNodeType);
                }
                currentNode.setCost(Integer.MAX_VALUE);
                currentNode.setPrevious(null);
                if (currentNode.getNodeType() == PanelNode.START){
                    startCount++;
                    graph.setStartPosition(i, j);
                    currentNode.setCost(0);
                }
                if (currentNode.getNodeType() == PanelNode.GOAL){
                    goalCount++;
                    graph.setDestinationPosition(i, j);
                }
                for (int ii=0; ii<=2; ii++) {
                    for(int jj=0; jj<=2; jj++) {
                        int direction = ii * 3 + jj;

                        // If out of bound or centre or diagonal
//                        if (!isInBound(i + ii - 1, j + jj - 1) || direction == 4) {
                        if (!isInBound(i + ii - 1, j + jj - 1) || direction % 2 == 0) {
                            currentNode.setNeighbour(null, direction, false, 0);
                            continue;
                        }
                        PanelNode neighbourCell = this.panelNodes[i + ii - 1][j + jj - 1];

                        // If neighbour is wall
                        if (neighbourCell.getNodeType() == PanelNode.WALL) {
                            currentNode.setNeighbour(neighbourCell, direction, false, 0);
                            continue;
                        }

                        // Other cases cost 14 diagonally, 10 horizontally and vertically
                        // Will make it support diagonal if we have enough time
                        currentNode.setNeighbour(neighbourCell, direction, true, direction % 2 == 0 ? 14 : 10);
                    }
                }
            }
        }

        for(int i=0; i<row; i++) {
            for(int j=0; j<column; j++) {
                PanelNode currentNode = this.panelNodes[i][j];
                int dy = currentNode.getRow() - graph.getDestinationRow(), dx = currentNode.getColumn() - graph.getDestinationColumn();
                currentNode.setHeuristic((int) Math.sqrt(dx*dx + dy*dy));
            }
        }

        System.out.println("startCount : " + startCount);
        System.out.println("goalCount : "  + goalCount);
        if(startCount == 1 && goalCount == 1 )
            return graph;
        else
            return null;
    }

    private PanelNode getNodeAt(int x, int y) {
        x -= this.horizontalOffset;
        y -= this.verticalOffset;
        x /= this.nodeSize; y /= this.nodeSize;
//        System.out.printf("[%3d] [%3d]\n", x, y);
        if (0 <= x && x < column && 0 <= y && y < row) return panelNodes[y][x];
        return null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        PanelNode node = getNodeAt(x, y);
        if (node == null) return;
        node.setState(SwingUtilities.isRightMouseButton(e) ? PanelNode.BLANK : PanelNode.WALL);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
//        System.out.printf("[%3d] [%3d]\n", x, y);

        PanelNode node = getNodeAt(x, y);
        if (node == null) return;
//        System.out.println("Click");
        if (SwingUtilities.isRightMouseButton(e)) {
            node.setState(PanelNode.BLANK);
        } else {
            node.toggleState();
        }
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
