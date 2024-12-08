package vendingmachine.controller;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.VendingMachine;
import vendingmachine.domain.price.Price;
import vendingmachine.domain.price.coin.Coin;
import vendingmachine.domain.product.Product;
import vendingmachine.domain.product.Products;
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
        VendingMachine vendingMachine = createVendingMachine();
        while (vendingMachine.canContinue()) {
            purchase(vendingMachine);
        }
        makeRemainingCoins(vendingMachine);
    }

    private VendingMachine createVendingMachine() {
        Map<Coin, Integer> coins = createHoldingCoins();
        Products holdingProducts = createHoldingProduct();
        Price inputPrice = createInputPrice();
        return new VendingMachine(coins, holdingProducts, inputPrice);
    }

    private void makeRemainingCoins(final VendingMachine vendingMachine) {
        outputView.showInputPrice(vendingMachine.getInputPriceValue());
        outputView.showRemainingPrice(vendingMachine.getLeastCoins());
    }

    private void purchase(final VendingMachine vendingMachine) {
        outputView.showInputPrice(vendingMachine.getInputPriceValue());
        Product orderProduct = createOrderProduct(vendingMachine);
        vendingMachine.purchase(orderProduct);
    }

    private Map<Coin, Integer> createHoldingCoins() {
        Price holdingPrice = createHoldingPrice();
        Map<Coin, Integer> coins = service.createRandomCoins(holdingPrice);
        outputView.informHoldingAmount(CoinDto.from(coins));
        return coins;
    }

    private Product createOrderProduct(final VendingMachine vendingMachine) {
        outputView.requestOrderProduct();
        return exceptionHandler.retryOn(() -> vendingMachine.findByName(inputView.readOrderProduct()));
    }

    private Price createInputPrice() {
        outputView.requestInputAmount();
        return exceptionHandler.retryOn(() -> {
            int inputPrice = inputView.readInputPrice();
            return service.createInputPrice(inputPrice);
        });
    }

    private Products createHoldingProduct() {
        outputView.requestHoldingProduct();
        return exceptionHandler.retryOn(() -> {
            List<String> products = inputView.readHoldingProduct();
            return service.createHoldingProducts(products);
        });
    }

    private Price createHoldingPrice() {
        outputView.requestHoldingAmount();
        return exceptionHandler.retryOn(() -> {
            int holdingAmount = inputView.readHoldingAmount();
            return service.createHoldingPrice(holdingAmount);
        });
    }
}
