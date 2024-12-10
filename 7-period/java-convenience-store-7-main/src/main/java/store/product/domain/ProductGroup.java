package store.product.domain;

import java.util.List;

public class ProductGroup {

    private final List<Product> products;

    public ProductGroup(final List<Product> products) {
        this.products = products;
    }

}
