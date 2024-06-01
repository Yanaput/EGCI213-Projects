package Project1;

import java.util.Arrays;

public class SalePerson implements Comparable<SalePerson> {
    private String type, name, productCode;
    private int q1Unit, q2Unit, q3Unit, q4Unit, salary, totalUnit, travelExpense, mobileExpense, travelExcess,
            mobileExcess, totalCommission, totalPayment;

    private int parseIntNonNegative(String str) throws InvalidInputException {
        int n = Integer.parseInt(str);
        if (n < 0) throw new InvalidInputException(": For input :\"" + str + "\"");
        return n;
    }

    public SalePerson(String[] args) throws InvalidInputException {
        switch (args[0].toLowerCase()) {
            case "c" -> this.type = "commission";
            case "s" -> this.type = "salary+";
            default -> throw new InvalidInputException(": For input :\"" + args[0] + "\"");
        }

        this.name = args[1];

        switch (args[2].toLowerCase()) {
            case "ac":
            case "st":
            case "rv":
                this.productCode = args[2].toUpperCase();
                break;
            default:
                throw new InvalidInputException(": For input :\"" + args[2] + "\"");
        }
        this.q1Unit = parseIntNonNegative(args[3]);
        this.q2Unit = parseIntNonNegative(args[4]);
        this.q3Unit = parseIntNonNegative(args[5]);
        this.q4Unit = parseIntNonNegative(args[6]);
        this.salary = this.type.equalsIgnoreCase("salary+") ? parseIntNonNegative(args[7]) : 0;
        this.totalUnit = q1Unit + q2Unit + q3Unit + q4Unit;
    }

    //-----------------------------------------Functions------------------------------------------------//
    protected String getType(){return this.type;}
    protected String getName(){return this.name;}
    protected String getProductCode(){return this.productCode;}
    protected int getQ1Unit(){return this.q1Unit;}
    protected int getQ2Unit(){return this.q2Unit;}
    protected int getQ3Unit(){return this.q3Unit;}
    protected int getQ4Unit(){return this.q4Unit;}
    protected int[] getQuarterlyUnit(){return new int[]{this.q1Unit, this.q2Unit, this.q3Unit, this.q4Unit};}

    protected int getSalary(){return this.salary*12;}
    protected int getTotalUnit(){return this.totalUnit;}
    protected int getTravelExpense(){return this.travelExpense;}
    protected int getMobileExpense(){return this.mobileExpense;}
    protected int getTravelExcess(){return this.travelExcess;}
    protected int getMobileExcess(){return this.mobileExcess;}
    protected int getTotalCommission(){return this.totalCommission;}
    protected int getTotalPayment(){return this.totalPayment;}
    protected void setExpenses(int travelExpense, int mobileExpense){
        this.travelExpense += travelExpense;
        this.mobileExpense += mobileExpense;
    }

    protected void setExcess(int travelExcess, int mobileExcess){
        this.travelExcess = (this.travelExpense > travelExcess) ? this.travelExpense - travelExcess : 0;
        this.mobileExcess = (this.mobileExpense > mobileExcess) ? this.mobileExpense - mobileExcess : 0;
    }

    protected void setTotalPayment(){
        this.totalPayment = this.salary * 12 + this.totalCommission - this.mobileExcess - this.travelExcess;
    }
    protected void setTotalCommission(int totalCommission){this.totalCommission = totalCommission;}


    @Override
    public int compareTo(SalePerson other){return Integer.compare(other.totalUnit, this.totalUnit);}

    public void print(){// TODO : add printing format
        System.out.printf("%s %s %d %d %d %d\n",this.name, this.type, this.salary, this.totalUnit, this.travelExpense, this.mobileExpense);
    }
}
