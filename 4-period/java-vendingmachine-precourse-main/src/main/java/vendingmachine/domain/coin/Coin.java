package vendingmachine.domain.coin;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    private static int getRandomCoin() {
        List<Integer> coins = Arrays.stream(Coin.values())
                .map(Coin::getAmount)
                .collect(Collectors.toList());
        return Randoms.pickNumberInList(coins);
    }

    public static Coins create(int coin) {
        Coins coins = new Coins();
        while (coin > 0) {
            int getCoin = getRandomCoin();
            if (coin >= getCoin) {
                coins.setCoin(getCoin);
                coin -= getCoin;
            }
        }
        return coins;
    }
}
