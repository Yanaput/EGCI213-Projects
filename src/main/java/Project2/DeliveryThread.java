package Project2;

import java.util.concurrent.CyclicBarrier;

public class DeliveryThread extends Thread{
    CyclicBarrier barrier;
    public DeliveryThread(String name){
        super(name);
    }

    protected void setBarrier(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run(){
        System.out.println(this.getName());
    }
}
