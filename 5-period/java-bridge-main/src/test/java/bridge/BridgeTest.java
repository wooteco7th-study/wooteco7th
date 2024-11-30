package bridge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}
