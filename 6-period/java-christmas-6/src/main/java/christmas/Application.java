package christmas;

import christmas.controller.ChristmasController;
import christmas.service.ChristmasService;
import christmas.view.console.ConsoleInputView;
import christmas.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        final ConsoleInputView consoleInputView = new ConsoleInputView();
        final ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        final ChristmasService christmasService = new ChristmasService();
        final ChristmasController christmasController = new ChristmasController(
                consoleInputView,
                consoleOutputView,
                christmasService
        );
        christmasController.run();
    }
}
