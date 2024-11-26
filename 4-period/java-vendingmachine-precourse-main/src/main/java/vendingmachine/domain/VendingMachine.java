package vendingmachine.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class VendingMachine {

    private static final int DEFAULT_PURCHASE_QUANTITY = 1;

    private final ProductGroup productGroup;
    private final CoinGroup coinGroup;
    private final Money money;

    public VendingMachine(final ProductGroup productGroup, final CoinGroup coinGroup, final Money money) {
        this.productGroup = productGroup;
        this.coinGroup = coinGroup;
        this.money = money;
    }

    public boolean hasPurchaseAbleProduct() {
        return productGroup.hasPurchaseAbleProduct(money.getValue(), DEFAULT_PURCHASE_QUANTITY);
    }

    public Map<Coin, Integer> getRemainingCoins() {
        final Map<Coin, Integer> coins = new LinkedHashMap<>();
        mergeCoin(coins);
        return coins;
    }

    public int getMoneyValue() {
        return money.getValue();
    }

    private void mergeCoin(final Map<Coin, Integer> coins) {
        final Coin expensiveCoin = coinGroup.getExpensiveCoin(money.getValue());
        if (hasNotChangeAbleCoin(expensiveCoin)) {
            return;
        }
        money.subtractValue(expensiveCoin.getAmount());
        coins.merge(expensiveCoin, 1, Integer::sum);
        mergeCoin(coins);
    }

    private boolean hasNotChangeAbleCoin(final Coin coin) {
        return Objects.equals(coin, Coin.NONE) || !money.isExceedsValue(Coin.COIN_10.getAmount());
    }
}
