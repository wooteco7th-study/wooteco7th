package vendingmachine.domain.product;

import static vendingmachine.exception.ErrorMessage.DUPLICATED_PRODUCT;
import static vendingmachine.exception.ErrorMessage.INVALID_ORDER_STATE;
import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_NAME;
import static vendingmachine.exception.ErrorMessage.NO_PRODUCT_EXIST;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.CustomIllegalStateException;

public class Products {

    private final List<Product> products;

    public Products(final List<Product> products) {
        validate(products);
        this.products = new ArrayList<>(products);
    }

    private void validate(final List<Product> products) {
        int distinctSize = (int) products.stream()
                .distinct()
                .count();
        if (distinctSize != products.size()) {
            throw new CustomIllegalArgumentException(DUPLICATED_PRODUCT);
        }
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
        throw new CustomIllegalStateException(INVALID_ORDER_STATE);
    }

    public int getLowestProductPrice() {
        return products.stream()
                .mapToInt(Product::getPriceAmount)
                .min()
                .orElseThrow(() -> new CustomIllegalArgumentException(NO_PRODUCT_EXIST));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Products products1)) {
            return false;
        }
        return Objects.equals(products, products1.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
