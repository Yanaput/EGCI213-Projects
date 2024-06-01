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
                    for (int i = 0; i < cols.length; i++)
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

        for (LineHandler oneLine : productsList) {
            Product product = new Product(oneLine.getArgsString());
            productArrayList.add(product);
            product.print();
        }

        //-----------------------------------------Reimbursements & Excess----------------------------------------------//
        fileName = "reimbursements.txt";
        ArrayList<LineHandler> reimbursementsList = argsFromFile(fileName);
        ArrayList<Reimbursements> reimbursementsArrayList = new ArrayList<>();

        for (LineHandler oneLine : reimbursementsList) {
            Reimbursements reimbursements = new Reimbursements(oneLine.getArgsString());
            reimbursementsArrayList.add(reimbursements);
            reimbursements.print();
        }

        //-----------------------------------------SalePersons------------------------------------------------//
        fileName = "salespersons.txt";
        ArrayList<LineHandler> salespersonsList = argsFromFile(fileName);
        ArrayList<SalePerson> salePersonArrayList = new ArrayList<>();

        for (LineHandler oneLine : salespersonsList) {
            try {
                SalePerson saleperson = new SalePerson(oneLine.getArgsString());
                salePersonArrayList.add(saleperson);
            } catch (InvalidInputException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(e);
                System.out.printf("[%s] --> skip this line\n", oneLine.getOriginalInput());
            }
        }

        //-----------------------------------------Expenses------------------------------------------------//
        fileName = "expenses.txt";
        ArrayList<LineHandler> expensesList = argsFromFile(fileName);

        for (LineHandler oneLine : expensesList) {
            String[] cols = oneLine.getArgsString();
            boolean nameFound = false;
            for (SalePerson salePerson : salePersonArrayList)
                if (salePerson.getName().equalsIgnoreCase(cols[0])) {
                    salePerson.setExpenses(Integer.parseInt(cols[1]), Integer.parseInt(cols[2]));
                    System.out.printf("%s  >>  total = %,6d, %,6d\n",
                            oneLine.getOriginalInput(), salePerson.getTravelExpense(), salePerson.getMobileExpense()
                    );
                    nameFound = true;
                    break;
                }
            if (!nameFound)
                System.out.printf("%s  >>  not exist\n", oneLine.getOriginalInput());
        }

        //----------------------------------------Reimbursements & Excess----------------------------------------------//
        for (SalePerson saleperson : salePersonArrayList) {
            for (Reimbursements reimbursements : reimbursementsArrayList)
                if (saleperson.getType().equalsIgnoreCase(reimbursements.getType()))
                    saleperson.setExcess(reimbursements.getTravelLimit(), reimbursements.getMobileLimit());
        }


        //------------------------------------Total commission&payment------------------------------------------------//

        for (SalePerson person : salePersonArrayList) {
            for (Product product : productArrayList) {
                int totalCommission = 0;
                if (person.getProductCode().equalsIgnoreCase(product.getCode())) {
                    int[] quarterlyUnit = person.getQuarterlyUnit();
                    int[] quarterlyComm = product.getQuarterlyComm();
                    switch (person.getType().toLowerCase().charAt(0)) {
                        case ('c'):
                            for (int i = 0; i < quarterlyUnit.length; i++)
                                totalCommission += quarterlyUnit[i] * product.getPrice() * quarterlyComm[i] / 100;
                            break;
                        case ('s'):
                            totalCommission += product.getPrice() * person.getTotalUnit() * product.getFlatComm() / 100;
                            break;
                        default:
                            break;
                    }
                    person.setTotalCommission(totalCommission);
                    person.setTotalPayment();
                }
            }
        }
        System.out.println();
        System.out.println("\n" + "=".repeat(10) + " Process Payments " + "=".repeat(10) + "\n");
        for (SalePerson person : salePersonArrayList) {
            for (Product product : productArrayList) {
                if (person.getProductCode().equalsIgnoreCase(product.getCode())) {
                    System.out.printf("%-7s %-12s >> %-10s %5s %,12d baht\n", person.getName(), person.getType(), "total  salary", "=", person.getSalary());
                    System.out.printf("%23s %-42s %sQ1(%3d)   Q2(%3d)   Q3(%3d)   Q4(%3d)%n", ">>", product.getName(), " ", person.getQ1Unit(), person.getQ2Unit(), person.getQ3Unit(), person.getQ4Unit());
                    System.out.printf("%23s total  commission = %,12d baht %-5s total  = %6d units\n", ">>", person.getTotalCommission(), " ", person.getTotalUnit());
                    System.out.printf("%23s travel expense %4s %,12d baht %-5s excess = %,6d baht\n", ">>", "=", person.getTravelExpense(), " ", person.getTravelExcess());
                    System.out.printf("%23s mobile expense %4s %,12d baht %-5s excess = %,6d baht\n", ">>", "=", person.getMobileExpense(), " ", person.getMobileExcess());
                    System.out.printf("%23s total  payment  %3s %,12d baht\n\n", ">>", "=", person.getTotalPayment());
                }
            }
        }
        //------------------------------------Summary------------------------------------------------//

        System.out.println();
        System.out.println("\n" + "=".repeat(10) + " Summary " + "=".repeat(10) + "\n");
        int totalAC = 0, totalST = 0, totalRV = 0, totalPricesAC = 0, totalPricesST = 0,
                totalPricesRV = 0, highestA = 0, highestS = 0, highestR = 0;
        ArrayList<String> nameAC = new ArrayList<>();
        ArrayList<String> nameST = new ArrayList<>();
        ArrayList<String> nameRV = new ArrayList<>();

        for (SalePerson person : salePersonArrayList) {
            for (Product product : productArrayList) {
                if (person.getProductCode().equalsIgnoreCase(product.getCode())) {
                    int temp = person.getTotalUnit();
                    switch (person.getProductCode()) {
                        case ("AC"):
                            totalAC += person.getTotalUnit();
                            if (temp > highestA) {
                                highestA = temp;
                                nameAC.add(person.getName());
                            }
                            else if (temp == highestA){
                                nameAC.add(person.getName());
                            }
                            break;
                        case ("ST"):
                            totalST += person.getTotalUnit();
                            if (temp > highestS) {
                                highestS = temp;
                                nameST.add(person.getName());
                            }
                            else if (temp == highestS){
                                nameST.add(person.getName());
                            }
                            break;
                        case ("RV"):
                            totalRV += person.getTotalUnit();
                            if (temp > highestR) {
                                highestR = temp;
                                nameRV.add(person.getName());
                            }
                            else if (temp == highestR) {
                                nameRV.add(person.getName());
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        for(Product product: productArrayList){
            switch (product.getCode().toLowerCase()){
                case ("ac") -> totalPricesAC =  product.getPrice() * totalAC;
                case ("st") -> totalPricesST =  product.getPrice() * totalST;
                case ("rv") -> totalPricesRV =  product.getPrice() * totalRV;
            }
        }

        String highestAC = String.join(", ", nameAC);
        String highestST = String.join(", ", nameST);
        String highestRV = String.join(", ", nameRV);

        System.out.printf("%-6s  %13s = %4d units %5s %,6d %-2s  %18s %5s \n", "Air Conditioners", "total sales", totalAC, "=", totalPricesAC, "baht", "highest sales by ", highestAC);
        System.out.printf("%-6s  %20s = %4d units %5s %,10d %-2s  %18s %5s \n", "Smart TVs", "total sales", totalST, "=", totalPricesST, "baht", "highest sales by ", highestST);
        System.out.printf("%-6s  %16s = %4d units %5s %,10d %-2s  %18s %5s \n", "Robot Vacuums", "total sales", totalRV, "=", totalPricesRV, "baht", "highest sales by ", highestRV);
    }
}
