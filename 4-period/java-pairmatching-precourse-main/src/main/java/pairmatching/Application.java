package pairmatching;

import pairmatching.controller.PairController;
import pairmatching.view.console.ConsoleInputView;
import pairmatching.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {

        final ConsoleInputView consoleInputView = new ConsoleInputView();
        final ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        final PairController pairController = new PairController(consoleInputView, consoleOutputView);
        pairController.run();
    }
}
