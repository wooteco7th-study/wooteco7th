package bridge.dto.response;

import bridge.domain.BridgeGame;
import bridge.domain.CurrentMap;

public record ResultResponse(
        CurrentMap finalMap,
        String result,
        int totalTrialCount
) {
    public static ResultResponse of(CurrentMap finalMap, BridgeGame bridgeGame) {
        return new ResultResponse(
                finalMap,
                bridgeGame.getResult(),
                bridgeGame.getTotalTrialCount());
    }
}
