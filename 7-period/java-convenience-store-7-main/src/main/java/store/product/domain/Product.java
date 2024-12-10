package store.product.domain;

import store.error.ErrorMessage;
import store.util.NumberValidator;

public class Product {

    private static final int MIN_QUANTITY = 0;

    private final String name;
    private final ProductType productType;
    private final int quantity;
    private final int price;
    private final String promotionName;

    public Product(final String name, final ProductType productType, final int quantity, final int price,
                   final String promotionName) {
        validate(quantity);
        this.name = name;
        this.productType = productType;
        this.quantity = quantity;
        this.price = price;
        this.promotionName = promotionName;
    }

    public String getName() {
        return name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getPromotionName() {
        return promotionName;
    }

    private void validate(final int quantity) {
        NumberValidator.validateRange(quantity, MIN_QUANTITY, Integer.MAX_VALUE, ErrorMessage.INVALID_INPUT);
    }
}
