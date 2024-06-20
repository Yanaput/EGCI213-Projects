package Project2;

public class Fleet {
    private String vehicle;
    private int maxLoad, minLoad, totalVehicle;
    public Fleet(String vehicle, int maxLoad, int totalVehicle){
        this.vehicle = vehicle;
        this.maxLoad = maxLoad;
        this.minLoad = maxLoad/2;
        this.totalVehicle = totalVehicle;
    }

    protected int getMaxLoad(){return this.maxLoad;}
    protected int getMinLoad(){return this.minLoad;}
    protected int getTotalVehicle(){return this.totalVehicle;}

}
