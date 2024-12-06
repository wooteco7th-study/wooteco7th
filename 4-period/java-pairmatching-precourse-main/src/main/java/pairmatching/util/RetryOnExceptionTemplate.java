package pairmatching.util;

import java.util.function.Supplier;

public class RetryOnExceptionTemplate {
    private RetryOnExceptionTemplate() {
    }

    public static <T> T retryOnException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
