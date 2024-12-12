package vendingmachine;

import vendingmachine.controller.MachineController;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {

        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        MachineController machineController = new MachineController(inputView, outputView);
        machineController.run();
    }
}
