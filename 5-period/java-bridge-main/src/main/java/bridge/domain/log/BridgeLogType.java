package bridge.domain.log;

import java.util.Objects;

public enum BridgeLogType {

    PASS("O"),
    FAIL("X"),
    NONE(" ");

    private final String expression;

    BridgeLogType(final String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public static boolean isPassed(final BridgeLogType bridgeLogType) {
        return Objects.equals(bridgeLogType, BridgeLogType.PASS);
    }
}
