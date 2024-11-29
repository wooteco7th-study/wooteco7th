package vendingmachine.util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static vendingmachine.exception.ErrorMessage.INVALID_UNIT;
import static vendingmachine.support.CustomExceptionAssertions.assertIllegalArgument;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.exception.ErrorMessage;

class NumberValidatorTest {

    @Nested
    @DisplayName("범위 테스트")
    class validateRangeTest {

        private final int min = 0;
        private final int max = 10;

        @ParameterizedTest
        @ValueSource(ints = {min, max, 3})
        @DisplayName("범위 안에 있는지 검증한다.")
        void test(int value) {
            // Given

            // When & Then
            assertThatCode(() -> {
                NumberValidator.validateRange(value, min, max, ErrorMessage.INVALID_RANGE);
            }).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("최소값보다 작으면 예외를 발생한다.")
        void testMin() {
            // Given

            // When & Then
            assertIllegalArgument(() -> NumberValidator.validateRange(-1, min, max, ErrorMessage.INVALID_RANGE));
        }

        @Test
        @DisplayName("최대값보다 크면 예외를 발생한다.")
        void testMax() {
            // Given

            // When & Then
            assertIllegalArgument(() -> NumberValidator.validateRange(11, min, max, ErrorMessage.INVALID_RANGE));
        }
    }

    @Nested
    @DisplayName("단위 테스트")
    class validateUnitTest {

        private static final int unit = 10;

        @Test
        @DisplayName("숫자가 단위로 나누어 떨어지면 예외를 던지지 않는다.")
        void testUnit() {
            // Given

            // When & Then
            assertThatCode(() -> {
                NumberValidator.validateUnit(10, unit, INVALID_UNIT);
            }).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("숫자가 단위로 나누어 떨어지지 않으면 예외를 던진다.")
        void testNotUnit() {
            // Given

            // When & Then
            assertIllegalArgument(() -> NumberValidator.validateUnit(12, unit, INVALID_UNIT),
                    INVALID_UNIT);
        }
    }
}
