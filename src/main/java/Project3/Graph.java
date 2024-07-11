package Project3;

public class Graph {

    private PanelNode [][] graph;

    private int startRow, startColumn;
    private int destinationRow, destinationColumn;

    private int startCount = 0, destinationCount = 0;

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

    public void incStart(){
        this.startCount++;
        System.out.println("inc start" + this.startCount);
    }

    public void decStart(){
        this.startCount--;
        System.out.println("dec start" + this.startCount);
    }
    public void incDestination(){
        this.destinationCount++;
        System.out.println("inc des" + this.destinationCount);
    }

    public void decDestination(){
        this.destinationCount--;
        System.out.println("dec des" + this.destinationCount);
    }

    public int getStartCount(){return this.startCount;}
    public int getDestinationCount(){return this.destinationCount;}

    public int getDestinationRow() {
        return destinationRow;
    }

    public int getDestinationColumn() {
        return destinationColumn;
    }

    public PanelNode getGoalNode() {
        return graph[destinationRow][destinationColumn];
    }

    public PanelNode[][] getArray() {return this.graph;}
}
