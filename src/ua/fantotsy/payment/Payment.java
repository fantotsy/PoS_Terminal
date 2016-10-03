package ua.fantotsy.payment;

import java.util.ArrayList;
import java.util.List;

import ua.fantotsy.utils.Utils;

public class Payment {
    private int receivedPayment;
    private int change;

    public int getReceivedPayment() {
        return receivedPayment;
    }

    public int getChange() {
        return change;
    }

    public boolean isPaymentCorrect(int price, List<Integer> availableCoins, String payment) {
        List<Integer> coinsList = new ArrayList<>();
        for (int i = 0; i < availableCoins.size(); i++) {
            coinsList.add(availableCoins.get(i));
        }
        String[] paymentCoins = payment.split(",");
        int sum = 0;
        int coin;
        for (int i = 0; i < paymentCoins.length; i++) {
            coin = Integer.parseInt(paymentCoins[i]);
            if (!coinsList.contains(coin) || coinsList.size() == 0) {
                return false;
            }
            coinsList.remove(coinsList.indexOf(coin));
            sum += coin;
        }
        if (price > sum) {
            return false;
        }
        receivedPayment = sum;
        change = sum - price;
        return true;
    }

    public static Payment createPayment() {
        return new Payment();
    }

    public List<Integer> divideChangeIntoCoins() {
        return Utils.divideSumIntoCoins(change);
    }
}