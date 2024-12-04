package subway.controller;

import subway.command.FunctionCommand;
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
        // 메인화면
        outputView.welcome();
        while (true) {
            if (isQuit()) {
                break;
            }

        }

    }

    private boolean isQuit() {
        return exceptionHandler.retryOn(
                () -> FunctionCommand.from(inputView.informFunction()).isQuit());
    }
}
