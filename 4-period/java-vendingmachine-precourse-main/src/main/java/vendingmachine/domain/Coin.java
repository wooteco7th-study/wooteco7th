package vendingmachine.domain;

import java.util.Arrays;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10),
    NONE(0);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin findByAmount(final int amount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.amount == amount)
                .findAny()
                .orElse(Coin.NONE);
    }
}
