package vendingmachine.controller;

import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinsConvertor;
import vendingmachine.domain.Money;
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
        final Money money = requestVendingMachineMoney();
        final Map<Coin, Integer> coins = CoinsConvertor.convert(money);
        outputView.printVendingMachineCoins(coins);
    }

    private Money requestVendingMachineMoney() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskVendingMachineMoney();
            final int money = inputView.readMoney();
            return new Money(money);
        });
    }

}
