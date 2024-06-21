package Project2;

import java.util.concurrent.*;

public class DeliveryThread extends Thread{
    private CyclicBarrier barrier;
    private DeliveryShop shop;
    private int days;
    public DeliveryThread(String name, int days, DeliveryShop shop){
        super(name);
        this.days = days;
        this.shop = shop;
    }

    protected void setBarrier(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    synchronized private void reportRemaining(){
        System.out.printf("%15s  >> parcels to deliver = %5d\n",Thread.currentThread().getName(),
                this.shop.getRemainingParcels());
    }

    synchronized private void deliver(){
        int vehiclesNum = this.shop.getRemainingParcels()/this.shop.getFleet().getMaxLoad();
        vehiclesNum = Math.min(vehiclesNum, this.shop.getFleet().getTotalVehicle());
        int totalDeliver = vehiclesNum * this.shop.getFleet().getMaxLoad();
        if(this.shop.getRemainingParcels()%this.shop.getFleet().getMaxLoad() > this.shop.getFleet().getMinLoad()
        && vehiclesNum < this.shop.getFleet().getTotalVehicle()){
                vehiclesNum++;
                totalDeliver += this.shop.getRemainingParcels()%this.shop.getFleet().getMaxLoad();

        }
        this.shop.deliver(totalDeliver);
        System.out.printf("%15s  >> delivered = %5d by %3d %7s, parcels remain = %5d\n",Thread.currentThread().getName(),
                totalDeliver, vehiclesNum, this.shop.getFleet().getVehicle().toLowerCase()+"s" ,this.shop.getRemainingParcels());
    }

    private int barrierWait(){
        try{
            return this.barrier.await();
        }
        catch (Exception e){ }
        return -1;
    }

    @Override
    public void run(){
        for(int i = 1; i <= days; i++) {
            int temp = barrierWait();
            if(temp == barrier.getParties()-1){
                System.out.printf("%15s  >> %s\n",Thread.currentThread().getName(), "=".repeat(15));
                System.out.printf("%15s  >>  Day  %d\n",Thread.currentThread().getName(), i);
            }
            barrierWait(); //wait for seller drop

            reportRemaining();
            barrierWait();

            deliver();
            barrierWait();
        }
    }
}
