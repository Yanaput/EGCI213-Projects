package Project3;

public class GraphEdge {

    private GraphNode source;
    private GraphNode destination;
    private int weight;

    public GraphEdge (GraphNode source, GraphNode destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public GraphNode getSource() {
        return source;
    }

    public GraphNode getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
