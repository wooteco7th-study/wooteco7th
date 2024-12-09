package pairmatching.exception;

import java.util.Optional;
import java.util.function.Supplier;
import pairmatching.view.OutputView;

public class ExceptionHandler {

    private final OutputView outputView;

    public ExceptionHandler(final OutputView outputView) {
        this.outputView = outputView;
    }

    public <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.showException(e);
            }
        }
    }

    public void retryUntilSuccess(Runnable callback) {
        while (true) {
            try {
                callback.run();
                return;
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.showException(e);
            }
        }
    }

    public <T> T retryUntilPresent(Supplier<Optional<T>> action) {
        while (true) {
            try {
                Optional<T> result = action.get();
                if (result.isPresent()) {
                    return result.get();
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.showException(e);
            }
        }
    }

    public <T> T executeWithCatch(Supplier<T> action) {
        try {
            return action.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.showException(e);
            return null;
        }
    }

    public void executeWithCatch(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.showException(e);
        }
    }
}
