package bridge.domain.bridgemaker;

import bridge.exception.ExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BridgeMakerTest {

    private BridgeMaker bridgeMaker;

    @BeforeEach
    void setUp() {
        bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 21})
    void 다리_사이즈의_범위가_3미만_20초과시_예외가_발생한다(int size) {
        assertThatIllegalArgumentException().isThrownBy(() -> bridgeMaker.makeBridge(size))
                .withMessageContaining(
                        ExceptionMessage.SIZE_OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 20})
    void 다리가_입력한_사이즈만큼_생성된다(int size) {
        //when
        List<String> bridge = bridgeMaker.makeBridge(size);
        //then
        assertThat(bridge).hasSize(size);
    }

}
