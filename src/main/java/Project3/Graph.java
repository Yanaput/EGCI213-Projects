package Project3;

public class Graph {

    private PanelNode [][] graph;

    private int startRow, startColumn;
    private int destinationRow, destinationColumn;

    public Graph(PanelNode [][] panelNodes) {
        this.graph = panelNodes;
    }

    public PanelNode getRootNode() {
        return graph[startRow][startColumn];
    }

    public void setStartPosition(int row, int col) {
        this.startRow = row;
        this.startColumn = col;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setDestinationPosition(int row, int col) {
        this.destinationRow = row;
        this.destinationColumn = col;
    }

    public int getDestinationRow() {
        return destinationRow;
    }

    public int getDestinationColumn() {
        return destinationColumn;
    }
}
