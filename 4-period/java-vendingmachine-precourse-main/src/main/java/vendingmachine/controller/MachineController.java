package vendingmachine.controller;

import vendingmachine.domain.change.Changes;
import vendingmachine.domain.coin.Coins;
import vendingmachine.domain.Drinker;
import vendingmachine.domain.product.Products;
import vendingmachine.service.creator.CoinCreator;
import vendingmachine.service.creator.DrinkerCreator;
import vendingmachine.service.Payment;
import vendingmachine.service.creator.ProductsCreator;
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
        Coins coins = makeCoins();
        outputView.print(coins.toString());

        Products products = makeProducts();
        Drinker drinker = makeDrinker();
        Payment payment = new Payment(products, drinker);

        while (true) {
            outputView.print(drinker.toString());
            if (products.isMoreThanMinPrice(drinker.getMoney())  || products.isProductsEmpty()) {
                break;
            }
            makePayment(payment);
        }
        outputView.printlnMessage(PrintMessage.RETURN_CHANGE);
        Changes changes = coins.change(drinker.getMoney());
        outputView.print(changes.toString());
    }

    private Coins makeCoins() {
        while (true) {
            try {
                String machineCoin = inputView.getMachineCoin();
                return new CoinCreator(machineCoin).create();
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    private Products makeProducts() {
        while (true) {
            try {
                String productInfo = inputView.getProductInfo();
                return new ProductsCreator(productInfo).create();
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    private Drinker makeDrinker() {
        while (true) {
            try {
                String inputMoney = inputView.getMoney();
                return new DrinkerCreator(inputMoney).create();
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    private void makePayment(Payment payment) {
        while (true) {
            try {
                String buyProduct = inputView.getProductName();
                payment.pay(buyProduct);
                return;
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }
}
