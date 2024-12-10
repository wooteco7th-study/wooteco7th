package store;

import java.util.ArrayList;
import store.product.domain.ProductRepository;
import store.promotion.domain.PromotionRepository;

public class Application {
    public static void main(String[] args) {
        final PromotionRepository promotionRepository = new PromotionRepository(new ArrayList<>());
        final ProductRepository productRepository = new ProductRepository(new ArrayList<>());
        final Store store = new Store(productRepository, promotionRepository);
        store.purchase();
    }
}
