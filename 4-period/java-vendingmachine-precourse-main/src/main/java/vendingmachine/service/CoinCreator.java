package vendingmachine.service;

import vendingmachine.Coin;
import vendingmachine.domain.Coins;
import vendingmachine.util.Convertor;

public class CoinCreator {

    private final int coin;

    public CoinCreator(String input) {
        int coin = Convertor.changeType(input);
        validate(coin);
        this.coin = coin;
    }

    private void validate(int coin) {
        if (coin < 100) {
            throw new IllegalArgumentException();
        }
    }

    public Coins create() {
        return Coin.create(coin);
    }
}
