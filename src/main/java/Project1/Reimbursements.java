package Project1;

public class Reimbursements {
    private int travelLimit, mobileLimit;
    private String type;
    public Reimbursements(String type, int travelLimit, int mobileLimit ){
        if(type.equalsIgnoreCase("c")){
            this.type = "commission";
        }
        else if(type.equalsIgnoreCase("s")){
            this.type = "salary+";
        }
        this.travelLimit = travelLimit;
        this.mobileLimit = mobileLimit;
    }
    protected String getType(){return this.type;}

    protected int getTravelLimit(){return this.travelLimit;}
    protected int getMobileLimit(){return this.mobileLimit;}
}
