package Project2;

import java.util.concurrent.CyclicBarrier;

public class DeliveryShop implements Comparable<DeliveryShop>{
    private String name;
    private Fleet fleet;
    private DeliveryThread thread;
    private int totalReceived, totalDelivered, remainingParcels;
    public DeliveryShop(String name, Fleet fleet, CyclicBarrier barrier, int days){
        this.fleet = fleet;
        this.name = name;
        this.thread = new DeliveryThread(name, days, this);
        this.thread.setBarrier(barrier);
    }

    protected void received(int parcelsNumber){
        this.remainingParcels += parcelsNumber;
        this.totalReceived += parcelsNumber;
    }

    @Override
    public int compareTo(DeliveryShop other) {
        return Double.compare( (double)other.totalDelivered/other.totalReceived,
                (double)this.totalDelivered/this.totalReceived);
    }

    protected DeliveryThread getThread(){return this.thread;}
    protected Fleet getFleet(){return this.fleet;}
    protected int getRemainingParcels(){return this.remainingParcels;}
    protected void deliver(int delivered){
        this.remainingParcels -= delivered;
        this.totalDelivered += delivered;
    }
}
