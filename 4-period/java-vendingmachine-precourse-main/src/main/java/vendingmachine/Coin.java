package vendingmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static vendingmachine.exception.ExceptionMessage.COIN_NOT_FOUND;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static List<Integer> coins() {
        List<Integer> coins = new ArrayList<>();
        for (Coin coin : values()) {
            coins.add(coin.amount);
        }
        return coins;
    }

    public static Coin from(int input) {
        return Arrays.stream(Coin.values())
                .filter(element -> element.amount == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COIN_NOT_FOUND.getMessage()));
    }
}
