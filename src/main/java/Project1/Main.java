package Project1;

import java.io.*;
import java.util.*;

// Input wrapper class
class LineHandler {
    private String [] argsString;
    private String originalInput;
    public LineHandler(String [] argsString, String originalInput) {
        this.argsString = argsString;
        this.originalInput = originalInput;
    }
    public String [] getArgsString() {return this.argsString;}
    public String getOriginalInput() {return this.originalInput;}
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
                System.out.println("\nRead from src/main/java/Project1/" + fileName);
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

        //----------------------------------------- products------------------------------------------------//
        String fileName = "products.txt";
        ArrayList<LineHandler> productsList = argsFromFile(fileName);
        ArrayList<Product> productArrayList = new ArrayList<>();

        for (LineHandler oneLine: productsList) {
            Product product = new Product(oneLine.getArgsString());
            productArrayList.add(product);
            product.print();
        }

        //-----------------------------------------Reimbursements & Excess----------------------------------------------//
        fileName = "reimbursements.txt";
        ArrayList<LineHandler> reimbursementsList = argsFromFile(fileName);
        ArrayList<Reimbursements> reimbursementsArrayList = new ArrayList<>();

        for(LineHandler oneLine : reimbursementsList) {
            Reimbursements reimbursements = new Reimbursements(oneLine.getArgsString());
            reimbursementsArrayList.add(reimbursements);
            reimbursements.print();
        }

        //-----------------------------------------SalePersons------------------------------------------------//
        fileName = "salespersons_errors.txt";
        ArrayList<LineHandler> salespersonsList = argsFromFile(fileName);
        ArrayList<SalePerson> salePersonArrayList = new ArrayList<>();

        for (LineHandler oneLine: salespersonsList) {
            try {
                SalePerson saleperson = new SalePerson(oneLine.getArgsString());
                salePersonArrayList.add(saleperson);
                saleperson.print();
            }
            catch (InvalidInputException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(e);
                System.out.printf("[%s] --> skip this line\n", oneLine.getOriginalInput());
            }
        }

        //-----------------------------------------Expenses------------------------------------------------//
        fileName = "expenses.txt";
        ArrayList<LineHandler> expensesList = argsFromFile(fileName);

        for (LineHandler oneLine: expensesList) {
            String [] cols = oneLine.getArgsString();
            boolean nameFound = false;
            for(SalePerson salePerson : salePersonArrayList)
                if(salePerson.getName().equalsIgnoreCase(cols[0])) {
                    salePerson.setExpenses(Integer.parseInt(cols[1]), Integer.parseInt(cols[2]));
                    System.out.printf("%s  >>  total = %,6d, %,6d\n",
                            oneLine.getOriginalInput(), salePerson.getTravelExpense(), salePerson.getMobileExpense()
                    );
                    nameFound = true; break;
                }
            if(!nameFound)
                System.out.printf("%s  >>  not exist\n", oneLine.getOriginalInput());
        }

        //----------------------------------------Reimbursements & Excess----------------------------------------------//
        for(SalePerson saleperson : salePersonArrayList){
            for(Reimbursements reimbursements : reimbursementsArrayList)
                if(saleperson.getType().equalsIgnoreCase(reimbursements.getType()))
                    saleperson.setExcess(reimbursements.getTravelLimit(), reimbursements.getMobileLimit());
        }

        System.out.println("\nexcess"); //debug
        for(SalePerson i: salePersonArrayList)
            System.out.printf("%-10s  %,-10d %,-10d\n", i.getName(), i.getTravelExcess(), i.getMobileExcess());


        //------------------------------------Total commission&payment------------------------------------------------//

        for(SalePerson person: salePersonArrayList){
            for (Product product: productArrayList){
                int totalCommission = 0;
                if(person.getProductCode().equalsIgnoreCase(product.getCode())){
                    int[] quarterlyUnit = person.getQuarterlyUnit();
                    int[] quarterlyComm = product.getQuarterlyComm();
                    switch (person.getType().toLowerCase().charAt(0)){
                        case ('c') :
                            for(int i = 0; i < quarterlyUnit.length; i++)
                                totalCommission +=  quarterlyUnit[i]* product.getPrice() * quarterlyComm[i] /100;
                            break;
                        case ('s') :
                            totalCommission += product.getPrice() * person.getTotalUnit() * product.getFlatComm() / 100;
                            break;
                        default: break;
                    }
                    person.setTotalCommission(totalCommission);
                    person.setTotalPayment();
                }
            }
        }
        System.out.println();
        System.out.println("\ntotal commission"); //debug
        for(SalePerson person: salePersonArrayList)
            System.out.printf("%-10s  total comm = %,-10d total pay = %,-10d\n", person.getName(), person.getTotalCommission(), person.getTotalPayment());

        Collections.sort(salePersonArrayList);
        System.out.println();
        for(SalePerson person: salePersonArrayList){
                System.out.printf("%-10s %-5s %,-5d unit\n", person.getName(), person.getProductCode(), person.getTotalUnit());
        }
    }
}