package vendingmachine.controller;

import vendingmachine.domain.Coins;
import vendingmachine.domain.Drinker;
import vendingmachine.domain.Products;
import vendingmachine.service.CoinCreator;
import vendingmachine.service.DrinkerCreator;
import vendingmachine.service.ProductsCreator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

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
        outputView.print(products.toString());

        String inputMoney = inputView.getMoney();
        Drinker drinker = new DrinkerCreator(inputMoney).create();
        outputView.print(drinker.toString());

        String buyProduct = inputView.getProductName();
        //관련 로직 구현
    }
}
