package store.product.domain;

public class Product {

    private final String name;
    private final ProductType productType;
    private final String promotionName;
    private final int quantity;
    private final int price;

    public Product(final String name, final ProductType productType, final int quantity, final int price,
                   final String promotionName) {
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

}
