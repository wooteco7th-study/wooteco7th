package store.product.dto;

import store.product.domain.Product;

public record ProductResponse(
        String name,
        int price,
        int quantity,
        String promotionName
) {
    public static ProductResponse of(final Product product) {
        return new ProductResponse(
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getPromotionName()
        );
    }
}
