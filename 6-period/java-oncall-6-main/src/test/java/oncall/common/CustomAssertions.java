package oncall.common;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import oncall.error.ErrorMessage;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.api.ThrowableAssertAlternative;

public class CustomAssertions {

    public static ThrowableAssertAlternative<IllegalArgumentException> customAssertThatIllegalArgumentException(
            ThrowingCallable throwingCallable) {
        return assertThatIllegalArgumentException().isThrownBy(throwingCallable)
                .withMessageStartingWith("[ERROR]");
    }

    public static ThrowableAssertAlternative<IllegalArgumentException> customAssertThatIllegalArgumentException(
            ThrowingCallable throwingCallable,
            ErrorMessage errorMessage) {
        return assertThatIllegalArgumentException().isThrownBy(throwingCallable)
                .withMessageStartingWith("[ERROR]")
                .withMessageContaining(errorMessage.getMessage());
    }

    public static ThrowableAssertAlternative<IllegalStateException> customAssertThatIllegalStateException(
            ThrowingCallable throwingCallable) {
        return assertThatIllegalStateException().isThrownBy(throwingCallable)
                .withMessageStartingWith("[ERROR]");
    }

    public static ThrowableAssertAlternative<IllegalStateException> customAssertThatIllegalStateException(
            ThrowingCallable throwingCallable, ErrorMessage errorMessage) {
        return assertThatIllegalStateException().isThrownBy(throwingCallable)
                .withMessageStartingWith("[ERROR]")
                .withMessageContaining(errorMessage.getMessage());

    }
}
