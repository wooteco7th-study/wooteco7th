package vendingmachine.controller;

import java.util.List;
import vendingmachine.dto.CoinDto;
import vendingmachine.exception.ExceptionHandler;
import vendingmachine.price.Price;
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

    public void process(){
        Price holdingPrice = createHoldingPrice();
        List<CoinDto> randomCoins = service.createRandomCoins(holdingPrice);
        outputView.informHoldingAmount(randomCoins);
    }

    private Price createHoldingPrice() {
        outputView.requestHoldingAmount();
        return exceptionHandler.retryOn(() -> {
            long holdingAmount = inputView.readHoldingAmount();
            return service.createHoldingPrice(holdingAmount);
        });
    }
}
