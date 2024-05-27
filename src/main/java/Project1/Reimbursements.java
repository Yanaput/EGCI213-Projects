package Project1;

public class Reimbursements {
    private int travelLimit, mobileLimit;
    private String type;

    public Reimbursements(String [] args) {
        switch (args[0]) {
            case "c" -> this.type = "commission";
            case "s" -> this.type = "salary+";
            default  -> this.type = "";
        }
        this.travelLimit = Integer.parseInt(args[1]);
        this.mobileLimit = Integer.parseInt(args[2]);
    }

    protected String getType(){return this.type;}
    protected int getTravelLimit(){return this.travelLimit;}
    protected int getMobileLimit(){return this.mobileLimit;}
    public void print() {
        System.out.printf("%-15s travel limit = %,8d   mobile limit = %,8d\n",
                this.type, this.travelLimit, this.mobileLimit
        );
    }
}
