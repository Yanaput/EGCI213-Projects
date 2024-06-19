package Project2;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class SellerThread extends Thread{
    private int maxDrop;
    private DeliveryShop shop;
    private CyclicBarrier barrier;

    public SellerThread(String name, int maxDrop){
        super(name);
        this.maxDrop = maxDrop;
    }

    protected void setBarrier(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    private void drop(){
        int random = new Random().nextInt(0,maxDrop+1);
        this.shop.received(random);
    }

    @Override
    public void run(){
        System.out.println(this.getName());
    }
}
