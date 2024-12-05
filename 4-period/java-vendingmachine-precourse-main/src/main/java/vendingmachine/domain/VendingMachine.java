package vendingmachine.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class VendingMachine {

    private static final int DEFAULT_PURCHASE_QUANTITY = 1;

    private final CoinGroup coinGroup;
    private final ProductGroup productGroup;
    private final Money money;

    public VendingMachine(final CoinGroup coinGroup, final ProductGroup productGroup, final Money money) {
        this.coinGroup = coinGroup;
        this.productGroup = productGroup;
        this.money = money;
    }

    public boolean hasPurchaseAbleProduct() {
        return productGroup.hasPurchaseAbleProduct(money.getValue(), DEFAULT_PURCHASE_QUANTITY);
    }

    public void purchaseProduct(final String name) {
        final Product product = productGroup.findByProductNameAndQuantity(name,
                DEFAULT_PURCHASE_QUANTITY);
        product.subtractQuantity(DEFAULT_PURCHASE_QUANTITY);
        money.subtractValue(product.getPrice());
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
        coinGroup.subtractQuantity(expensiveCoin);
        mergeCoin(coins);
    }

    private boolean hasNotChangeAbleCoin(final Coin coin) {
        return Objects.equals(coin, Coin.NONE) || !money.isExceedsValue(Coin.COIN_10.getAmount());
    }
}
