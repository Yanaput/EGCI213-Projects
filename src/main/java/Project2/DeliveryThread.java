package Project2;

public class DeliveryThread extends Thread{
    private DeliveryShop shop;
    public DeliveryThread(String name, Fleet fleet){
        super(name);
        this.shop = new DeliveryShop(fleet);
    }

    @Override
    public void run(){
        System.out.println(this.getName());
    }
}
