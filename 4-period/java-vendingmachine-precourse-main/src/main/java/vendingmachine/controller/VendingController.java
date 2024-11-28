package vendingmachine.controller;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.OrderProcessor;
import vendingmachine.domain.price.Price;
import vendingmachine.domain.price.coin.Coin;
import vendingmachine.domain.price.coin.LeastCoinGenerator;
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
        Map<Coin, Integer> coins = createVendingMachineCoins();
        Products holdingProducts = createHoldingProduct();
        Price inputPrice = createInputPrice();
        OrderProcessor orderProcessor = new OrderProcessor(holdingProducts, inputPrice);
        while (orderProcessor.canContinue()) {
            order(holdingProducts, orderProcessor);
        }
        makeRemainingCoins(coins, orderProcessor);
    }

    private void makeRemainingCoins(final Map<Coin, Integer> coins, final OrderProcessor orderProcessor) {
        outputView.showInputPrice(orderProcessor.getInputPrice().getAmount());
        Price remainingPrice = orderProcessor.getInputPrice();
        LeastCoinGenerator leastCoinGenerator = new LeastCoinGenerator(coins);
        Map<Coin, Integer> leastCoins = leastCoinGenerator.generateCoins(remainingPrice);
        outputView.showRemainingPrice(leastCoins);
    }

    private void order(final Products holdingProducts, final OrderProcessor orderProcessor) {
        outputView.showInputPrice(orderProcessor.getInputPrice().getAmount());
        Product orderProduct = createOrderProduct(holdingProducts);
        orderProcessor.process(orderProduct);
    }

    private Map<Coin, Integer> createVendingMachineCoins() {
        Price holdingPrice = createHoldingPrice();
        Map<Coin, Integer> coins = service.createRandomCoins(holdingPrice);
        outputView.informHoldingAmount(CoinDto.from(coins));
        return coins;
    }

    private Product createOrderProduct(final Products holdingProducts) {
        outputView.requestOrderProduct();
        return exceptionHandler.retryOn(() -> holdingProducts.findByName(inputView.readOrderProduct()));
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
