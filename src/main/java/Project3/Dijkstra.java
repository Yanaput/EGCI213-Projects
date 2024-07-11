package Project3;

import java.awt.*;
import java.util.*;

public class Dijkstra implements IAlgorithm {
    private Graph graph;
    private boolean finished = false;

    private PriorityQueue<PanelNode> pq;
//    private Set<PanelNode> visited;
    private PanelNode currentNode;

    public Dijkstra() {}

    @Override
    public void step() {
        if (this.finished) return;
        if (pq.isEmpty()) return;


        currentNode = pq.peek();
        pq.remove();

        if (currentNode != graph.getGoalNode() && currentNode != graph.getRootNode())
            currentNode.setState(PanelNode.SEARCH);

        if (currentNode == null) return;

//        while (currentNode.getCost() < ) {
//
//        }

        for(PanelNodeConnection p : currentNode.getNeighbours()) {
            if (p == null || !p.isConnected()) continue;
            PanelNode pt = p.getPanelNode();
//            System.out.println("Neighbour: " + pt);
            if (pt == null || currentNode.getCost() + p.getWeight() > pt.getCost()) continue;
            pt.setCost(currentNode.getCost() + p.getWeight());
            pt.setPrevious(currentNode);
            if (pt != graph.getGoalNode() && pt != graph.getRootNode())
                pt.setState(PanelNode.SEARCHING);
            if (pq.contains(pt)) continue;
            pq.add(pt);
        }
//        System.out.println(pq);
        if (pq.isEmpty()) {
            PanelNode node = graph.getGoalNode();
            while(node.getPrevious() != null) {
                if (node != graph.getGoalNode() && node != graph.getRootNode())
                    node.setState(PanelNode.PATH);
                node = node.getPrevious();
            }
            finished = true;
            return;
        }
    }

    @Override
    public void reset() {
        Comparator<PanelNode> comparator = new Comparator<PanelNode>() {
            @Override
            public int compare(PanelNode o1, PanelNode o2) {
                int d = o1.getCost() - o2.getCost();
                int d2 = o1.getRow() - o2.getRow();
                return d != 0 ? d : (d2 != 0 ? d2 : o1.getColumn() - o2.getColumn());
            }
        };

        this.pq = new PriorityQueue<PanelNode>(comparator);
//        this.visited = new HashSet<PanelNode>();
        this.currentNode = graph.getRootNode();
        this.currentNode.setPrevious(null);
        this.finished = false;
        currentNode.setCost(0);
        pq.add(currentNode);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String toString() {
        return "Dijkstra";
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
