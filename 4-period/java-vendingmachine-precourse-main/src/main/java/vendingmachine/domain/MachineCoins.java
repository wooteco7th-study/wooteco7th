package vendingmachine.domain;

import vendingmachine.Coin;

import java.util.EnumMap;
import java.util.Map;

public class MachineCoins {

    private static final int INIT_AMOUNT = 0;
    private Map<Coin, Integer> coins = initCoins();

    public Map<Coin, Integer> saveCoins(int amountHeld) {
        while (amountHeld > INIT_AMOUNT) {
            int coinAmount = RandomNumberGenerator.generate(Coin.coins());
            amountHeld = putCoinIfAvailable(amountHeld, coinAmount);
        }
        return coins;
    }

    private int putCoinIfAvailable(int amountHeld, final int coinAmount) {
        if (amountHeld % coinAmount == 0) {
            Coin coin = Coin.from(coinAmount);
            amountHeld -= coinAmount;
            coins.put(coin, coins.get(coin) + 1);
        }
        return amountHeld;
    }

    private Map<Coin, Integer> initCoins() {
        Map<Coin, Integer> statistics = new EnumMap<>(Coin.class);
        for (Coin rank : Coin.values()) {
            statistics.put(rank, 0);
        }
        return statistics;
    }
}
