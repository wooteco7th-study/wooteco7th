package bridge.domain.bridge;

import static bridge.exception.ErrorMessage.INVALID_BRIDGE_LENGTH;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeSizeTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 20})
    @DisplayName("3이상 20 이하이면 정상적으로 생성된다.")
    void 생성_테스트_성공(int value) {
        // Given

        // When & Then
        assertThatCode(() -> {
            new BridgeSize(value);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 21})
    @DisplayName("3이상 20이하가 아니면 예외가 발생한다.")
    void 생성_테스트_실패(int value) {
        // Given

        // When & Then
        assertThatThrownBy(() -> new BridgeSize(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_BRIDGE_LENGTH.getMessage());
    }

}
