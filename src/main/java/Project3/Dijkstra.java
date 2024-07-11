package Project3;

public class Dijkstra implements IAlgorithm {
    private Graph graph;
    private int startRow, startColumn;
    private int destinationRow, destinationColumn;

    public Dijkstra() {

    }

    @Override
    public void step() {

    }

    @Override
    public void reset() {

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
