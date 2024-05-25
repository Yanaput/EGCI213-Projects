package Project1;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main mainApp = new Main();
        mainApp.task();
    }

    public void task(){
        Scanner fileScanner;
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        ArrayList<SalePerson> salePersonArrayList = new ArrayList<SalePerson>();
        //----------------------------------------- products------------------------------------------------//

        try {
            fileScanner = new Scanner(new File("src/main/java/Project1/products.txt"));
            fileScanner.nextLine();
            System.out.println("Read from src/main/java/Project1/products.txt");

            while (fileScanner.hasNext()) {
                String readLine = fileScanner.nextLine();
                String[] cols = readLine.split(",");
                for (int i = 0; i < cols.length; i++) {
                    cols[i] = cols[i].trim();
                }

                int[] commRate = {Integer.parseInt(cols[3]), Integer.parseInt(cols[4]), Integer.parseInt(cols[5]),
                        Integer.parseInt(cols[6]), Integer.parseInt(cols[7])};

                productArrayList.add(new Product(cols[0], cols[1], Integer.parseInt(cols[2]), commRate));
            }
            fileScanner.close();
        }
        catch (Exception e){
            System.err.println(e);
        }

        for (Product i : productArrayList)
            i.print();

        //----------------------------------------- SalePersons------------------------------------------------//
        try {

            fileScanner = new Scanner(new File("src/main/java/Project1/salespersons.txt"));
            fileScanner.nextLine();
            System.out.println("Read from src/main/java/Project1/salespersons.txt");


            while (fileScanner.hasNext()) {
                String readLine = fileScanner.nextLine();
                String[] cols = readLine.split(",");
                for (int i = 0; i < cols.length; i++) {
                    cols[i] = cols[i].trim();
                }
                salePersonArrayList.add(new SalePerson(cols[0], cols[1]));
            }
            fileScanner.close();
        }
        catch(Exception e){
            System.err.println(e);
        }

        for(SalePerson i: salePersonArrayList)
            i.print();
    }
}