package subway.exception;

import subway.view.OutputView;

import java.util.function.Supplier;

public class ExceptionHandler {

    private ExceptionHandler() {
    }

    public static <T> T retryOnInvalidInput(Supplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    public static void retryOnInvalidInput(Runnable input) {
        while (true) {
            try {
                input.run();
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
