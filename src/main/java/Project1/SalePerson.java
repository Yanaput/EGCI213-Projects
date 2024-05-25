package Project1;

public class SalePerson {
    private String type, name;
    public SalePerson(String type, String name){
        this.type = type;
        this.name = name;
    }

    public void print(){
        System.out.printf("%s %s \n",this.name, this.type);
    }

}
