package racingcar.util;

import java.util.function.Supplier;

public class LoopTemplate {

    public static <T> T tryCatch(final Supplier<T> call) {
        while (true) {
            try {
                return call.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private LoopTemplate() {

    }
}
