package bridge.domain.bridge;

import static bridge.exception.ErrorMessage.INVALID_BRIDGE_LENGTH;
import static bridge.support.CustomAssertions.assertIllegalArgument;

import bridge.domain.bridge.Bridge;
import bridge.domain.bridge.BridgeMaker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeTest {

    @Test
    @DisplayName("다리 생성 테스트")
    void test() {
        // Given
        BridgeMaker bridgeMaker = new BridgeMaker(() -> 1);

        // When & Then
        Assertions.assertThatCode(() -> {
            new Bridge(3, bridgeMaker);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 21})
    @DisplayName("다리 길이는 3부터 20 사이의 숫자가 아니면 예외가 발생한다.")
    void 생성_실패(int size) {
        // Given
        BridgeMaker bridgeMaker = new BridgeMaker(() -> 1);

        // When & Then
        assertIllegalArgument(() -> new Bridge(size, bridgeMaker), INVALID_BRIDGE_LENGTH);
    }

}
