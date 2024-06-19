package Project2;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main mainApp = new Main();
        mainApp.task();
    }

    private ArrayList<Integer> argsFromLine(String line){
        String[] cols = line.split(",");
        ArrayList<Integer> args = new ArrayList<>();
        for(int i = 1; i < cols.length; i++)
            args.add(Integer.parseInt(cols[i].trim()));
        return args;
    }

    private void task(){
        int days = 0, bikeNum = 0, bikeMaxLoad= 0, truckNum =0, truckMaxLoad = 0, sellerNum = 0
                , maxDrop = 0, deliByBike = 0 , deliByTruck = 0;
        boolean fileLoaded = false;
        String fileName = "config_1.txt";
        while(!fileLoaded){
            try(Scanner fileScanner = new Scanner(new File("src/main/java/Project2/" + fileName))) {
                fileLoaded = true;
                ArrayList<Integer> fileValue = new ArrayList<>();
                while(fileScanner.hasNext()){
                    fileValue.addAll(argsFromLine(fileScanner.nextLine()));
                }
                days = fileValue.get(0);
                bikeNum = fileValue.get(1); bikeMaxLoad = fileValue.get(2);
                truckNum = fileValue.get(3); truckMaxLoad = fileValue.get(4);
                sellerNum = fileValue.get(5); maxDrop = fileValue.get(6);
                deliByBike = fileValue.get(7); deliByTruck = fileValue.get(8);
            }
            catch (Exception e){
                Scanner keyboardScanner = new Scanner(System.in);
                System.err.println(e + "The system cannot find the file specified \nEnter correct file name =");
                fileName =  keyboardScanner.next();
                keyboardScanner.close();
            }
        }

        ArrayList<SellerThread> sellerThreads = new ArrayList<>();
        for(int i = 0; i < sellerNum; i++){
            sellerThreads.add(new SellerThread("Seller_"+i, maxDrop));
        }

        Fleet bike = new Fleet("Bike", bikeMaxLoad, bikeNum);
        Fleet truck = new Fleet("Truck", truckMaxLoad, truckNum);

        ArrayList<DeliveryThread> deliveryThreads = new ArrayList<>();
        for(int i = 0; i < deliByBike ; i++){
            deliveryThreads.add(new DeliveryThread("BikeDelivery_"+i, bike));
        }
        for(int i = 0; i < deliByTruck ; i++){
            deliveryThreads.add(new DeliveryThread("TruckDelivery_"+i, truck));
        }

        for(DeliveryThread thread : deliveryThreads)
            thread.start();
    }
}

