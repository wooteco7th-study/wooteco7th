package vendingmachine.controller;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinGroup;
import vendingmachine.domain.CoinsConvertor;
import vendingmachine.domain.Money;
import vendingmachine.domain.Product;
import vendingmachine.domain.ProductGroup;
import vendingmachine.domain.ProductsGenerator;
import vendingmachine.domain.VendingMachine;
import vendingmachine.util.LoopTemplate;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Money vendingMachineMoney = requestVendingMachineMoney();
        final Map<Coin, Integer> coins = CoinsConvertor.convert(vendingMachineMoney);
        outputView.printVendingMachineCoins(coins);
        final VendingMachine vendingMachine = generateVendingMachine(coins);
        purchase(vendingMachine);
    }

    private void purchase(final VendingMachine vendingMachine) {
        while (vendingMachine.hasPurchaseAbleProduct()) {
            purchaseProduct(vendingMachine);
        }
        responseRemainingCoins(vendingMachine);
    }

    private void responseRemainingCoins(final VendingMachine vendingMachine) {
        outputView.printInputMoney(vendingMachine.getMoneyValue());
        outputView.printRemainingCoins(vendingMachine.getRemainingCoins());
    }

    private void purchaseProduct(final VendingMachine vendingMachine) {
        LoopTemplate.tryCatchLoop(() -> {
            outputView.printInputMoney(vendingMachine.getMoneyValue());
            outputView.printAskPurchaseProduct();
            final String name = inputView.purchaseProduct();
            vendingMachine.purchaseProduct(name);
        });
    }


    private VendingMachine generateVendingMachine(final Map<Coin, Integer> coins) {
        final CoinGroup coinGroup = new CoinGroup(coins);
        final ProductGroup productGroup = requestProducts();
        final Money inputMoney = requestInputMoney();
        return new VendingMachine(coinGroup, productGroup, inputMoney);
    }

    private Money requestInputMoney() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskInputMoney();
            final int money = inputView.readMoney();
            return new Money(money);
        });
    }

    private ProductGroup requestProducts() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskProducts();
            final List<String> inputs = inputView.readProducts();
            final List<Product> products = ProductsGenerator.generate(inputs);
            return new ProductGroup(products);
        });
    }


    private Money requestVendingMachineMoney() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskVendingMachineMoney();
            final int money = inputView.readMoney();
            return new Money(money);
        });
    }
}
