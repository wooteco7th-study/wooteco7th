package lotto.domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 번호 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 44, 45})
    void 로또번호_생성_성공(int value) {

        // expect
        Assertions.assertThatCode(() -> new LottoNumber(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호 생성 실패")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 로또번호_생성_실패(int value) {

        // expect
        Assertions.assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
