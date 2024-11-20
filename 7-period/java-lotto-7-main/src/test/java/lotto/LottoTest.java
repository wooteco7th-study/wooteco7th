package lotto;

import lotto.domain.Lotto;
import lotto.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호가 1 ~ 45 범위를 초과하여 예외가 발생 한다.")
    void validateRangeTest() throws Exception {
        //given
        final List<Integer> numbers1 = List.of(0, 1, 2, 3, 4, 5);
        final List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 61);

        //should
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(numbers1)).withMessageContaining(
                        ErrorMessage.INVALID_EXCEEDS_LOTTO_NUMBER_RANGE.getMessage()),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(numbers2)).withMessageContaining(
                        ErrorMessage.INVALID_EXCEEDS_LOTTO_NUMBER_RANGE.getMessage()
                )
        );
    }
}
