package Project3;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFS implements IAlgorithm {
    private Graph graph;
    private boolean finished = false;

    private Stack<PanelNode> stack;
    private Set<PanelNode> visited;
    private Set<PanelNode> searched;
    private PanelNode currentNode;

    public DFS() {}

    @Override
    public void step() {

        if (this.finished) return;
        if (stack.empty()) return;

        currentNode = stack.peek();
        stack.pop();

        if (currentNode == null) return;
        while (visited.contains(currentNode)) {
            currentNode = stack.peek();
            stack.pop();
        }

        if (currentNode != graph.getGoalNode() && currentNode != graph.getRootNode())
            currentNode.setState(PanelNode.SEARCH);

        visited.add(currentNode);

        if (currentNode == graph.getGoalNode()) {
            PanelNode node = currentNode;
            while(node.getPrevious() != null) {
                if(node != graph.getGoalNode() && node != graph.getRootNode())
                    node.setState(PanelNode.PATH);
                node = node.getPrevious();
            }
            finished = true;
            return;
        }

        for (PanelNodeConnection p : currentNode.getNeighbours()) {
            if (p == null || !p.isConnected()) continue;
            PanelNode pt = p.getPanelNode();

            if (pt == null || visited.contains(pt) || searched.contains(pt)) continue;
            pt.setPrevious(currentNode);
            if (pt != graph.getGoalNode() && pt != graph.getRootNode())
                pt.setState(PanelNode.SEARCHING);

            stack.add(pt);
            searched.add(pt);
        }
    }

    @Override
    public void reset() {
        this.stack = new Stack<PanelNode>();
        this.visited = new HashSet<PanelNode>();
        this.searched = new HashSet<PanelNode>();
        this.currentNode = graph.getRootNode();
        this.currentNode.setPrevious(null);
        this.finished = false;
        stack.add(currentNode);
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    @Override
    public String toString() {
        return "DFS";
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
