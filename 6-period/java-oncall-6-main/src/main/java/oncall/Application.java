package oncall;

import oncall.controller.OnCallController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final OnCallController onCallController = new OnCallController(inputView, outputView);
        onCallController.run();
    }
}
