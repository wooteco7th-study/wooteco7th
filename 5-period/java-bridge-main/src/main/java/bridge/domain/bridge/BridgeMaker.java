package bridge.domain.bridge;

import bridge.domain.command.UpDown;
import bridge.domain.generator.BridgeNumberGenerator;
import bridge.exception.CustomIllegalArgumentException;
import bridge.exception.ErrorMessage;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validate(size);
        List<Integer> numbers = makeBridgeNumbers(size);
        return numbers.stream()
                .map(UpDown::from)
                .map(UpDown::getDirection)
                .toList();
    }

    private void validate(final int size) {
        if (size < 3 || size > 20) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_BRIDGE_LENGTH);
        }
    }

    private List<Integer> makeBridgeNumbers(final int size) {
        return IntStream.range(0, size)
                .mapToObj(n -> bridgeNumberGenerator.generate())
                .toList();
    }
}