package vendingmachine.controller;

import vendingmachine.domain.Changes;
import vendingmachine.domain.Coins;
import vendingmachine.domain.Drinker;
import vendingmachine.domain.Products;
import vendingmachine.service.CoinCreator;
import vendingmachine.service.DrinkerCreator;
import vendingmachine.service.Payment;
import vendingmachine.service.ProductsCreator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;
import vendingmachine.view.PrintMessage;

public class MachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public MachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String machineCoin = inputView.getMachineCoin();
        Coins coins = new CoinCreator(machineCoin).create();
        outputView.print(coins.toString());

        String productInfo = inputView.getProductInfo();
        Products products = new ProductsCreator(productInfo).create();

        String inputMoney = inputView.getMoney();
        Drinker drinker = new DrinkerCreator(inputMoney).create();
        Payment payment = new Payment(products, drinker);

        while (true) {
            outputView.print(drinker.toString());
            if (products.isMoreThanMinPrice(drinker.getMoney())  || products.isProductsEmpty()) {
                break;
            }
            String buyProduct = inputView.getProductName();
            payment.pay(buyProduct);
            outputView.print(products.toString());
        }
        outputView.printlnMessage(PrintMessage.RETURN_CHANGE);
        Changes changes = coins.change(drinker.getMoney());
        outputView.print(changes.toString());
    }
}
