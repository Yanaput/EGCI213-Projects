package Project1;

import java.io.*;
import java.util.*;

// Input wrapper class
class LineHandler {
    public String [] argsString;
    public String originalInput;
    public LineHandler(String [] argsString, String originalInput) {
        this.argsString = argsString;
        this.originalInput = originalInput;
    }
}

public class Main {     //TODO : add printing format, assign totalPayment to each salePerson, check compare function, summary
    public static void main(String[] args) {
        //System.setErr(System.out); //For debugging. I used err and out stream, but the printing order is kind of strange.
        Main mainApp = new Main();
        mainApp.task();
    }

    private static ArrayList<LineHandler> argsFromFile(String fileName) {
        Scanner fileScanner;
        Scanner keyboardScanner = new Scanner(System.in);
        boolean fileLoaded = false;
        ArrayList<LineHandler> args = new ArrayList<>();
        while (!fileLoaded) {
            try {
                fileScanner = new Scanner(new File("src/main/java/Project1/" + fileName));
                fileLoaded = true;
                fileScanner.nextLine();
                System.out.println("Read from src/main/java/Project1/" + fileName);
                while (fileScanner.hasNext()) {
                    String readLine = fileScanner.nextLine();
                    String[] cols = readLine.split(",");
                    for (int i=0; i<cols.length; i++)
                        cols[i] = cols[i].trim();
                    LineHandler line = new LineHandler(cols, readLine);
                    args.add(line);
                }
                fileScanner.close();

            } catch (FileNotFoundException e) {
                System.err.println(e + "The system cannot find the file specified \nEnter correct file name =");
                fileName = keyboardScanner.next();
            }
        }

        return args;
    }

    public void task() {


        ArrayList<Reimbursements> reimArrayList = new ArrayList<>();

        boolean fileLoaded = false;
        String fileName;
        String readLine = "";

        //----------------------------------------- products------------------------------------------------//
        fileName = "products.txt";

        ArrayList<LineHandler> productsList = argsFromFile(fileName);
        ArrayList<Product> productArrayList = new ArrayList<>();
        for (LineHandler oneLine: productsList)
            productArrayList.add(new Product(oneLine.argsString));

        //-----------------------------------------SalePersons------------------------------------------------//
        fileName = "salespersons_errors.txt";

        ArrayList<LineHandler> salespersonsList = argsFromFile(fileName);
        ArrayList<SalePerson> salePersonArrayList = new ArrayList<>();
        for (LineHandler oneLine: salespersonsList) {
            try {
                SalePerson saleperson = new SalePerson(oneLine.argsString);
                salePersonArrayList.add(saleperson);
            }
            catch (InvalidInputException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(e);
                System.out.printf("[%s] --> skip this line\n", oneLine.originalInput);
            }
        }
        System.out.println("\nsalePerson"); //debug
        for(SalePerson i: salePersonArrayList)
            i.print();

        //-----------------------------------------Expenses------------------------------------------------//
        /*fileLoaded = false;
        fileName = "expenses.txt";
        while(!fileLoaded){
            try {
                fileScanner = new Scanner(new File("src/main/java/Project1/" + fileName));
                fileLoaded = true;
                System.out.println("Read from src/main/java/Project1/" + fileName);
                fileScanner.nextLine();

                while (fileScanner.hasNext()){
                    readLine = fileScanner.nextLine();
                    String[] cols = readLine.split(",");

                    for (int i = 0; i < cols.length; i++) {
                        cols[i] = cols[i].trim();
                    }

                    boolean nameFound = false;
                    for(SalePerson i:salePersonArrayList){
                        if(i.getName().equalsIgnoreCase(cols[0])) {
                            i.setExpenses(Integer.parseInt(cols[1]), Integer.parseInt(cols[2]));
                            System.out.printf("%s  >>  total = %,6d, %,6d\n", readLine, i.getTravelExpense(), i.getMobileExpense());
                            nameFound = true;
                        }
                    }
                    if(!nameFound){
                        System.out.printf("%s  >>  not exist\n", readLine);
                    }
                }
                fileScanner.close();
            }catch(FileNotFoundException e){
                System.err.println(e + "The system cannot find the file specified \nEnter correct file name =");
                fileName = keyboardScanner.next();
            }
        }
        //-----------------------------------------Reimbursements&Excess----------------------------------------------//
        fileLoaded = false;
        fileName = "reimbursements.txt";
        while(!fileLoaded){
            try{
                fileScanner = new Scanner(new File("src/main/java/Project1/" + fileName));
                fileLoaded = true;
                fileScanner.nextLine();

                while (fileScanner.hasNext()){
                    readLine = fileScanner.nextLine();
                    String[] cols = readLine.split(",");
                    for(int i = 0; i < cols.length; i++)
                        cols[i] = cols[i].trim();
                    reimArrayList.add(new Reimbursements(cols[0], Integer.parseInt(cols[1]), Integer.parseInt(cols[2])));
                }

                fileScanner.close();
            }catch (FileNotFoundException e){
                System.err.println(e + "The system cannot find the file specified \nEnter correct file name =");
                fileName = keyboardScanner.next();
            }
        }

        for(SalePerson i: salePersonArrayList){
            for(Reimbursements j:reimArrayList)
                if(i.getType().equalsIgnoreCase(j.getType()))
                    i.setExcess(j.getTravelLimit(), j.getMobileLimit());
        }

        System.out.println("\nexcess"); //debug
        for(SalePerson i: salePersonArrayList)
            System.out.printf("%s  %,d %,d\n", i.getName(), i.getTravelExcess(), i.getMobileExcess());

        //-----------------------------------------Total commission------------------------------------------------//

        for(SalePerson person: salePersonArrayList){
            for (Product product: productArrayList){
                double totalCommission = 0;
                if(person.getProductCode().equalsIgnoreCase(product.getCode())){
                    int[] quarterlyUnit = person.getQuarterlyUnit();
                    int[] quarterlyComm = product.getQuarterlyComm();
                    switch (person.getType().toLowerCase().charAt(0)){
                        case ('c') :
                            for(int i = 0; i < quarterlyUnit.length; i++)
                                totalCommission += quarterlyUnit[i]* product.getPrice()*((double) quarterlyComm[i] /100);
                            break;
                        case ('s') :
                            totalCommission += person.getSalary();
                            for (int j : quarterlyUnit)
                                totalCommission += j * ((double) product.getFlatComm() / 100);
                            break;
                        default: break;
                    }
                    person.setTotalCommission((int)totalCommission);
                }
            }
        }
        System.out.println();
        System.out.println("\ntotal commission"); //debug
        for(SalePerson i: salePersonArrayList)
            System.out.printf("%s  %,d \n", i.getName(), i.getTotalCommission());

        keyboardScanner.close();*/
    }
}