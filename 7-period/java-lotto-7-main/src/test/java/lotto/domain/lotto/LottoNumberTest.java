package lotto.domain.lotto;

import static lotto.domain.support.CustomAssert.assertIllegalArgument;
import static lotto.exception.ErrorMessage.INVALID_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @Test
    @DisplayName("로또 번호를 생성한다.")
    void 생성_성공() {
        // Given

        // When & Then
        assertThatCode(() -> {
            new LottoNumber(30);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호의 범위 1 이상 45 이하가 아니면 예외가 발생한다.")
    void 생성_실패_로또번호범위(int value) {
        // Given

        // When & Then
        assertIllegalArgument(() -> new LottoNumber(value), INVALID_LOTTO_NUMBER);
    }
}
