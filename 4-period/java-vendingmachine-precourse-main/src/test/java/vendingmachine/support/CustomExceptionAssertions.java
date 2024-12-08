package vendingmachine.support;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.ThrowableAssert;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.ErrorPrefix;

public class CustomExceptionAssertions {

    // IllegalArgumentException
    public static AbstractThrowableAssert<?, ? extends Throwable> assertIllegalArgument(
            ThrowableAssert.ThrowingCallable throwingCallable) {
        return assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ErrorPrefix.format(""));
    }

    public static AbstractThrowableAssert<?, ? extends Throwable> assertIllegalArgument(
            ThrowableAssert.ThrowingCallable throwingCallable,
            ErrorMessage expectedMessage) {
        return assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ErrorPrefix.format(""))
                .hasMessageContaining(expectedMessage.getMessage());
    }

    // IllegalStateException
    public static AbstractThrowableAssert<?, ? extends Throwable> assertIllegalState(
            ThrowableAssert.ThrowingCallable throwingCallable) {
        return assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith(ErrorPrefix.format(""));
    }
}
