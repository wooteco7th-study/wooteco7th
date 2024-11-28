package vendingmachine.domain.price.coin;

import static vendingmachine.exception.ErrorMessage.COIN_NOT_EXIST;

import java.util.Arrays;
import java.util.List;
import vendingmachine.domain.price.Price;
import vendingmachine.exception.CustomIllegalArgumentException;

public enum Coin {

    COIN_500(new Price(500)),
    COIN_100(new Price(100)),
    COIN_50(new Price(50)),
    COIN_10(new Price(10)),
    COIN_0(new Price(0));

    private static final List<Coin> VALUED_COINS = Arrays.stream(Coin.values())
            .filter(coin -> coin != COIN_0)
            .sorted((c1, c2) -> Long.compare(c2.getPrice().getAmount(), c1.getPrice().getAmount()))
            .toList();

    private final Price price;

    Coin(final Price price) {
        this.price = price;
    }

    public static Coin createCoin(Price price) {
        return VALUED_COINS.stream()
                .filter(coin -> coin.getPrice().equals(price))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(COIN_NOT_EXIST));
    }

    public static List<Coin> calculateAvailableCoinTypes(Price price) {
        return VALUED_COINS.stream()
                .filter(coin -> price.isMoreThanEqual(coin.getPrice()))
                .toList();
    }

    public static List<Coin> sortedCoins() {
        return VALUED_COINS;
    }

    public static Coin getLowest() {
        return VALUED_COINS.getLast();
    }

    public Price getPrice() {
        return price;
    }
}
