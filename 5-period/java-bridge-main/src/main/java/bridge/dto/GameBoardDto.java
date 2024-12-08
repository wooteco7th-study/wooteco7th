package bridge.dto;

import bridge.domain.bridge.BridgeLog;
import bridge.domain.bridge.LogType;
import java.util.List;

public record GameBoardDto(List<String> upBridgeLogs, List<String> downBridgeLogs) {
    public static GameBoardDto from(final BridgeLog bridgeLog) {
        List<String> upBridgeLogs = makeUpBridgeLogs(bridgeLog.getUpBridge());
        List<String> downBridgeLogs = makeUpBridgeLogs(bridgeLog.getDownBridge());
        return new GameBoardDto(upBridgeLogs, downBridgeLogs);
    }

    private static List<String> makeUpBridgeLogs(final List<LogType> bridgeLog) {
        return bridgeLog.stream()
                .map(LogType::getMark)
                .toList();
    }
}
