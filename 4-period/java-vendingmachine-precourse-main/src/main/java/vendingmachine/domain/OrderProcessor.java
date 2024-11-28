package vendingmachine.domain;

import static vendingmachine.exception.ErrorMessage.INVALID_ORDER_PRICE;
import static vendingmachine.exception.ErrorMessage.OUT_OF_STOCK;

import vendingmachine.domain.price.Price;
import vendingmachine.domain.product.Product;
import vendingmachine.domain.product.Products;
import vendingmachine.exception.CustomIllegalArgumentException;

public class OrderProcessor {

    private final Products holdingProducts;
    private Price inputPrice;

    public OrderProcessor(final Products holdingProducts, final Price inputPrice) {
        this.holdingProducts = holdingProducts;
        this.inputPrice = inputPrice;
    }

    public void process(final Product orderProduct) {
        checkAvailablePrice(orderProduct);
        checkOutOfStock(orderProduct);
        int productPrice = holdingProducts.buy(orderProduct);
        inputPrice = new Price(inputPrice.getAmount() - productPrice);
    }

    public boolean canContinue() {
        return !checkAllOutOfStock() || !checkInputPriceLowerThanLeastProductPrice();
    }

    public Price getInputPrice() {
        return inputPrice;
    }

    private boolean checkInputPriceLowerThanLeastProductPrice() {
        return inputPrice.getAmount() < holdingProducts.getLowestProductPrice();
    }

    private boolean checkAllOutOfStock() {
        return holdingProducts.isAllOutOfStock();
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
        throw new CustomIllegalArgumentException(INVALID_ORDER_PRICE);
    }
}
