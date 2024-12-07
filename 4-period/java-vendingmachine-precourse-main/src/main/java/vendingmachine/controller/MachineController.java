package vendingmachine.controller;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Inventories;
import vendingmachine.domain.InventoriesGenerator;
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
        MachineCoins machineCoins = saveMachineCoins();

        Inventories inventories = retryOnInvalidInput(this::createInventories);
        int orderAmount = retryOnInvalidInput(inputView::readOrderAmount);
        orderAmount = processOrders(orderAmount, inventories);

        Map<Coin, Integer> change = machineCoins.getChange(orderAmount);
        outputView.printOrderAmount(orderAmount);
        outputView.printChange(change);
    }

    private MachineCoins saveMachineCoins() {
        MachineCoins machineCoins = retryOnInvalidInput(this::createMachineCoins);
        machineCoins.saveCoins();
        outputView.printMachineCoins(machineCoins.toResponse());
        return machineCoins;
    }

    private MachineCoins createMachineCoins() {
        int amountHeld = inputView.readAmountHeld();
        return new MachineCoins(amountHeld);
    }

    private Inventories createInventories() {
        String inventories = inputView.readInventories();
        return InventoriesGenerator.parseInventories(inventories);
    }

    private int processOrders(int orderAmount, final Inventories inventories) {
        while (inventories.keepOrder(orderAmount)) {
            outputView.printOrderAmount(orderAmount);
            final int currentOrderAmount = orderAmount;
            orderAmount = retryOnInvalidInput(() -> getOrderAmount(currentOrderAmount, inventories));
        }
        return orderAmount;
    }

    private int getOrderAmount(int orderAmount, final Inventories inventories) {
        String orderProductName = inputView.readOrderProductName();
        return inventories.update(orderAmount, orderProductName);
    }

    private <T> T retryOnInvalidInput(Supplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                // Q: retryHandler 클래스 빼면 output의존성 주입해야하는데 귀찮음(일을 키우는 느낌)
                outputView.printError(e.getMessage());
            }
        }
    }
}
