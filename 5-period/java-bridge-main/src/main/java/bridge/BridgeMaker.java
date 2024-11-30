package bridge;

import bridge.exception.CustomIllegalStateException;
import bridge.exception.ErrorMessage;
import bridge.generator.BridgeNumberGenerator;
import java.util.ArrayList;
import java.util.List;

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
        List<String> directions = new ArrayList<>();
        while (directions.size() != size) {
            directions.add(toDirection(bridgeNumberGenerator.generate()));
        }
        return directions;
    }

    private String toDirection(int input) {
        if (input == 1) {
            return "U";
        }
        if (input == 0) {
            return "D";
        }
        throw new CustomIllegalStateException(ErrorMessage.INVALID_DIRECTION_VALUE);
    }
}
