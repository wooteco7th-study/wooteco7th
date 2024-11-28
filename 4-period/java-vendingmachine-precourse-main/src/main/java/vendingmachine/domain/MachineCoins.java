package vendingmachine.domain;

import vendingmachine.dto.response.MachineCoinsResponse;

import java.util.EnumMap;
import java.util.Map;

import static vendingmachine.domain.Coin.COIN_10;
import static vendingmachine.domain.Coin.COIN_100;
import static vendingmachine.domain.Coin.COIN_50;
import static vendingmachine.domain.Coin.COIN_500;
import static vendingmachine.domain.Coin.coins;
import static vendingmachine.domain.Coin.values;
import static vendingmachine.exception.ExceptionMessage.REMAINDER_EXIST;

public class MachineCoins {

    private int amountHeld;
    private Map<Coin, Integer> coins = initCoins();

    public MachineCoins(final int amountHeld) {
        validateAmountHold(amountHeld);
        this.amountHeld = amountHeld;
    }

    public void saveCoins() {
        while (amountHeld >= Coin.getLowestCoin()) {
            int coinAmount = RandomNumberGenerator.generate(coins());
            if (amountHeld >= coinAmount) {
                Coin coin = Coin.from(coinAmount);
                amountHeld -= coinAmount;
                coins.merge(coin, 1, Integer::sum);
            }
        }
    }

    public Map<Coin, Integer> getChange(final int orderAmount) {
        Map<Coin, Integer> changes = new EnumMap<>(Coin.class);
        updateChange(orderAmount, changes);
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

    private void updateChange(final int orderAmount, final Map<Coin, Integer> changes) {
        coins.forEach((coin, availableQuantity) -> {
            int needQuantity = orderAmount / coin.getAmount();

            if (needQuantity > availableQuantity) {
                changes.put(coin, availableQuantity);
            }
            if (needQuantity <= availableQuantity) {
                changes.put(coin, needQuantity);
            }
        });
    }

    private Map<Coin, Integer> initCoins() {
        Map<Coin, Integer> statistics = new EnumMap<>(Coin.class);
        for (Coin coin : values()) {
            statistics.put(coin, 0);
        }
        return statistics;
    }

    private void validateAmountHold(final int amountHeld) {
        if (amountHeld % Coin.getLowestCoin() != 0) {
            throw new IllegalArgumentException(REMAINDER_EXIST.getMessage());
        }
    }
}
