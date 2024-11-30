package bridge.util;

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
}
