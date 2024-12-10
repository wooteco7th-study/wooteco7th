package store.product.controller;

import java.util.List;
import store.error.ErrorMessage;
import store.product.dto.ProductRequest;
import store.product.dto.ProductResponse;
import store.product.service.ProductService;
import store.product.view.InputView;
import store.product.view.OutputView;
import store.util.LoopTemplate;
import store.util.StringParser;

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
        final List<ProductRequest> productRequests = requestProductRequests();
        productService.generatePurchaseProductGroup(productRequests);
    }

    private List<ProductRequest> requestProductRequests() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskProduct();
            return inputView.readProducts().stream()
                    .map(this::convertToProductRequest)
                    .toList();
        });
    }

    private ProductRequest convertToProductRequest(final String token) {
        final String[] splitToken = token.split("-");
        return new ProductRequest(splitToken[0], StringParser.parseToInt(splitToken[1], ErrorMessage.INVALID_INPUT));
    }
}
