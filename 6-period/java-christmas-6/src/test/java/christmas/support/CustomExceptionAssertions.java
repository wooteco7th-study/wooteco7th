package christmas.support;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import christmas.exception.ErrorPrefix;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.ThrowableAssert;

public class CustomExceptionAssertions {

    // IllegalArgumentException
    public static AbstractThrowableAssert<?, ? extends Throwable> assertCustomIllegalArgumentException(
            ThrowableAssert.ThrowingCallable throwingCallable) {
        return assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ErrorPrefix.format(""));
    }

    public static AbstractThrowableAssert<?, ? extends Throwable> assertCustomIllegalArgumentException(
            ThrowableAssert.ThrowingCallable throwingCallable,
            ErrorMessage expectedMessage) {
        return assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ErrorPrefix.format(""))
                .hasMessageContaining(expectedMessage.getMessage());
    }

    // IllegalStateException
    public static AbstractThrowableAssert<?, ? extends Throwable> assertCustomIllegalStateException(
            ThrowableAssert.ThrowingCallable throwingCallable) {
        return assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith(ErrorPrefix.format(""));
    }
}
