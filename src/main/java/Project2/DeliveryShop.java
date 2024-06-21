package Project2;

import java.util.concurrent.CyclicBarrier;

public class DeliveryShop implements Comparable<DeliveryShop>{
    private String name;
    private Fleet fleet;
    private DeliveryThread thread;
    private double successRate;
    private int totalReceived, totalDelivered, remainingParcels;
    public DeliveryShop(String name, Fleet fleet, CyclicBarrier barrier, int days){
        this.fleet = fleet;
        this.name = name;
        this.thread = new DeliveryThread(name, days, this);
        this.thread.setBarrier(barrier);
    }

    synchronized protected void received(int parcelsNumber){
        this.remainingParcels += parcelsNumber;
        this.totalReceived += parcelsNumber;
    }

    @Override
    public int compareTo(DeliveryShop other) {
        return Double.compare( other.successRate,
                this.successRate);
    }

    private void calSuccessRate(){
        this.successRate = (double)this.totalDelivered/(double)this.totalReceived;
    }

    public void reportSummary(){
        System.out.printf("%15s >>  %-15s received = %4d, delivered = %4d, success rate = %4.2f\n",
                Thread.currentThread().getName(), this.getName(), this.getTotalReceived(),
                this.getTotalDelivered()  , this.getSuccessRate());
    }

    protected int getTotalReceived(){return this.totalReceived;}
    protected int getTotalDelivered(){return this.totalDelivered;}
    protected String getName(){return this.name;}
    protected double getSuccessRate(){return this.successRate;}
    protected DeliveryThread getThread(){return this.thread;}
    protected Fleet getFleet(){return this.fleet;}
    protected int getRemainingParcels(){return this.remainingParcels;}

    protected void deliver(int delivered){
        this.remainingParcels -= delivered;
        this.totalDelivered += delivered;
        calSuccessRate();
    }
}
