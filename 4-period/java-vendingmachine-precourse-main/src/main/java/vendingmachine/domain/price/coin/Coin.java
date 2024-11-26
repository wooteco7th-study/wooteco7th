package vendingmachine.domain.price.coin;

import static vendingmachine.exception.ErrorMessage.COIN_NOT_EXIST;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import vendingmachine.domain.price.Price;
import vendingmachine.exception.CustomIllegalArgumentException;

public enum Coin {

    COIN_500(new Price(500)),
    COIN_100(new Price(100)),
    COIN_50(new Price(50)),
    COIN_10(new Price(10));

    private final Price price;

    Coin(final Price price) {
        this.price = price;
    }

    public static Coin createCoin(Price price) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.getPrice().equals(price))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(COIN_NOT_EXIST));
    }

    public static List<Coin> calculateAvailableCoinTypes(Price price) {
        return Arrays.stream(Coin.values())
                .filter(coin -> price.isMoreThanEqual(coin.getPrice()))
                .toList();
    }

    public static Coin calculateMaxCoin(Price price) {
        return Arrays.stream(Coin.values())
                .filter(coin -> price.isMoreThanEqual(coin.getPrice()))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException("해당 동전이 없습니다."));
    }

    public static List<Coin> sortedCoins() {
        return Arrays.stream(Coin.values())
                .sorted((coin1, coin2) -> Long.compare(coin2.getPrice().getAmount(), coin1.getPrice().getAmount()))
                .toList();
    }

    public static Coin getLowest() {
        return Arrays.stream(Coin.values())
                .min(Comparator.comparingLong(c -> c.getPrice().getAmount()))
                .orElseThrow(() -> new CustomIllegalArgumentException(COIN_NOT_EXIST));
    }

    public Price getPrice() {
        return price;
    }
}
