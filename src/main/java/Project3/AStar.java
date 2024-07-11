package Project3;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar implements IAlgorithm {
    private Graph graph;
    private PriorityQueue<PanelNode> openList;
    private Set<PanelNode> closedList;
    private PanelNode currentNode;

    private boolean finished;

    public AStar() {

    }

    @Override
    public void step() {
        if (this.finished) return;
        if (openList.isEmpty()) return;

        currentNode = openList.peek();
        openList.remove();

        if (currentNode == graph.getGoalNode()) {
            PanelNode node = graph.getGoalNode();
            while(node.getPrevious() != null) {
                if (node != graph.getGoalNode() && node != graph.getRootNode())
                    node.setState(PanelNode.PATH);
                node = node.getPrevious();
            }
            finished = true;
            return;
        }

        closedList.add(currentNode);

        if (currentNode != graph.getGoalNode() && currentNode != graph.getRootNode())
            currentNode.setState(PanelNode.SEARCH);

        if (currentNode == null) return;

        for(PanelNodeConnection p : currentNode.getNeighbours()) {
            if (p == null || !p.isConnected()) continue;
            PanelNode pt = p.getPanelNode();

            if (pt == null || closedList.contains(pt)) continue;
            int tentativeCost = currentNode.getCost() + p.getWeight();
            if (tentativeCost >= pt.getCost() && openList.contains(pt)) continue;
            pt.setCost(tentativeCost);
            pt.setPrevious(currentNode);
            if (pt != graph.getGoalNode() && pt != graph.getRootNode())
                pt.setState(PanelNode.SEARCHING);
            if (openList.contains(pt)) continue;
            openList.add(pt);
        }

    }

    @Override
    public void reset() {

        Comparator<PanelNode> comparator = new Comparator<PanelNode>() {
            @Override
            public int compare(PanelNode o1, PanelNode o2) {
                return o1.getComputedHeuristicFunction() - o2.getComputedHeuristicFunction();
            }
        };

        this.openList = new PriorityQueue<PanelNode>(comparator);
        this.closedList = new HashSet<PanelNode>();
        this.currentNode = graph.getRootNode();
        this.currentNode.setPrevious(null);
        this.finished = false;
        currentNode.setCost(0);
        openList.add(currentNode);
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    @Override
    public String toString() {
        return "A*";
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
