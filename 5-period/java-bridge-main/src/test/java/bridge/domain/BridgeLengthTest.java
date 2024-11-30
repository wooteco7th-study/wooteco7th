package bridge.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bridge.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeLengthTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 21, -1, 0})
    @DisplayName("다리의 길이가 3 ~ 20을 벗어나 예외가 발생한다.")
    void validateRangeTest(final int length) throws Exception {
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new BridgeLength(length))
                .withMessageContaining(ErrorMessage.INVALID_BRIDGE_LENGTH_FORMAT.getMessage());
    }

}
