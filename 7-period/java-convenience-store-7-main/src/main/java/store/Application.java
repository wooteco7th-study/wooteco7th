package store;

import java.util.ArrayList;
import store.product.controller.ProductController;
import store.product.domain.ProductRepository;
import store.product.service.ProductService;
import store.product.view.InputView;
import store.product.view.OutputView;
import store.promotion.domain.PromotionRepository;

public class Application {
    public static void main(String[] args) {
        final PromotionRepository promotionRepository = new PromotionRepository(new ArrayList<>());
        promotionRepository.initialize();
        final ProductRepository productRepository = new ProductRepository(new ArrayList<>());
        productRepository.initialize();
        final ProductService productService = new ProductService(productRepository);
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final ProductController productController = new ProductController(inputView, outputView, productService);
        productController.run();
    }
}
