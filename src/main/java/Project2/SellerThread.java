package Project2;

import java.util.Random;

public class SellerThread extends Thread{
    private int maxDrop;
    private DeliveryShop shop;

    public SellerThread(String name, int maxDrop){
        super(name);
        this.maxDrop = maxDrop;
    }

    public void setShop(DeliveryShop shop){
        this.shop = shop;
    }
    private void drop(){
        int random = new Random().nextInt(0,maxDrop+1);
        this.shop.received(random);
    }

    @Override
    public void run(){

    }
}
