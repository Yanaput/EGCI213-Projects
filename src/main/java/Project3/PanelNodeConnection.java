package Project3;

public class PanelNodeConnection {

    private PanelNode panelNode;
    private boolean connected;
    private int weight;

    public PanelNodeConnection(PanelNode panelNode, boolean connected, int weight) {
        this.panelNode = panelNode;
        this.connected = connected;
        this.weight = weight;
    }

    public PanelNode getPanelNode() {
        return panelNode;
    }

    public boolean isConnected() {
        return connected;
    }

    public int getWeight() {
        return weight;
    }
}
