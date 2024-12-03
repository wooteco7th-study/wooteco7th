package lotto.domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @DisplayName("구입금액 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 10000})
    void 구입금액_생성_성공(int value) {

        // expect
        Assertions.assertThatCode(() -> new PurchaseAmount(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("구입금액 생성 실패")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 999, 1001})
    void 구입금액_생성_실패(int value) {

        // expect
        Assertions.assertThatThrownBy(() -> new PurchaseAmount(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
