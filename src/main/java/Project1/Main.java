package Project1;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main mainApp = new Main();
        mainApp.task();
    }

    public void task(){
        try{
            //----------------------------------------- products------------------------------------------------//

            Scanner productScanner = new Scanner(new File("src/main/java/Project1/products.txt"));
            productScanner.nextLine();
            System.out.println("Read from src/main/java/Project1/products.txt");
            ArrayList<Product> productArrayList = new ArrayList<Product>();

            while(productScanner.hasNext()){
                String readLine = productScanner.nextLine();
                String[] cols = readLine.split(",");
                for(int i = 0 ;i < cols.length ;i++){
                    cols[i] = cols[i].trim();
                }
                int[] commRate = {Integer.parseInt(cols[3]), Integer.parseInt(cols[4]), Integer.parseInt(cols[5]),
                        Integer.parseInt(cols[6]), Integer.parseInt(cols[7])};

                productArrayList.add(new Product(cols[0], cols[1], Integer.parseInt(cols[2]) , commRate));

            }

            for(Product i: productArrayList)
                i.print();

            //----------------------------------------- alePersons------------------------------------------------//

            Scanner salePersonScanner = new Scanner(new File("src/main/java/Project1/products.txt"));
            salePersonScanner.nextLine();
            System.out.println("Read from src/main/java/Project1/products.txt");
            ArrayList<SalePerson> salePersonArrayList = new ArrayList<SalePerson>();

            while (productScanner.hasNext()){
                String readLine = productScanner.nextLine();
                String[] cols = readLine.split(",");
                for(int i = 0 ;i < cols.length ;i++){
                    cols[i] = cols[i].trim();
                }

            }

            salePersonScanner.close();
            productScanner.close();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}