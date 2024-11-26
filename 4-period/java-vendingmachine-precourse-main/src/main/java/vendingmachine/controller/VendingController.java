package vendingmachine.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        Price holdingPrice = createHoldingPrice();
        Map<Coin, Long> coins = service.createRandomCoins(holdingPrice);
        List<CoinDto> coinDtos = new ArrayList<>();
        for (Coin coin : Coin.values()) {
            if (coins.containsKey(coin)) {
                coinDtos.add(new CoinDto(coin.getPrice().getAmount(), coin.getPrice().getAmount()));
            }
            coinDtos.add(new CoinDto(coin.getPrice().getAmount(), 0L));
        }
        for (Entry<Coin, Long> entry : coins.entrySet()) {
            coinDtos.add(new CoinDto(entry.getKey().getPrice().getAmount(), entry.getValue()));
        }
        outputView.informHoldingAmount(coinDtos);

        Products holdingProducts = new Products(createHoldingProduct());
        Price inputPrice = createInputPrice();
        OrderProcessor orderProcessor = new OrderProcessor(holdingProducts, inputPrice);
        while (true) {
            outputView.showInputPrice(orderProcessor.getInputPrice().getAmount());
            Product orderProduct = createOrderProduct(holdingProducts);
            if (orderProcessor.process(orderProduct)) {
                outputView.showInputPrice(orderProcessor.getInputPrice().getAmount());
                break;
            }
        }
        Price remainingPrice = orderProcessor.getInputPrice();
        LeastCoinGenerator leastCoinGenerator = new LeastCoinGenerator();
        Map<Coin, Long> leastCoins = leastCoinGenerator.generateCoins2(remainingPrice, coins);
        outputView.showRemainingPrice(leastCoins);
    }

    private Product createOrderProduct(final Products holdingProducts) {
        outputView.requestOrderProduct();
        return exceptionHandler.retryOn(() -> holdingProducts.findByName(inputView.readOrderProduct()));
    }

    private Price createInputPrice() {
        outputView.requestInputAmount();
        return exceptionHandler.retryOn(() -> {
            long inputPrice = inputView.readInputPrice();
            return service.createInputPrice(inputPrice);
        });
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
