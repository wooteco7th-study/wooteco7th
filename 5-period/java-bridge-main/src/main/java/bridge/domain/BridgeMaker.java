package bridge.domain;

import java.util.List;
import java.util.stream.IntStream;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final int UP_NUMBER = 1;
    private static final int DOWN_NUMBER = 0;

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        return IntStream.range(0, size)
                .mapToObj(idx -> getBridge(bridgeNumberGenerator.generate()))
                .toList();
    }

    private String getBridge(final int number) {
        if (number == UP_NUMBER) {
            return UP;
        }
        if (number == DOWN_NUMBER) {
            return DOWN;
        }
        return "";
    }
}
