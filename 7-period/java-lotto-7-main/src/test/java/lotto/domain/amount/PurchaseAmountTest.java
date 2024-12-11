package lotto.domain.amount;

import static lotto.domain.support.CustomAssert.assertIllegalArgument;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @Test
    @DisplayName("생성 테스트")
    void 생성_테스트() {
        // Given

        // When & Then
        assertThatCode(() -> {
            new PurchaseAmount(3000);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-2000, 0, 800, 1200})
    @DisplayName("0 이하이면 예외가 발생한다.")
    void 실패_0_이하(int value) {
        // Given

        // When & Then
        assertIllegalArgument(() -> new PurchaseAmount(value), ErrorMessage.INVALID_INPUT);
    }

    @ParameterizedTest
    @ValueSource(ints = {800, 1200})
    @DisplayName("1000원 단위가 아니면 예외가 발생한다.")
    void 실패_1000_원_단위(int value) {
        // Given

        // When & Then
        assertIllegalArgument(() -> new PurchaseAmount(value), ErrorMessage.INVALID_INPUT);
    }

    @Test
    @DisplayName("로또 수량을 계산한다.")
    void calculateLottoQuantity() {
        // Given
        PurchaseAmount purchaseAmount = new PurchaseAmount(3000);

        // When & Then
        assertThat(purchaseAmount.calculateLottoQuantity()).isEqualTo(3);
    }
}
