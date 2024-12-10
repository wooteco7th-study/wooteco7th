package store;

import java.util.ArrayList;
import store.payment.controller.PaymentController;
import store.payment.view.PaymentInputView;
import store.payment.view.PaymentOutputView;
import store.product.controller.ProductController;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProductGroup;
import store.product.service.ProductService;
import store.product.view.InputView;
import store.product.view.OutputView;
import store.promotion.controller.PromotionController;
import store.promotion.domain.PromotionRepository;
import store.promotion.service.PromotionService;
import store.promotion.view.PromotionInputView;
import store.promotion.view.PromotionOutputView;

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
        final PurchaseProductGroup purchaseProductGroup = productController.requestPurchaseProductGroup();

        final PromotionInputView promotionInputView = new PromotionInputView();
        final PromotionOutputView promotionOutputView = new PromotionOutputView();
        final PromotionService promotionService = new PromotionService(promotionRepository, productRepository);
        final PromotionController promotionController = new PromotionController(promotionInputView, promotionOutputView,
                promotionService, purchaseProductGroup);
        promotionController.run();

        final PaymentInputView paymentInputView = new PaymentInputView();
        final PaymentOutputView paymentOutputView = new PaymentOutputView();
        final PaymentController paymentController = new PaymentController(paymentInputView, paymentOutputView,
                productRepository, promotionRepository, purchaseProductGroup);

        paymentController.run();
    }
}
