package subway.controller;

import subway.command.FunctionCommand;
import subway.command.RouteCriteriaCommand;
import subway.exception.ExceptionHandler;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;

    public SubwayController(final InputView inputView, final OutputView outputView,
                            final ExceptionHandler exceptionHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
    }

    public void process() {
        while (true) {
            if (isQuit()) {
                break;
            }
            RouteCriteriaCommand command = makeCriteria();
            if (command.isGoBack()) {
                outputView.showBlank();
                continue;
            }
        }

    }

    private RouteCriteriaCommand makeCriteria() {
        outputView.selectRouteCriteria();
        return exceptionHandler.retryOn(()-> RouteCriteriaCommand.from(inputView.readRouteCriteria()));
    }

    private boolean isQuit() {
        outputView.welcome();
        return exceptionHandler.retryOn(
                () -> FunctionCommand.from(inputView.informFunction()).isQuit());
    }
}
