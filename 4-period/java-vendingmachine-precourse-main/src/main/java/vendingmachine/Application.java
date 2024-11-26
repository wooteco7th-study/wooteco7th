package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.view.console.ConsoleInputView;
import vendingmachine.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        final ConsoleInputView consoleInputView = new ConsoleInputView();
        final ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        final VendingMachineController vendingMachineController = new VendingMachineController(consoleInputView,
                consoleOutputView);
        vendingMachineController.run();
    }
}
