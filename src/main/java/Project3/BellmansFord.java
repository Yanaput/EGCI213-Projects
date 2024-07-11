package Project3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BellmansFord implements IAlgorithm {
    private Graph graph;
    private boolean finished;
    private int i, j, k;
    private int row, col;
    private ArrayList<PanelNodeConnection> edgeList;
    private HashMap<PanelNode, Integer> dist;

    public BellmansFord() {}

    @Override
    public void step() {
        if (i < row) {
            if (j < col) {
                if (k < edgeList.size()) {
//                    System.out.printf("[%2d][%2d][%2d]\n", i, j, k);
                    PanelNodeConnection edge = edgeList.get(k++);
                    if (edge == null) return;
                    PanelNode dst = edge.getPanelNode();
                    PanelNode src = edge.getSource();
                    if (dst == null || src.getNodeType() == PanelNode.WALL || dst.getNodeType() == PanelNode.WALL) return;
                    int weight = edge.getWeight();

                    int srcCost = dist.get(src), dstCost = dist.get(dst);

//                    System.out.println(src);
                    if (srcCost != Integer.MAX_VALUE && srcCost + weight < dstCost) {
                        int updatedCost = srcCost + weight;
                        dist.put(dst, updatedCost);
                        dst.setCost(updatedCost);
                        if (dst.getNodeType() != PanelNode.GOAL)
                            dst.setState(PanelNode.SEARCH);
                        dst.setPrevious(src);
                    }
                    return;
                }
                System.out.printf("Relaxing %d from %d\n", i * col + j + 1, row * col);
                j++; k=0;
                return;
            }
            i++; j=0;
            return;
        }
        PanelNode node = graph.getGoalNode();
        while (node.getPrevious() != null) {
            if (node != graph.getGoalNode() && node != graph.getRootNode())
                node.setState(PanelNode.PATH);
            node = node.getPrevious();
        }
        finished = true;
        return;
    }

    @Override
    public void reset() {
        this.finished = false;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        PanelNode [][] nodeList = graph.getArray();
        this.row = nodeList.length;
        this.col = nodeList[0].length;
        System.out.println(row);
        System.out.println(col);
        this.edgeList = new ArrayList<PanelNodeConnection>();
        this.dist = new HashMap<PanelNode, Integer>();
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                PanelNodeConnection [] neighbours = nodeList[i][j].getNeighbours();
                this.edgeList.addAll(Arrays.asList(neighbours));
                this.dist.put(nodeList[i][j], Integer.MAX_VALUE);
            }
        }

        this.dist.put(graph.getRootNode(), 0);
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    @Override
    public String toString() {
        return "Bellman's Ford";
    }

    @Override
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void setStart(int row, int col) {}

    @Override
    public void setDestination(int row, int col) {}
}
