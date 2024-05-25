package Project1;

public class SalePerson {
    private String type, name, product;
    private int  q1Comm, q2Comm, q3Comm, q4Comm, salary;
    public SalePerson(String type, String name, String product, int[] comm, int salary) throws InvalidInputException{
        //-----------------------------------------Exception cases------------------------------------------------//
        if(!type.equalsIgnoreCase("c") && !type.equalsIgnoreCase("s"))
            throw new InvalidInputException(": For input :\"" + type + "\"");
        if(!product.equalsIgnoreCase("ac") && !product.equalsIgnoreCase("st")
            && !product.equalsIgnoreCase("rv"))
            throw new InvalidInputException(": For input :\"" + product + "\"");
        for(int i: comm)
            if(i < 0)
                throw new InvalidInputException(": For input :\"" + i + "\"");
        if (salary < 0)
            throw new InvalidInputException(": For input :\"" + salary + "\"");

        //-----------------------------------------Assign values------------------------------------------------//
        this.type = type;
        this.name = name;
        this.product = product;
        this.q1Comm = comm[0];
        this.q2Comm = comm[1];
        this.q3Comm = comm[2];
        this.q4Comm = comm[3];
        this.salary = salary;
    }

    protected String getType(){return this.type;}
    protected String getName(){return this.name;}
    protected String getProduct(){return this.product;}
    protected int getQ1Comm(){return this.q1Comm;}
    protected int getQ2Comm(){return this.q2Comm;}
    protected int getQ3Comm(){return this.q3Comm;}
    protected int getQ4Comm(){return this.q4Comm;}
    protected int getSalary(){return this.salary;}

    public void print(){// TODO : add printing format
        System.out.printf("%s %s %d\n",this.name, this.type, this.salary);
    }
}
