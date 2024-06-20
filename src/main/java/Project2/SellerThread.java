package Project2;

import java.util.Random;
import java.util.concurrent.*;

public class SellerThread extends Thread{
    private int maxDrop, days;
    private DeliveryShop shop;
    private CyclicBarrier barrier;

    public SellerThread(String name, int maxDrop, int days){
        super(name);
        this.maxDrop = maxDrop;
        this.days = days;
    }

    protected void setBarrier(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    synchronized private void drop(){
//        int random = new Random().nextInt(0,maxDrop+1);
//        this.shop.received(random);
        System.out.println(Thread.currentThread().getName() + "  drop");
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
            drop();
            barrierWait();
        }
    }
}
