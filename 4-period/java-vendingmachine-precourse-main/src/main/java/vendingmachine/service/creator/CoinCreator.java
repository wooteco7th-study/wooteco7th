package vendingmachine.service.creator;

import vendingmachine.domain.coin.Coin;
import vendingmachine.domain.coin.Coins;
import vendingmachine.excpetion.InputException;
import vendingmachine.util.Convertor;

public class CoinCreator {

    private final int coin;

    public CoinCreator(String input) {
        int coin = Convertor.changeType(input);
        validate(coin);
        this.coin = coin;
    }

    private void validate(int coin) {
        if (coin < 10) {
            throw new InputException();
        }
    }

    public Coins create() {
        return Coin.create(coin);
    }
}
