package Project1;

public class Product {
    private String code, name;
    private int price, flatComm, q1Comm, q2Comm, q3Comm, q4Comm;

    public Product(String [] params) {
        this.code = params[0];
        this.name = params[1];
        this.price = Integer.parseInt(params[2]);
        this.flatComm = Integer.parseInt(params[3]);
        this.q1Comm = Integer.parseInt(params[4]);
        this.q2Comm = Integer.parseInt(params[5]);
        this.q3Comm = Integer.parseInt(params[6]);
        this.q4Comm = Integer.parseInt(params[7]);
    }

    protected String getCode(){return this.code;}
    protected String getName(){return this.name;}
    protected int getFlatComm(){return this.flatComm;}
    protected int getQ1Comm(){return this.q1Comm;}
    protected int getQ2Comm(){return this.q2Comm;}
    protected int getQ3Comm(){return this.q3Comm;}
    protected int getQ4Comm(){return this.q4Comm;}
    protected int getPrice(){return this.price;}
    protected int[] getQuarterlyComm(){return new int[]{this.q1Comm, this.q2Comm, this.q3Comm, this.q4Comm};}

    protected void print(){
        System.out.printf(
                "%-20s (%2s)    unit price = %,6d   commissions >> flat = %3d%%   Q1 = %3d%%   Q2 = %3d%%   Q3 = %3d%%   Q4 = %3d%% \n",
                this.name, this.code, this.price, this.flatComm, this.q1Comm, this.q2Comm, this.q3Comm, this.q4Comm
        );
    }
}
