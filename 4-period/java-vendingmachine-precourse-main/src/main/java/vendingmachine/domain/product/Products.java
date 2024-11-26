package vendingmachine.domain.product;

import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_NAME;
import static vendingmachine.exception.ErrorMessage.INVALID_STATE_ORDER;
import static vendingmachine.exception.ErrorMessage.NO_PRODUCT_EXIST;

import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.price.Price;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.CustomIllegalStateException;

public class Products {

    private final List<Product> products;

    public Products(final List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public Product findByName(final String productName) {
        return products.stream()
                .filter(product -> product.hasName(productName))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(INVALID_PRODUCT_NAME));
    }

    public boolean isAllOutOfStock() {
        return products.stream()
                .map(Product::getQuantity)
                .noneMatch(Quantity::hasStock);
    }

    public Price buy(final Product orderProduct) {
        for (Product product : products) {
            if (product.equals(orderProduct)) {
                product.subtractQuantity();
                return product.getPrice();
            }
        }
        throw new CustomIllegalStateException(INVALID_STATE_ORDER);
    }

    public Price getLowestProcutPrice() {
        return products.stream()
                .map(Product::getPrice)
                .min((p1, p2) -> Long.compare(p1.getAmount(), p2.getAmount()))
                .orElseThrow(() -> new CustomIllegalArgumentException(NO_PRODUCT_EXIST));
    }

}
