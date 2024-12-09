package oncall;

import oncall.controller.OnCallController;
import oncall.view.console.ConsoleInputView;
import oncall.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        final ConsoleInputView consoleInputView = new ConsoleInputView();
        final ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        final OnCallController onCallController = new OnCallController(consoleInputView, consoleOutputView);
        onCallController.run();
    }
}
