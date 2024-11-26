package vendingmachine.controller;

import java.util.List;
import vendingmachine.domain.price.Price;
import vendingmachine.domain.product.Product;
import vendingmachine.dto.CoinDto;
import vendingmachine.exception.ExceptionHandler;
import vendingmachine.service.VendingService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final VendingService service;

    public VendingController(final InputView inputView, final OutputView outputView,
                             final ExceptionHandler exceptionHandler,
                             final VendingService service) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.service = service;
    }

    public void process() {
        Price holdingPrice = createHoldingPrice();
        List<CoinDto> randomCoins = service.createRandomCoins(holdingPrice);
        outputView.informHoldingAmount(randomCoins);

        List<Product> holdingProducts = createHoldingProduct();
    }

    private List<Product> createHoldingProduct() {
        outputView.requestHoldingProduct();
        return exceptionHandler.retryOn(() -> {
            List<String> products = inputView.readHoldingProduct();
            return service.createHoldingProducts(products);
        });
    }

    private Price createHoldingPrice() {
        outputView.requestHoldingAmount();
        return exceptionHandler.retryOn(() -> {
            long holdingAmount = inputView.readHoldingAmount();
            return service.createHoldingPrice(holdingAmount);
        });
    }
}
