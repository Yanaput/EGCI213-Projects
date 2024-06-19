package Project2;

import java.util.concurrent.CyclicBarrier;

public class DeliveryShop implements Comparable<DeliveryShop>{
    private String name;
    private Fleet fleet;
    private DeliveryThread thread;
    private int totalReceived, totalDelivered, remainingParcels;
    public DeliveryShop(String name, Fleet fleet, CyclicBarrier barrier){
        this.fleet = fleet;
        this.name = name;
        this.thread = new DeliveryThread(name);
        this.thread.setBarrier(barrier);
    }

    protected void received(int parcelsNumber){
        this.remainingParcels += parcelsNumber;
        this.totalReceived += parcelsNumber;
    }

    @Override
    public int compareTo(DeliveryShop other) {
        return Double.compare( (double)this.totalDelivered/this.totalReceived, (double)other.totalDelivered/other.totalReceived);
    }

    protected DeliveryThread getThread(){
        return this.thread;
    }
}
