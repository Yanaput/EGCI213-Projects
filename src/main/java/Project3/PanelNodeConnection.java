package Project3;

public class PanelNodeConnection {

    private PanelNode panelNode;
    private PanelNode source;
    private boolean connected;
    private int weight;

    public PanelNodeConnection(PanelNode source, PanelNode panelNode, boolean connected, int weight) {
        this.source = source;
        this.panelNode = panelNode;
        this.connected = connected;
        this.weight = weight;
    }

    public PanelNode getPanelNode() {
        return panelNode;
    }

    public PanelNode getSource() {
        return source;
    }

    public boolean isConnected() {
        return connected;
    }

    public int getWeight() {
        return weight;
    }
}
