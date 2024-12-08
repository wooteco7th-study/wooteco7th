package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BridgeGeneratorTest {

    @DisplayName("다리 길이에 맞게 다리를 생성한다.")
    @ParameterizedTest
    @MethodSource
    void generateTest(final BridgeLength bridgeLength) throws Exception {
        //should
        assertThat(BridgeGenerator.generate(bridgeLength).getSize()).isEqualTo(bridgeLength.getValue());
    }

    private static Stream<BridgeLength> generateTest() {
        return Stream.of(
                new BridgeLength(3),
                new BridgeLength(10),
                new BridgeLength(15)
        );
    }

}
