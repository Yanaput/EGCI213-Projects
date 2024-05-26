package Project1;

import java.util.Arrays;

public class SalePerson implements Comparable<SalePerson> {
    private String type, name, product;
    private int q1Unit, q2Unit, q3Unit, q4Unit, salary, totalUnit, travelExpense, mobileExpense, travelExcess,
            mobileExcess, totalCommission, totalPayment;
    public SalePerson(String type, String name, String product, int[] unitSold, int salary) throws InvalidInputException{
        //-----------------------------------------Exception cases------------------------------------------------//
        if(!product.equalsIgnoreCase("ac") && !product.equalsIgnoreCase("st")
            && !product.equalsIgnoreCase("rv"))
            throw new InvalidInputException(": For input :\"" + product + "\"");
        for(int i: unitSold)
            if(i < 0)
                throw new InvalidInputException(": For input :\"" + i + "\"");
        if (salary < 0)
            throw new InvalidInputException(": For input :\"" + salary + "\"");

        //-----------------------------------------Assign values------------------------------------------------//
        if(type.equalsIgnoreCase("c")){
            this.type = "commission";
        }
        else if(type.equalsIgnoreCase("s")){
            this.type = "salary+";
        }
        else
            throw new InvalidInputException(": For input :\"" + type + "\"");

        this.name = name;
        this.product = product;
        this.q1Unit = unitSold[0];
        this.q2Unit = unitSold[1];
        this.q3Unit = unitSold[2];
        this.q4Unit = unitSold[3];
        this.salary = salary;
        this.totalUnit = Arrays.stream(unitSold).sum();
    }
    //-----------------------------------------Functions------------------------------------------------//
    protected String getType(){return this.type;}
    protected String getName(){return this.name;}
    protected String getProduct(){return this.product;}
    protected int getQ1Unit(){return this.q1Unit;}
    protected int getQ2Unit(){return this.q2Unit;}
    protected int getQ3Unit(){return this.q3Unit;}
    protected int getQ4Unit(){return this.q4Unit;}
    protected int[] getQuarterlyUnit(){return new int[]{this.q1Unit, this.q2Unit, this.q3Unit, this.q4Unit};}

    protected int getSalary(){return this.salary;}
    protected int getTotalUnit(){return this.totalUnit;}
    protected int getTravelExpense(){return this.travelExpense;}
    protected int getMobileExpense(){return this.mobileExpense;}
    protected int getTravelExcess(){return this.travelExcess;}
    protected int getMobileExcess(){return this.mobileExcess;}
    protected int getTotalCommission(){return this.totalCommission;}
    protected void setExpenses(int travelExpense, int mobileExpense){
        this.travelExpense += travelExpense;
        this.mobileExpense += mobileExpense;
    }

    protected void setExcess(int travelExcess, int mobileExcess){
        this.travelExcess = (this.travelExpense > travelExcess) ? this.travelExpense - travelExcess : 0;
        this.mobileExcess = (this.mobileExpense > mobileExcess) ? this.mobileExpense - mobileExcess : 0;
    }

    protected void setTotalCommission(int totalCommission){this.totalCommission = totalCommission;}

    @Override
    public int compareTo(SalePerson other){
        return Integer.compare(this.totalUnit, other.totalUnit);
    }

    public void print(){// TODO : add printing format
        System.out.printf("%s %s %d %d %d %d\n",this.name, this.type, this.salary, this.totalUnit, this.travelExpense, this.mobileExpense);
    }
}
