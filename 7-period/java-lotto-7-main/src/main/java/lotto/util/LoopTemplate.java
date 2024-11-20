package lotto.util;

import java.util.function.Supplier;

public class LoopTemplate {

    public static <T> T tryCatchLoop(final Supplier<T> callBack) {
        while (true) {
            try {
                return callBack.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private LoopTemplate() {

    }
}
