package store.product.controller;

import java.util.List;
import store.product.dto.ProductResponse;
import store.product.service.ProductService;
import store.product.view.InputView;
import store.product.view.OutputView;

public class ProductController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ProductService productService;

    public ProductController(final InputView inputView, final OutputView outputView,
                             final ProductService productService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.productService = productService;
    }

    public void run() {
        final List<ProductResponse> productResponses = productService.getProductResponses();
        outputView.printProductResponse(productResponses);
    }
}
