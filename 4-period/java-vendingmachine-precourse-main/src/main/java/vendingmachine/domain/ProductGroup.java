package vendingmachine.domain;

import java.util.List;
import java.util.Objects;
import vendingmachine.error.AppException;
import vendingmachine.error.ErrorMessage;
import vendingmachine.util.ListValidator;

public class ProductGroup {

    private final List<Product> products;

    public ProductGroup(final List<Product> products) {
        validate(products);
        this.products = products;
    }

    public boolean hasPurchaseAbleProduct(final int price, final int quantity) {
        return products.stream()
                .anyMatch(product -> product.isOverProductPrice(price) && product.hasQuantity(quantity));
    }

    public Product findByProductNameAndQuantity(final String name, final int quantity) {
        return products.stream()
                .filter(product -> Objects.equals(product.getName(), name))
                .filter(product -> product.hasQuantity(quantity))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_CAN_NOT_PURCHASE_PRODUCT));
    }

    private void validate(final List<Product> products) {
        ListValidator.validateDuplicate(products, ErrorMessage.INVALID_DUPLICATED_PRODUCT);
    }
}
