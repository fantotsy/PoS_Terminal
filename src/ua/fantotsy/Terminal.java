package ua.fantotsy;

import ua.fantotsy.payment.Payment;
import ua.fantotsy.payment.Pocket;
import ua.fantotsy.products.Coffee;
import ua.fantotsy.products.Juice;
import ua.fantotsy.products.Product;
import ua.fantotsy.products.Tea;

import java.util.List;
import java.util.Scanner;

public final class Terminal {
    public final static int[] availableCoins = new int[]{1, 5, 10, 25, 50};

    public static void main(String[] args) {
        int command = 1;
        Scanner in = new Scanner(System.in);
        while (command == 1) {
            int pocketNumber = askForPocketNumber(in);
            Pocket pocket = Pocket.createPocket(pocketNumber);
            printStartMenuForCertainPocket(pocket);
            Sale sale = Sale.createSale();
            int choice = askForNeededProduct(in);
            while (choice != 0) {
                if (choice == -1) {
                    sale.cancelLastOrder();
                } else {
                    Product product;
                    if (choice == 1) {
                        product = new Tea();
                    } else if (choice == 2) {
                        product = new Coffee();
                    } else {
                        product = new Juice();
                    }
                    int quantity = askForNeededQuantity(in, product);
                    sale.makeLineItem(product, quantity);
                }
                choice = askForNeededProduct(in);
            }
            printSaleInfo(sale);
            Payment payment = Payment.createPayment();
            if (askForPaymentAndCheckIt(in, sale, pocket, payment)) {
                printChangeInfo(payment.divideChangeIntoCoins());
                doTransaction(pocket, payment);
            }
            command = askForCommand(in);
        }
        in.close();
    }

    private static int askForPocketNumber(Scanner in) {
        System.out.print("Enter your pocket number: ");
        int pocketNumber = in.nextInt();
        while (!(pocketNumber >= 100000000 && pocketNumber <= 999999999)) {
            pocketNumber = in.nextInt();
        }
        return pocketNumber;
    }

    private static void printStartMenuForCertainPocket(Pocket pocket) {
        int balance = pocket.getBalance();
        StringBuilder menu = new StringBuilder();
        menu.append("----------" + "Tea/Coffee/Juice" + "----------\n");
        menu.append("Your balance: " + balance + "\n");
        menu.append("Your coins: ");
        List<Integer> coins = pocket.getCoins();
        for (int i = 0; i < coins.size(); i++) {
            menu.append("" + coins.get(i) + ",");
        }
        menu.append("\nPress '-1' to cancel last order.");
        menu.append("\nPress '0' to exit.");
        System.out.println(menu.toString());
    }

    private static int askForNeededProduct(Scanner in) {
        StringBuilder menu = new StringBuilder();
        menu.append("\nTea: 25 (Press 1)\n");
        menu.append("Coffee: 35 (Press 2)\n");
        menu.append("Juice: 45 (Press 3)\n");
        menu.append("Make your choice: ");
        System.out.print(menu.toString());
        int choice = in.nextInt();
        while (!(choice >= -1 && choice <= 3)) {
            choice = in.nextInt();
        }
        return choice;
    }

    private static int askForNeededQuantity(Scanner in, Product product) {
        System.out.print("Choose quantity of " + product.getClass().getSimpleName() + " (maximum 5): ");
        int quantity = in.nextInt();
        while (!(quantity >= 1 && quantity <= 5)) {
            quantity = in.nextInt();
        }
        System.out.println();
        return quantity;
    }

    private static void printSaleInfo(Sale sale) {
        StringBuilder saleInfo = new StringBuilder();
        List<SalesLineItem> salesLineItems = sale.getSalesLineItems();
        saleInfo.append("\nYou've ordered:\n");
        for (int i = 0; i < sale.getSalesLineItems().size(); i++) {
            saleInfo.append(salesLineItems.get(i).toString());
        }
        System.out.println(saleInfo);
    }

    private static boolean askForPaymentAndCheckIt(Scanner in, Sale sale, Pocket pocket, Payment payment) {
        System.out.println("In total: " + sale.total());
        System.out.print("Payment (Press '0' to cancel order): ");
        String actualPayment = in.next();
        while (!payment.isPaymentCorrect(sale.total(), pocket.getCoins(), actualPayment) && (!actualPayment.equals("0"))) {
            System.out.println("Wrong payment! Try again.");
            System.out.print("Payment (Press '0' to cancel order): ");
            actualPayment = in.next();
        }
        if (actualPayment.equals("0")) {
            return false;
        }
        return true;
    }

    private static void printChangeInfo(List<Integer> change) {
        StringBuilder changeDeliveryMenu = new StringBuilder();
        changeDeliveryMenu.append("Your change: ");
        for (int i = 0; i < change.size(); i++) {
            changeDeliveryMenu.append(change.get(i) + ",");
        }
        System.out.println(changeDeliveryMenu.toString());
    }

    private static void doTransaction(Pocket pocket, Payment payment) {
        pocket.decreaseBalance(payment.getReceivedPayment());
        pocket.increaseBalance(payment.getChange());
        System.out.println("\nThanks for your purchase!\n");
    }

    private static int askForCommand(Scanner in) {
        StringBuilder exitInfo = new StringBuilder();
        exitInfo.append("Press '1' if you want to make another purchase;\n");
        exitInfo.append("Press '0' if you want to exit.");
        System.out.println(exitInfo);
        int command = in.nextInt();
        while (!(command >= 0 && command <= 1)) {
            command = in.nextInt();
        }
        return command;
    }
}