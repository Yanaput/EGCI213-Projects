package Project1;

public class Product {
    String code, name;
    int price, flatComm, q1Comm, q2Comm, q3Comm, q4Comm;
    public Product(String code, String name, int price, int[] comm){
        this.code = code;
        this.name = name;
        this.price = price;
        this.flatComm = comm[0];
        this.q1Comm = comm[1];
        this.q2Comm = comm[2];
        this.q3Comm = comm[3];
        this.q4Comm =  comm[4];
    }

    protected String getCode(){return this.code;}
    protected String getName(){return this.name;}
    protected int getFlatComm(){return this.flatComm;}
    protected int getQ1Comm(){return this.q1Comm;}
    protected int getQ2Comm(){return this.q2Comm;}
    protected int getQ3Comm(){return this.q3Comm;}
    protected int getQ4Comm(){return this.q4Comm;}
    protected void print(){
        System.out.printf("%s %d \n",this.name,this.price);
    }
}
