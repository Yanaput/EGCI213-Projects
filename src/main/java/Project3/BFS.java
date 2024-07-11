package Project3;

import java.awt.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class BFS implements IAlgorithm {
    private Graph graph;
    private int startRow, startColumn;
    private int destinationRow, destinationColumn;
    private boolean finished = false;

    private Queue<PanelNode> queue;
    private Set<PanelNode> visited;
    private PanelNode currentNode;

    public BFS() {
        reset();
    }

    @Override
    public void step() {
        visited.add(currentNode);
        currentNode.setState(PanelNode.SEARCH);
        if (currentNode == graph.getGoalNode()) {
            finished = true;
            return;
        }

        queue.remove();
        currentNode = queue.peek();
        if (visited.contains(currentNode)) {
            return;
        }

        for(PanelNode p : currentNode.getNeighbours()) {
            p.setState(PanelNode.SEARCHING);
            queue.add(p);
        }

    }

    @Override
    public void reset() {
        this.queue = new LinkedList<PanelNode>();
        this.visited = new HashSet<PanelNode>();
        this.currentNode = graph.getRootNode();
        queue.add(currentNode);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String toString() {
        return "BFS";
    }

    @Override
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void setStart(int row, int col) {
        this.startRow = row;
        this.startColumn = col;
    }

    @Override
    public void setDestination(int row, int col) {
        this.destinationRow = row;
        this.destinationColumn = col;
    }
}
