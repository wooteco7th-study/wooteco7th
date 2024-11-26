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

    public boolean process(final Product orderProduct) {
        // 구매할 수 있는지 확인
        if (checkAllOutOfStock() || checkInputPriceLowerThanLeastProductPrice()) {
            return true;
        }
        checkAvailablePrice(orderProduct);
        checkOutOfStock(orderProduct);
        // 구매
        Price productPrice = holdingProducts.buy(orderProduct);
        inputPrice = inputPrice.subtract(productPrice);
        // 종료 조건 (true이면 종료)
        return checkAllOutOfStock() || checkInputPriceLowerThanLeastProductPrice();
    }

    public Price getInputPrice() {
        return inputPrice;
    }

    private boolean checkInputPriceLowerThanLeastProductPrice() {
        System.out.println(inputPrice);
        System.out.println(holdingProducts.getLowestProcutPrice());
        return !inputPrice.isMoreThanEqual(holdingProducts.getLowestProcutPrice());
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
        if (inputPrice.isMoreThanEqual(orderProduct.getPrice())) {
            return;
        }
        throw new CustomIllegalArgumentException(INVALID_ORDER_PRICE);
    }
}
