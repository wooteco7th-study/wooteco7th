package menu.exception;

import java.util.Optional;
import java.util.function.Supplier;

public class ExceptionHandler {

    public static <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void retryUntilSuccess(Runnable callback) {
        while (true) {
            try {
                callback.run();
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static <T> T retryUntilPresent(Supplier<Optional<T>> action) {
        while (true) {
            try {
                Optional<T> result = action.get();
                if (result.isPresent()) {
                    return result.get();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
