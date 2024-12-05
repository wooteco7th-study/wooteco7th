package vendingmachine.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public static List<Coin> findAll() {
        return Arrays.stream(Coin.values())
                .sorted((c1, c2) -> c2.amount - c1.amount)
                .filter(coin -> !Objects.equals(coin, Coin.NONE))
                .collect(Collectors.toList());
    }

    public int getAmount() {
        return amount;
    }
}
