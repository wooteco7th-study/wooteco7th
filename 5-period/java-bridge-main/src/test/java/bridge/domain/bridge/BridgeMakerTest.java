package bridge.domain.bridge;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.domain.bridge.BridgeMaker;
import java.util.List;
import org.junit.jupiter.api.Test;

class BridgeMakerTest {

    @Test
    void makeBridge() {
        // Given
        BridgeMaker bridgeMaker = new BridgeMaker(() -> 1);

        // When
        List<String> directions = bridgeMaker.makeBridge(3);

        // Then
        assertThat(directions).containsExactly("U", "U", "U");
    }
}
