package vendingmachine.domain;

import static vendingmachine.exception.ErrorMessage.INSUFFICIENT_PRICE;
import static vendingmachine.exception.ErrorMessage.OUT_OF_STOCK;

import java.util.Map;
import vendingmachine.domain.price.Price;
import vendingmachine.domain.price.coin.Coin;
import vendingmachine.domain.price.coin.CoinGenerator;
import vendingmachine.domain.price.coin.LeastCoinGenerator;
import vendingmachine.domain.product.Product;
import vendingmachine.domain.product.Products;
import vendingmachine.exception.CustomIllegalArgumentException;

public class VendingMachine {

    private final Products holdingProducts;
    private Price inputPrice;
    private final CoinGenerator leastCoinGenerator;

    public VendingMachine(final Map<Coin, Integer> coins, final Products holdingProducts, final Price inputPrice) {
        this.holdingProducts = holdingProducts;
        this.inputPrice = inputPrice;
        this.leastCoinGenerator = new LeastCoinGenerator(coins);
    }

    public void purchase(final Product orderProduct) {
        checkAvailablePrice(orderProduct);
        checkOutOfStock(orderProduct);
        int productPrice = holdingProducts.buy(orderProduct);
        inputPrice = new Price(inputPrice.getAmount() - productPrice);
    }

    public boolean canContinue() {
        return hasStock() && hasEnoughPrice();
    }

    private boolean hasStock() {
        return !holdingProducts.isAllOutOfStock();
    }

    private boolean hasEnoughPrice() {
        return inputPrice.getAmount() >= holdingProducts.getLowestProductPrice();
    }

    private void checkOutOfStock(final Product orderProduct) {
        if (orderProduct.getQuantity().isZero()) {
            throw new CustomIllegalArgumentException(OUT_OF_STOCK);
        }
    }

    private void checkAvailablePrice(final Product orderProduct) {
        if (inputPrice.getAmount() >= orderProduct.getPriceAmount()) {
            return;
        }
        throw new CustomIllegalArgumentException(INSUFFICIENT_PRICE);
    }

    public int getInputPriceValue() {
        return inputPrice.getAmount();
    }

    public Product findByName(final String name) {
        return holdingProducts.findByName(name);
    }

    public Map<Coin, Integer> getLeastCoins() {
        return leastCoinGenerator.generateCoins(inputPrice);
    }
}
