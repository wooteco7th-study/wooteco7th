package bridge.domain;
import bridge.error.CustomIllegalStateException;
import bridge.error.ErrorMessage;
import java.util.List;

public class BridgeLogGroup {

    private final List<BridgeLog> bridgeLogs;

    public BridgeLogGroup(final BridgeLog... bridgeLogs) {
        this.bridgeLogs = List.of(bridgeLogs);
    }

    public void updateBridgeLogs(final boolean matchedMoveCommand, final MoveCommand moveCommand) {
        for (BridgeLog bridgeLog : bridgeLogs) {
            bridgeLog.updateLog(moveCommand, matchedMoveCommand);
        }
    }

    public List<BridgeLogType> getUpBridgeLogs() {
        return bridgeLogs.stream()
                .filter(UpBridgeLog.class::isInstance)
                .findAny()
                .map(BridgeLog::getValues)
                .orElseThrow(() -> new CustomIllegalStateException(ErrorMessage.INVALID_NOT_FOUND_BRIDGE_LOG));
    }

    public List<BridgeLogType> getDownBridgeLogs() {
        return bridgeLogs.stream()
                .filter(DownBridgeLog.class::isInstance)
                .findAny()
                .map(BridgeLog::getValues)
                .orElseThrow(() -> new CustomIllegalStateException(ErrorMessage.INVALID_NOT_FOUND_BRIDGE_LOG));
    }

    public void clear() {
        for (BridgeLog bridgeLog : bridgeLogs) {
            bridgeLog.clear();
        }
    }

    public int countTotalPass() {
        return bridgeLogs.stream()
                .mapToInt(BridgeLog::countPass)
                .sum();
    }
}
