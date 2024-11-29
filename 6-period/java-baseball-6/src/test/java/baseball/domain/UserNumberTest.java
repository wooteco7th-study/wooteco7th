package baseball.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import baseball.error.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserNumberTest {

    @Test
    @DisplayName("숫자가 중복되어 예외가 발생한다.")
    void validateDuplicateTest() throws Exception {
        //given
        final List<Integer> numbers = List.of(1, 2, 2);

        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new UserNumber(numbers))
                .withMessageContaining(ErrorMessage.INVALID_DUPLICATED_NUMBER.getMessage());
    }

    @Test
    @DisplayName("숫자가 1~9 범위를 벗어나 예외가 발생한다.")
    void validateRangeTest() throws Exception {
        //given
        final List<Integer> numbers1 = List.of(0, 1, 2);
        final List<Integer> numbers2 = List.of(-1, 1, 2);

        //should
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new UserNumber(numbers1))
                        .withMessageContaining(ErrorMessage.INVALID_WRONG_NUMBER_FORMAT.getMessage()),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new UserNumber(numbers2))
                        .withMessageContaining(ErrorMessage.INVALID_WRONG_NUMBER_FORMAT.getMessage())
        );
    }

    @Test
    @DisplayName("숫자의 갯수가 3이 아니므로 예외가 발생한다.")
    void validateSizeTest() throws Exception {
        //given
        final List<Integer> numbers1 = List.of(1, 2);
        final List<Integer> numbers2 = List.of(1, 2, 3, 4);

        //should
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new UserNumber(numbers1))
                        .withMessageContaining(ErrorMessage.INVALID_EXCEEDS_NUMBERS_SIZE.getMessage()),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new UserNumber(numbers2))
                        .withMessageContaining(ErrorMessage.INVALID_EXCEEDS_NUMBERS_SIZE.getMessage())
        );
    }

}
