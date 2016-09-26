package ua.fantotsy.payment;

import ua.fantotsy.utils.Utils;

import java.util.List;

public class Pocket {
    private int balance;
    private int pocketNumber;
    private List<Integer> coins;

    public Pocket(int pocketNumber) {
        balance = getBalanceOfCertainPocket(pocketNumber);
        this.pocketNumber = pocketNumber;
        List<Integer> coins = getAvailableCoins(balance);
        this.coins = coins;
    }

    public int getBalance() {
        return balance;
    }

    public void increaseBalance(int delta) {
        balance += delta;
        List<Integer> coins = getAvailableCoins(balance);
        this.coins = coins;
    }

    public void decreaseBalance(int delta) {
        balance -= delta;
        List<Integer> coins = getAvailableCoins(balance);
        this.coins = coins;
    }

    public int getPocketNumber() {
        return pocketNumber;
    }

    public List<Integer> getCoins() {
        return coins;
    }

    private int getBalanceOfCertainPocket(int pocketNumber) {
        int number = pocketNumber;
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    private List<Integer> getAvailableCoins(int balance) {
        return Utils.divideSumIntoCoins(balance);
    }
}