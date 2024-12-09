package oncall.support;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import oncall.exception.ErrorMessage;
import oncall.exception.ErrorPrefix;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.ThrowableAssert;

public class CustomAssert {
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
