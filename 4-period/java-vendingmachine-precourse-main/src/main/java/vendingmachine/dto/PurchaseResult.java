package vendingmachine.dto;

import java.util.Map;
import vendingmachine.domain.money.Coin;
import vendingmachine.domain.product.Product;

public class PurchaseResult {
    public PurchaseResult(final Product updatedProduct, final Map<Coin, Integer> coins, final Object balance) {

    }
}
