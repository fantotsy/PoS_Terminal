package ua.fantotsy.utils;

import ua.fantotsy.Terminal;

import java.util.ArrayList;
import java.util.List;

public final class Utils {

    public static List<Integer> divideSumIntoCoins(int sum) {
        List<Integer> result = new ArrayList<>();
        int numberOfAvailableCoins = Terminal.availableCoins.length;
        int availableQuantityOfCertainCoin = sum;

        for (int i = (numberOfAvailableCoins - 1); i >= 0; i--) {
            availableQuantityOfCertainCoin = sum;
            availableQuantityOfCertainCoin /= Terminal.availableCoins[i];
            for (int j = 0; j < availableQuantityOfCertainCoin; j++) {
                result.add(Terminal.availableCoins[i]);
            }
            sum -= availableQuantityOfCertainCoin * Terminal.availableCoins[i];
        }
        return result;
    }
}