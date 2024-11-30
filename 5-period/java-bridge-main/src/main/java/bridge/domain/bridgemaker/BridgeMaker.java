package bridge.domain.bridgemaker;

import bridge.domain.BridgeCommand;

import java.util.ArrayList;
import java.util.List;

import static bridge.exception.ExceptionMessage.SIZE_OUT_OF_RANGE;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 20;

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validateSize(size);
        return createBridgeAnswer(size);
    }

    private void validateSize(int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException(SIZE_OUT_OF_RANGE.getMessage());
        }
    }

    private List<String> createBridgeAnswer(final int size) {
        List<String> bridgeAnswer = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int randomNumber = bridgeNumberGenerator.generate();
            bridgeAnswer.add(BridgeCommand.getCommand(randomNumber));
        }
        return bridgeAnswer;
    }
}
