package Project1;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.setErr(System.out); //For debugging. I used err and out stream, but the printing order is kind of strange.
        Main mainApp = new Main();
        mainApp.task();
    }

    public void task() {
        Scanner fileScanner;
        Scanner keyboardScanner = new Scanner(System.in);

        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<SalePerson> salePersonArrayList = new ArrayList<>();

        boolean fileLoaded = false;
        String fileName;
        String readLine = "";

        //----------------------------------------- products------------------------------------------------//
        fileName = "products.txt";
        while (!fileLoaded) {
            try {
                fileScanner = new Scanner(new File("src/main/java/Project1/" + fileName));
                fileLoaded = true;
                fileScanner.nextLine();
                System.out.println("Read from src/main/java/Project1/" + fileName);
                while (fileScanner.hasNext()) {
                    try {
                        readLine = fileScanner.nextLine();
                        String[] cols = readLine.split(",");
                        for (int i = 0; i < cols.length; i++) {
                            cols[i] = cols[i].trim();
                        }

                        int[] commRate = new int[5];
                        for (int i = 0; i < commRate.length; i++) {
                            if(i+3 < cols.length)
                                commRate[i] = Integer.parseInt(cols[i + 3]);
                        }
                        productArrayList.add(new Product(cols[0], cols[1], Integer.parseInt(cols[2]), commRate));
                    }catch (Exception e){
                        System.err.println(e + "\n[" + readLine + "]  -->  skip this line\n");
                    }
                }
                fileScanner.close();

            } catch (FileNotFoundException e) {
                System.err.println(e + "The system cannot find the file specified \nEnter correct file name =");
                fileName = keyboardScanner.next();
            }
        }

        for (Product i : productArrayList) {
            i.print();
        }
        System.out.println();

        //----------------------------------------- SalePersons------------------------------------------------//
        fileLoaded = false;
        fileName = "salespersons_errors.txt";
        while (!fileLoaded){
            try {
                fileScanner = new Scanner(new File("src/main/java/Project1/" + fileName));
                fileLoaded = true;
                fileScanner.nextLine();
                System.out.println("Read from src/main/java/Project1/" + fileName);

                while (fileScanner.hasNext()) {
                    try {
                        readLine = fileScanner.nextLine();
                        String[] cols = readLine.split(",");

                        if (!cols[0].equals("c") && !cols[0].equals("s"))
                            throw new InvalidInputException(": For input :\"" + cols[0].trim() + "\"");

                        for (int i = 0; i < cols.length; i++) {
                            cols[i] = cols[i].trim();
                        }

                        int[] commRate = new int[4];
                        for (int i = 0; i < commRate.length; i++) {
                            if(i+3 < cols.length)
                                commRate[i] = Integer.parseInt(cols[i + 3]);
                        }

                        int salary = (cols.length > 7) ? Integer.parseInt(cols[7].trim()) : 0;
                        salePersonArrayList.add(new SalePerson(cols[0], cols[1], cols[2], commRate, salary));
                    }catch (InvalidInputException | NumberFormatException e){
                        System.err.println(e + "\n[" + readLine + "]  -->  skip this line\n");
                    }
                }
                fileScanner.close();

            } catch (FileNotFoundException e) {
                System.err.println(e + "The system cannot find the file specified \nEnter correct file name =");
                fileName = keyboardScanner.next();
            }
        }

        for(SalePerson i: salePersonArrayList)
            i.print();

    }
}