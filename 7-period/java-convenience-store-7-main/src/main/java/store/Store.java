package store;

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

public class Store {

    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;

    public Store(final ProductRepository productRepository, final PromotionRepository promotionRepository) {
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
    }

    public void purchase() {
        promotionRepository.initialize();
        productRepository.initialize();
        while (true) {
            final PurchaseProductGroup purchaseProductGroup = productController().requestPurchaseProductGroup();
            final PromotionController promotionController = promotionController(purchaseProductGroup);
            promotionController.run();
            final PaymentController paymentController = paymentController(purchaseProductGroup);
            paymentController.run();
            if (!paymentController.isRetry()) {
                break;
            }
        }
    }


    private ProductController productController() {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final ProductService productService = new ProductService(productRepository);
        return new ProductController(inputView, outputView, productService);
    }

    private PromotionController promotionController(final PurchaseProductGroup purchaseProductGroup) {
        final PromotionInputView promotionInputView = new PromotionInputView();
        final PromotionOutputView promotionOutputView = new PromotionOutputView();
        final PromotionService promotionService = new PromotionService(promotionRepository, productRepository);
        return new PromotionController(promotionInputView, promotionOutputView, promotionService, purchaseProductGroup);
    }

    private PaymentController paymentController(final PurchaseProductGroup purchaseProductGroup) {
        final PaymentInputView paymentInputView = new PaymentInputView();
        final PaymentOutputView paymentOutputView = new PaymentOutputView();
        return new PaymentController(paymentInputView, paymentOutputView, productRepository, promotionRepository, purchaseProductGroup);
    }
}
