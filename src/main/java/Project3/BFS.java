package Project3;

import java.awt.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class BFS implements IAlgorithm {
    private Graph graph;
    private boolean finished = false;

    private Queue<PanelNode> queue;
    private Set<PanelNode> visited;
    private Set<PanelNode> searched;
    private PanelNode currentNode;

    public BFS() {}

    @Override
    public void step() {

        if (this.finished) return;

        if (queue.isEmpty()) return;
//        System.out.println("Stepping");

        // Front
        currentNode = queue.peek();
//        System.out.println(currentNode);

        // Pop
        queue.remove();

//        System.out.println(currentNode);

        if (currentNode == null) return;
        while (visited.contains(currentNode)) {
            currentNode = queue.peek();
            queue.remove();
        }

        if (currentNode != graph.getGoalNode() && currentNode != graph.getRootNode())
            currentNode.setState(PanelNode.SEARCH);

        visited.add(currentNode);

        if (currentNode == graph.getGoalNode()) {
            PanelNode node = currentNode;
            while (node.getPrevious() != null) {

                if (node != graph.getGoalNode() && node != graph.getRootNode())
                    node.setState(PanelNode.PATH);
                node = node.getPrevious();
            }
            finished = true;
            return;
        }
//        System.out.println("Here");


        for(PanelNodeConnection p : currentNode.getNeighbours()) {
            if (p == null || !p.isConnected()) continue;
            PanelNode pt = p.getPanelNode();
//            System.out.println("Neighbour: " + pt);
            if (pt == null || visited.contains(pt) || searched.contains(pt)) continue;
            pt.setPrevious(currentNode);
            if (pt != graph.getGoalNode() && pt != graph.getRootNode())
                pt.setState(PanelNode.SEARCHING);
            queue.add(pt);
            searched.add(pt);
        }

    }

    @Override
    public void reset() {
        this.queue = new LinkedList<PanelNode>();
        this.visited = new HashSet<PanelNode>();
        this.searched = new HashSet<PanelNode>();
        this.currentNode = graph.getRootNode();
        this.currentNode.setPrevious(null);
        this.finished = false;
        queue.add(currentNode);
    }

    @Override
    public boolean isFinished() {
        return this.finished;
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
    public void setStart(int row, int col) {}

    @Override
    public void setDestination(int row, int col) {}
}
