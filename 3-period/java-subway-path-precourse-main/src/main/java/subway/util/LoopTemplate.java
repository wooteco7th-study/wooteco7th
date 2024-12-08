package subway.util;

import java.util.Optional;
import java.util.function.Supplier;

public class LoopTemplate {

    private LoopTemplate() {
    }

    public static <T> T tryCatchLoop(Supplier<T> callback) {
        while (true) {
            try {
                return callback.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void tryCatchLoop(Runnable callback) {
        while (true) {
            try {
                callback.run();
                return;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static <T> Optional<T> tryCatchLoopOptional(Supplier<T> callback) {
        try {
            return Optional.of(callback.get());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
