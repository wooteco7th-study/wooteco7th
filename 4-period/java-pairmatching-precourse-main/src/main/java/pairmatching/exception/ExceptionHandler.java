package pairmatching.exception;

import java.util.function.Supplier;
import pairmatching.view.OutputView;

public class ExceptionHandler {

    private final OutputView outputView;

        public ExceptionHandler(final OutputView outputView) {
        this.outputView = outputView;
    }

    public <T> T retryOn(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.showException(e);
            }
        }
    }

    public void retryOn(Runnable callback) {
        while (true) {
            try {
                callback.run();
                return;
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.showException(e);
            }
        }
    }

    public <T> T handle(Supplier<T> action) {
        try {
            return action.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.showException(e);
            throw e;
        }
    }

    public void handle(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.showException(e);
            throw e;
        }
    }
}
