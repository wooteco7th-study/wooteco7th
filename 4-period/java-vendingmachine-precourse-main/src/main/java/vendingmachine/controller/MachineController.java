package vendingmachine.controller;

import vendingmachine.Coin;
import vendingmachine.domain.Inventories;
import vendingmachine.domain.MachineCoins;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.Map;
import java.util.function.Supplier;

public class MachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public MachineController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        MachineCoins machineCoins = retryOnInvalidInput(this::getMachineCoins);
        Map<Coin, Integer> coinIntegerMap = machineCoins.saveCoins();
        outputView.printMachineCoins(machineCoins.toResponse());
        Inventories inventories = inputView.readInventories();
        int orderAmount = inputView.readOrderAmount();

        do {
            outputView.printOrderAmount(orderAmount);
            String orderProductName = inputView.readOrderProductName();
            orderAmount = inventories.update(orderAmount, orderProductName);
        } while (inventories.keepOrder(orderAmount));

        Map<Coin, Integer> coins = machineCoins.update(orderAmount);
        outputView.printOrderAmount(orderAmount);
        outputView.printChange(coins);
    }

    private MachineCoins getMachineCoins() {
        int amountHeld = inputView.readAmountHeld();
        return new MachineCoins(amountHeld);
    }

    private <T> T retryOnInvalidInput(Supplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
