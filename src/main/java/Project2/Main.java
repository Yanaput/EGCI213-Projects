package Project2;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Main mainApp = new Main();
        mainApp.task();
    }

    private ArrayList<Integer> argsFromLine(String line) {
        String[] cols = line.split(",");
        ArrayList<Integer> args = new ArrayList<>();
        for (int i = 1; i < cols.length; i++)
            args.add(Integer.parseInt(cols[i].trim()));
        return args;
    }

    private void task() {
        int days = 0, bikeNum = 0, bikeMaxLoad = 0, truckNum = 0, truckMaxLoad = 0, sellerNum = 0, maxDrop = 0, deliByBike = 0, deliByTruck = 0;
        boolean fileLoaded = false;
        String fileName = "config_1.txt";
        while (!fileLoaded) {
            try (Scanner fileScanner = new Scanner(new File("src/main/java/Project2/" + fileName))) {
                fileLoaded = true;
                ArrayList<Integer> fileValue = new ArrayList<>();
                while (fileScanner.hasNext())
                    fileValue.addAll(argsFromLine(fileScanner.nextLine()));

                days = fileValue.get(0);
                bikeNum = fileValue.get(1);
                bikeMaxLoad = fileValue.get(2);
                truckNum = fileValue.get(3);
                truckMaxLoad = fileValue.get(4);
                sellerNum = fileValue.get(5);
                maxDrop = fileValue.get(6);
                deliByBike = fileValue.get(7);
                deliByTruck = fileValue.get(8);
            } catch (Exception e) {
                Scanner keyboardScanner = new Scanner(System.in);
                System.err.println(e + "The system cannot find the file specified \nEnter correct file name =");
                fileName = keyboardScanner.next();
                keyboardScanner.close();
            }
        }

        CyclicBarrier barrier = new CyclicBarrier(deliByBike + deliByTruck + sellerNum +1);


        Fleet bike = new Fleet("Bike", bikeMaxLoad, bikeNum);
        Fleet truck = new Fleet("Truck", truckMaxLoad, truckNum);

        ArrayList<DeliveryShop> shopArrayList = new ArrayList<>();
        for (int i = 0; i < deliByBike; i++)
            shopArrayList.add(new DeliveryShop("BikeDelivery_" + i, bike, barrier, days));

        for (int i = 0; i < deliByTruck; i++)
            shopArrayList.add(new DeliveryShop("TruckDelivery_" + i, truck, barrier, days));

        for (DeliveryShop shop : shopArrayList)
            shop.getThread().start();

        ArrayList<SellerThread> sellerThreads = new ArrayList<>();
        for (int i = 0; i < sellerNum; i++) {
            sellerThreads.add(new SellerThread("Seller_" + i, maxDrop, days, shopArrayList));
        }
        for (SellerThread thread : sellerThreads)
            thread.setBarrier(barrier);

        for (SellerThread thread : sellerThreads)
            thread.start();

        for(int i = 1; i <= days; i++) {
            int temp = -1;
            try {
                temp = barrier.await();
            } catch (Exception e) { }

            if (temp == barrier.getParties() - 1){
                System.out.printf("%15s  >> %s\n",Thread.currentThread().getName(), "=".repeat(15));
                System.out.printf("%15s  >>  Day  %d\n", Thread.currentThread().getName(), i);
            }
            try { //wait for seller drop
                barrier.await();
            } catch (Exception e) { }

            try { //wait for delivery report
                barrier.await();
            } catch (Exception e) { }

            try { //wait for delivery deliver
                barrier.await();
            } catch (Exception e) { }
        }

    }
}

