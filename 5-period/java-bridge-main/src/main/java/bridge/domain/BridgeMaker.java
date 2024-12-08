package bridge.domain;

import java.util.List;
import java.util.stream.IntStream;

public class BridgeMaker {

    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final int UP_NUMBER = 1;
    private static final int DOWN_NUMBER = 0;

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }


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
