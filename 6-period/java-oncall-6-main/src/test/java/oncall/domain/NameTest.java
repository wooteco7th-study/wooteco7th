package oncall.domain;

import static oncall.support.CustomAssert.assertIllegalArgument;
import static org.assertj.core.api.Assertions.assertThatCode;

import oncall.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("이름을 생성한다.")
    void test() {
        // Given

        // When & Then
        assertThatCode(() -> {
            new Name("밍트");
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이름이 5글자를 초과하면 예외가 발생한다.")
    void 이름이_5_글자를_초과하면_예외가_발생() {
        // Given

        // When & Then
        assertIllegalArgument(() -> new Name("밍트abcd"), ErrorMessage.INVALID_INPUT);
    }
}
