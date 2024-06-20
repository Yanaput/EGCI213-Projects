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

    synchronized private void deliver(){
//        int vehiclesNum = this.shop.getRemainingParcels()/this.shop.getFleet().getMaxLoad();
//        int totalDeliver = vehiclesNum * this.shop.getFleet().getMaxLoad();
//        if(this.shop.getRemainingParcels()%this.shop.getFleet().getMaxLoad() > this.shop.getFleet().getMinLoad()){
//            vehiclesNum++;
//            totalDeliver += this.shop.getRemainingParcels()%this.shop.getFleet().getMaxLoad();
//        }
//        this.shop.deliver(totalDeliver);
        System.out.println(Thread.currentThread().getName() + "  delivered");
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
            if(temp == barrier.getParties()-1)
                System.out.printf("%10s  >>  Day  %d\n",Thread.currentThread().getName(), i);
            barrierWait();
            barrierWait();
            deliver();

        }
    }
}
