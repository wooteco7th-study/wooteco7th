package pairmatching.controller;

import pairmatching.domain.FunctionCommand;
import pairmatching.exception.ExceptionHandler;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;

    public PairController(final InputView inputView, final OutputView outputView,
                          final ExceptionHandler exceptionHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
    }

    public void process() {
        FunctionCommand functionCommand = makeFunctionCommand();

    }

    private FunctionCommand makeFunctionCommand() {
        outputView.showTitleFunction();
        return exceptionHandler.retryOn(() -> FunctionCommand.from(inputView.readFunction()));
    }
}
