package Project3;

public class GraphNodeDirection {
    private int direction;
    public GraphNodeDirection(int direction) {
        this.direction = direction;
    }
    public void enableDirection(int direction) {
        this.direction |= direction;
    }
    public void disableDirection(int direction) {
        this.direction &= ~direction;
    }
    public boolean isDirectionAvailable(int direction) {
        return (direction & this.direction) != 0;
    }
    public int getDirection() {
        return direction;
    }
}
