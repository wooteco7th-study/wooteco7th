package vendingmachine.domain;

import vendingmachine.Coin;
import vendingmachine.dto.response.MachineCoinsResponse;

import java.util.EnumMap;
import java.util.Map;

import static vendingmachine.Coin.COIN_10;
import static vendingmachine.Coin.COIN_100;
import static vendingmachine.Coin.COIN_50;
import static vendingmachine.Coin.COIN_500;
import static vendingmachine.Coin.coins;
import static vendingmachine.Coin.values;

public class MachineCoins {

    private int amountHeld;
    private Map<Coin, Integer> coins = initCoins();

    public MachineCoins(final int amountHeld) {
        this.amountHeld = amountHeld;
    }

    public Map<Coin, Integer> saveCoins() {
        while (amountHeld > 0) {
            int coinAmount = RandomNumberGenerator.generate(coins());
            Coin coin = Coin.from(coinAmount);
            amountHeld -= coinAmount;
            coins.put(coin, coins.get(coin) + 1);
        }
        return coins;
    }

    public Map<Coin, Integer> update(final int orderAmount) {
        if (orderAmount == 0) {
            return initCoins();
        }
        Map<Coin, Integer> changes = new EnumMap<>(Coin.class);
        for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
            if (entry.getValue() > 0) {
                Coin coin = entry.getKey();
                int quantity = orderAmount / coin.getAmount();
                if (quantity > entry.getValue()) {
                    changes.put(coin, entry.getValue());
                }
                if (quantity <= entry.getValue()) {
                    changes.put(coin, quantity);
                }
            }
        }
        return changes;
    }

    public MachineCoinsResponse toResponse() {
        return new MachineCoinsResponse(
                coins.get(COIN_500),
                coins.get(COIN_100),
                coins.get(COIN_50),
                coins.get(COIN_10)
        );
    }

    private Map<Coin, Integer> initCoins() {
        Map<Coin, Integer> statistics = new EnumMap<>(Coin.class);
        for (Coin coin : values()) {
            statistics.put(coin, 0);
        }
        return statistics;
    }
}
