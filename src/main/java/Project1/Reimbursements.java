package Project1;

public class Reimbursements {
    private int travelLimit, mobileLimit;
    private String type;
    public Reimbursements(String type, int travelLimit, int mobileLimit ){
        this.type = type;
        this.travelLimit = travelLimit;
        this.mobileLimit = mobileLimit;
    }
    protected String getType(){return this.type;}
    protected int getTravelLimit(){return this.travelLimit;}
    protected int getMobileLimit(){return this.mobileLimit;}
}
