package Project2;

public class DeliveryShop {
    private String name;
    private Fleet fleet;
    private int totalReceived, totalDelivered, remainingParcels;
    public DeliveryShop(Fleet fleet){
        this.fleet = fleet;
    }

    protected void received(int parcelsNumber){
        this.remainingParcels += parcelsNumber;
        this.totalReceived += parcelsNumber;
    }
}
