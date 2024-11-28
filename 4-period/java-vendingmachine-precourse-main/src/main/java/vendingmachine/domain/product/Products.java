package vendingmachine.domain.product;

import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_NAME;
import static vendingmachine.exception.ErrorMessage.INVALID_STATE_ORDER;
import static vendingmachine.exception.ErrorMessage.NO_PRODUCT_EXIST;

import java.util.ArrayList;
import java.util.List;
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

    public int buy(final Product orderProduct) {
        for (Product product : products) {
            if (product.equals(orderProduct)) {
                product.subtractQuantity();
                return product.getPriceAmount();
            }
        }
        throw new CustomIllegalStateException(INVALID_STATE_ORDER);
    }

    public int getLowestProductPrice() {
        return products.stream()
                .mapToInt(Product::getPriceAmount)
                .min()
                .orElseThrow(() -> new CustomIllegalArgumentException(NO_PRODUCT_EXIST));
    }
}
