package Project3;

public interface IAlgorithm {
    public void step();
    public void reset();
    public boolean isFinished();
    public String toString();
    public void setGraph(Graph graph);
    public void setStart(int row, int col);
    public void setDestination(int row, int col);
}
