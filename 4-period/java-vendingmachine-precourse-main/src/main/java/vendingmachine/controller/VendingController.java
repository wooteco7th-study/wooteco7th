package vendingmachine.controller;

import vendingmachine.exception.ExceptionHandler;
import vendingmachine.price.HoldingPrice;
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
        HoldingPrice holdingPrice = createHoldingPrice();


    }

    private HoldingPrice createHoldingPrice() {
        return exceptionHandler.retryOn(() -> {
            long holdingAmount = inputView.readHoldingAmount();
            return service.createHoldingPrice(holdingAmount);
        });
    }
}
